import {Component, EventEmitter, Input, OnDestroy, OnInit, Output, TemplateRef} from '@angular/core';
import {TableModel} from "../../../model/TableModel";
import {Lesson} from "../../../model/lesson";
import {Subscription} from "rxjs";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TableModelService} from "../../../service/table-model.service";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {ProfessorAccount} from "../../../model/professor-account";
import {Group} from "../../../model/group";
import {DaysOfWeek} from "../../../model/DaysOfWeek";
import {RequestHelper} from "../../../model/RequestHelper";
import {Constants} from "../../../share/constants";
import {Page} from "../../../model/page";

@Component({
  selector: 'schedule-tab',
  templateUrl: './schedule-tab.component.html',
  styleUrls: ['./schedule-tab.component.css']
})
export class ScheduleTabComponent implements OnInit, OnDestroy {

  public isGroupsScheduleCollapsed: boolean = false;

  private subscriptions: Subscription[] = [];

  public editMode: boolean = false;
  private modalRef: BsModalRef;

  public editableLesson: Lesson;

  public professorPage: Page<ProfessorAccount>;
  public groupPage: Page<Group>;
  public sortDirection: boolean = false;
  public itemsPerPage: number = Constants.NUMBER_OF_ROWS_ON_ONE_PAGE;
  public adminMap: Map<number, DaysOfWeek<Lesson>>;
  public professorMap: Map<number, DaysOfWeek<Lesson>>;

  constructor(private loadingService: Ng4LoadingSpinnerService,
              private tableModelService: TableModelService,
              private modalService: BsModalService) {
  }

  ngOnInit() {
    this.editableLesson = new Lesson();

    this.adminMap = new Map<number, DaysOfWeek<Lesson>>();
    this.professorMap = new Map<number, DaysOfWeek<Lesson>>();

    this.groupPage = new Page<Group>();
    this.professorPage = new Page<ProfessorAccount>();

    this.getGroupPage(1);
    this.getProfessorPage(1);

    this.groupPage.content.forEach(group =>
      this.adminMap.set(group.id, new DaysOfWeek<Lesson>()));
    this.professorPage.content.forEach(professor => {
      this.professorMap.set(professor.id, new DaysOfWeek<Lesson>());
    })
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  deleteLesson(lesson: Lesson, group?: Group): void {
    this.loadingService.show();
    //if we pass group then we delete lesson from group. in other case we delete lesson at all
    if (group) {
      lesson.groups = lesson.groups.filter(gr => gr.id != group.id);

      //we are deleting lesson if we have no more groups in it
      if (lesson.groups.length == 0) {
        this.subscriptions.push(this.tableModelService.deleteLesson(lesson.id).subscribe(() => {
          this.getGroupSchedule(group.id);
          this.loadingService.hide()
        }))
      } else {
        this.subscriptions.push(this.tableModelService.saveLesson(lesson).subscribe(req => {
          this.getGroupSchedule(group.id);
          this.loadingService.hide();
        }));
      }

    } else {
      this.subscriptions.push(this.tableModelService.deleteLesson(lesson.id).subscribe(() => {
        this.getProfessorSchedule(lesson.professor.id);
        this.loadingService.hide();
      }));
    }
  }

  openModal(template: TemplateRef<any>, lesson?: Lesson): void {
    if (lesson) {
      this.editableLesson = Lesson.cloneLesson(lesson);
      this.editMode = true;
    }
    this.modalRef = this.modalService.show(template);
  }

  closeModal(): void {
    this.modalRef.hide();
  }

  getGroupSchedule(groupId: number) {
    this.loadingService.show();
    if (!this.adminMap.has(groupId)) {
      this.adminMap.set(groupId, new DaysOfWeek<Lesson>());
    }

    this.subscriptions.push(
      this.tableModelService.getGroupLessons(groupId, new Date()).subscribe(req => {
        let tempDays: DaysOfWeek<Lesson> = DaysOfWeek.transformLessonsToDaysOfWeek(req as Lesson[]);
        this.adminMap.set(groupId, tempDays);
        this.loadingService.hide();
      }));
  }

  getProfessorSchedule(professorId: number) {
    this.loadingService.show();
    if (!this.professorMap.has(professorId)) {
      this.professorMap.set(professorId, new DaysOfWeek<Lesson>());
    }

    this.subscriptions.push(this.tableModelService.getProfessorLessons(
      professorId, new Date()).subscribe(request => {
      let tempDays: DaysOfWeek<Lesson> = DaysOfWeek.transformLessonsToDaysOfWeek(request as Lesson[]);
      console.log(tempDays);
      this.professorMap.set(professorId, tempDays);
      this.loadingService.hide();
    }));
  }

  getGroupPage(pageNumber: number) {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.getPageObservable<Group>(
      RequestHelper.GROUP,
      pageNumber - 1,
      this.itemsPerPage,
      'id,' + (this.sortDirection ? 'desc' : 'asc'))
      .subscribe(req => {
        this.groupPage = req as Page<Group>;
        this.groupPage.number += 1;

        this.groupPage.content.forEach(group =>
          this.adminMap.has(group.id) ? true : this.adminMap.set(group.id, new DaysOfWeek<Lesson>()));

        this.loadingService.hide();
      }));
  }


  getProfessorPage(pageNumber: number) {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.getPageObservable<ProfessorAccount>(
      RequestHelper.PROFESSOR,
      pageNumber - 1,
      this.itemsPerPage,
      'id,' + (this.sortDirection ? 'desc' : 'asc'))
      .subscribe(req => {
        this.professorPage = req as Page<ProfessorAccount>;
        this.professorPage.number += 1;

        this.professorPage.content.forEach(professor => {
          this.professorMap.has(professor.id) ? true : this.professorMap.set(professor.id, new DaysOfWeek<Lesson>())
        });
        this.loadingService.hide();
      })
    );
  }
}

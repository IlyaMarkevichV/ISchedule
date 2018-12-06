import {Component, EventEmitter, Input, OnDestroy, OnInit, Output, TemplateRef} from '@angular/core';
import {TableModel} from "../../../model/TableModel";
import {Lesson} from "../../../model/lesson";
import {Subscription} from "rxjs";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TableModelService} from "../../../service/table-model.service";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {DatePipe} from "@angular/common";
import {ProfessorAccount} from "../../../model/professor-account";
import {Group} from "../../../model/group";
import {LessonInfo} from "../../../model/lessonInfo";
import {LessonTime} from "../../../model/lessonTime";
import {Day} from "../../../model/day";

@Component({
  selector: 'schedule-tab',
  templateUrl: './schedule-tab.component.html',
  styleUrls: ['./schedule-tab.component.css']
})
export class ScheduleTabComponent implements OnInit, OnDestroy {

  @Input()
  public tableModel: TableModel;

  @Output()
  public loadLessons: EventEmitter<any> = new EventEmitter<any>();

  public isGroupsScheduleCollapsed: boolean = false;
  public isProfessorsScheduleCollapsed: boolean = false;

  private subscriptions: Subscription[] = [];

  public editMode: boolean = false;
  private modalRef: BsModalRef;

  public editableLesson: Lesson;

  constructor(private loadingService: Ng4LoadingSpinnerService,
              private tableModelService: TableModelService,
              private modalService: BsModalService) {
  }

  ngOnInit() {
    this.editableLesson = new Lesson();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  updateSchedule(): void {
    this.loadLessons.emit();
    //TODO: pages
  }

  deleteLesson(lesson: Lesson, group?: Group): void {
    this.loadingService.show();
    //if we pass group then we delete lesson from group. in other case we delete lesson at all
    if (group) {
      lesson.groups = lesson.groups.filter(gr => gr.id != group.id);
      this.subscriptions.push(this.tableModelService.saveLesson(lesson).subscribe(req => {
        this.updateSchedule();
        this.loadingService.hide();
      }));
    } else {
      this.subscriptions.push(this.tableModelService.deleteLesson(lesson.id).subscribe(() => {
        this.updateSchedule();
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

  saveLesson(): void {
    this.loadingService.show();
    console.log(this.editableLesson);
    this.subscriptions.push(this.tableModelService.saveLesson(this.editableLesson).subscribe(req => {
      this.updateSchedule();
      this.closeModal();
      this.loadingService.hide();
    }));
  }

  compareFn(obj1: any, obj2: any): boolean {
    return obj1 && obj2 ? obj1.id === obj2.id : obj1 === obj2;
  }
}

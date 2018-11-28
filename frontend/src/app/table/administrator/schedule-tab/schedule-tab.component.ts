import {Component, EventEmitter, Input, OnDestroy, OnInit, Output, TemplateRef} from '@angular/core';
import {TableModel} from "../../../model/TableModel";
import {Lesson} from "../../../model/lesson";
import {Subscription} from "rxjs";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TableModelService} from "../../../service/table-model.service";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {DatePipe} from "@angular/common";
import {ProfessorAccount} from "../../../model/professor-account";

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

  public isGroupsScheduleCollapsed: boolean = true;
  public isProfessorsScheduleCollapsed: boolean = true;

  private subscriptions: Subscription[] = [];

  public editMode: boolean = false;
  public editableLesson: Lesson;
  private modalRef: BsModalRef;

  public lessonInfoId: number;
  public professorId: number;
  public timeId: number;
  public dayId: number;
  public groups: number[]=[];

  constructor(private loadingService: Ng4LoadingSpinnerService,
              private tableModelService: TableModelService,
              private modalService: BsModalService,
              private datePipe: DatePipe) { }

  ngOnInit() {
    this.editableLesson = new Lesson();
   /* this.editableLesson.day =*/
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  refreshEditableLesson(): void {
    this.editableLesson = new Lesson();
  }

  updateLessons(): void {
    this.loadLessons.emit();
    //TODO: pages
  }

  deleteLesson(lesson: Lesson): void {
    this.subscriptions.push(this.tableModelService.deleteLesson(lesson.lessonId).subscribe(() => {
      this.updateLessons();
    }));
  }

  openModal(template: TemplateRef<any>, lesson?: Lesson): void {
    if (lesson) {
      this.editableLesson = Lesson.cloneLesson(lesson);
      this.editMode = true;
      this.lessonInfoId = this.editableLesson.lessonInfo.lessonInfoId;
      this.professorId = this.editableLesson.professor.professorId;
      this.timeId = this.editableLesson.lessonTime.id;
      this.dayId = this.editableLesson.day.dayNumber;
      for (let group of this.editableLesson.groups) {
        this.groups.push(group.groupId);
      }
    } else {
      this.refreshEditableLesson();
      this.editMode = false;
      this.lessonInfoId = this.tableModel.lessonInfos.length != 0 ? this.tableModel.lessonInfos[0].lessonInfoId : 1;
      this.professorId = this.tableModel.professors.length != 0 ? this.tableModel.professors[0].professorId : 1;
      this.timeId = this.tableModel.lessonTimes.length != 0 ? this.tableModel.lessonTimes[0].id : 0;
      this.dayId = this.tableModel.studyDays.length != 0 ? this.tableModel.studyDays[0].dayNumber : 0;
      this.groups = [];
    }
    this.modalRef = this.modalService.show(template);
  }

  closeModal(): void {
    this.modalRef.hide();
  }
}

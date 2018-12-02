import {Component, EventEmitter, Input, OnDestroy, OnInit, Output, TemplateRef} from '@angular/core';
import {Subscription} from "rxjs";
import {TableModel} from "../../../model/TableModel";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Lesson} from "../../../model/lesson";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TableModelService} from "../../../service/table-model.service";

@Component({
  selector: 'lesson-tab',
  templateUrl: './lesson-tab.component.html',
  styleUrls: ['./lesson-tab.component.css']
})
export class LessonTabComponent implements OnInit, OnDestroy {

  @Input()
  public tableModel: TableModel;

  @Output()
  public loadLessons: EventEmitter<any> = new EventEmitter<any>();

  private subscriptions: Subscription[] = [];

  public editMode: boolean = false;
  private modalRef: BsModalRef;

  public editableLesson: Lesson;

  constructor(private loadingService: Ng4LoadingSpinnerService,
              private tableModelService: TableModelService,
              private modalService: BsModalService) { }

  ngOnInit() {
    this.editableLesson = new Lesson();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  updateLessons(): void {
    this.loadLessons.emit();
    //TODO: pages
  }

  saveLesson(): void {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.saveLesson(this.editableLesson).subscribe(req => {
      this.updateLessons();
      this.loadingService.hide();
    }));
  }

  deleteLesson(): void {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.deleteLesson(this.editableLesson.id).subscribe(req => {
      this.updateLessons();
      this.loadingService.hide();
    }));
  }

  refreshEditableLesson(): void {
    this.editableLesson = new Lesson();
  }

  openModal(template: TemplateRef<any>, lesson?: Lesson): void {
    if (lesson) {
      this.editableLesson = Lesson.cloneLesson(lesson);
      this.editMode = true;
    } else {
      this.refreshEditableLesson();
      this.editMode = false;
      this.editableLesson.professor =
        (this.tableModel.professors && this.tableModel.professors.length != 0) ? this.tableModel.professors[0] : null;
      this.editableLesson.day =
        (this.tableModel.studyDays && this.tableModel.studyDays.length != 0) ? this.tableModel.studyDays[0] : null;
      this.editableLesson.lessonTime =
        (this.tableModel.lessonTimes && this.tableModel.lessonTimes.length != 0) ? this.tableModel.lessonTimes[0] : null;
      this.editableLesson.lessonInfo =
        (this.tableModel.lessonInfos && this.tableModel.lessonInfos.length != 0) ? this.tableModel.lessonInfos[0] : null;
      this.editableLesson.groups = [];
    }
    this.modalRef = this.modalService.show(template);
  }

  closeModal(): void {
    this.modalRef.hide();
  }

  compareFn(obj1: any, obj2: any): boolean {
    return obj1 && obj2 ? obj1.id === obj2.id : obj1 === obj2;
  }
}
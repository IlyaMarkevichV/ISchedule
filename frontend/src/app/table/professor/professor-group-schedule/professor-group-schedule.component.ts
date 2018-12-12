import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {TableModel} from "../../../model/TableModel";
import {TableModelService} from "../../../service/table-model.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Subscription} from "rxjs";
import {DaysOfWeek} from "../../../model/DaysOfWeek";
import {Lesson} from "../../../model/lesson";
import {RequestHelper} from "../../../model/RequestHelper";
import {Constants} from "../../../share/constants";
import {Page} from "../../../model/page";
import {StudentAccount} from "../../../model/student-account";
import {Group} from "../../../model/group";

@Component({
  selector: 'professor-group-schedule',
  templateUrl: './professor-group-schedule.component.html',
  styleUrls: ['./professor-group-schedule.component.css']
})
export class ProfessorGroupScheduleComponent implements OnInit, OnDestroy {

  @Input()
  public tableModel: TableModel;

  private subscriptions: Subscription[] = [];
  public professorMap: Map<number, DaysOfWeek<Lesson>>;
  public isGroupsScheduleCollapsed: boolean = false;

  public studentPage: Page<StudentAccount>;
  public groupPage: Page<Group>;
  public sortDirection: boolean = false;
  public itemsPerPage: number = Constants.NUMBER_OF_ROWS_ON_ONE_PAGE;

  constructor(
    private loadingService: Ng4LoadingSpinnerService,
    private tableModelService: TableModelService) {
  }

  ngOnInit() {
    this.professorMap = new Map<number, DaysOfWeek<Lesson>>();

    this.groupPage = new Page<Group>();
    this.studentPage = new Page<StudentAccount>();

    this.getGroupPage(1);
    this.getStudentPage(1);

    this.groupPage.content.forEach(group =>
      this.professorMap.set(group.id, new DaysOfWeek<Lesson>()));
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  getGroupSchedule(groupId: number) {
    this.loadingService.show();
    if (!this.professorMap.has(groupId)) {
      this.professorMap.set(groupId, new DaysOfWeek<Lesson>());
    }

    this.subscriptions.push(
      this.tableModelService.getGroupLessons(groupId, new Date()).subscribe(req => {
        let tempDays: DaysOfWeek<Lesson> = DaysOfWeek.transformLessonsToDaysOfWeek(req as Lesson[]);
        this.professorMap.set(groupId, tempDays);
        this.loadingService.hide();
      }));
  }

  getGroupPage(pageNumber: number) {
    this.loadingService.show();
    console.log(pageNumber);
    console.log('id,' + (this.sortDirection ? 'desc' : 'asc'));
    this.subscriptions.push(this.tableModelService.getPageObservable<Group>(
      RequestHelper.GROUP,
      pageNumber - 1,
      Constants.NUMBER_OF_ROWS_ON_ONE_PAGE,
      'id,' + (this.sortDirection ? 'desc' : 'asc'))
      .subscribe(req => {
        this.groupPage = req as Page<Group>;
        this.groupPage.number += 1;

        this.groupPage.content.forEach(group =>
          this.professorMap.has(group.id) ? true : this.professorMap.set(group.id, new DaysOfWeek<Lesson>()));

        this.loadingService.hide();
      }));
  }

  getStudentPage(pageNumber: number) {
    this.loadingService.show();
    console.log(pageNumber);
    console.log('id,' + (this.sortDirection ? 'desc' : 'asc'));
    this.subscriptions.push(this.tableModelService.getPageObservable<StudentAccount>(
      RequestHelper.STUDENT,
      pageNumber - 1,
      Constants.NUMBER_OF_ROWS_ON_ONE_PAGE,
      'id,' + (this.sortDirection ? 'desc' : 'asc'))
      .subscribe(req => {
        this.studentPage = req as Page<StudentAccount>;
        this.studentPage.number += 1;

        this.studentPage.content.forEach(student =>
          this.professorMap.has(student.group.id) ?
            true
            :
            this.professorMap.set(student.group.id, new DaysOfWeek<Lesson>()));
        this.loadingService.hide();
      }));
  }
}

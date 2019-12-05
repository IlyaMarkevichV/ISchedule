import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TableModelService} from "../../../service/table-model.service";
import {AuthorizationAndTransmitService} from "../../../service/authorization-and-transmit.service";
import {Subscription} from "rxjs";
import {DaysOfWeek} from "../../../model/DaysOfWeek";
import {Lesson} from "../../../model/lesson";
import {Attendance} from "../../../model/Attendance";
import {ProfessorAccount} from "../../../model/professor-account";

@Component({
  selector: 'professor-attendance',
  templateUrl: './professor-attendance.component.html',
  styleUrls: ['./professor-attendance.component.css']
})
export class ProfessorAttendanceComponent implements OnInit, OnDestroy {

  private subscriptions: Subscription[] = [];
  public days: DaysOfWeek<Lesson>;
  public attendances: Map<number, Attendance[]>;
  public currentLessons: Lesson[] = [];
  public alerts: Map<number, boolean>;

  @Input()
  public professor: ProfessorAccount;

  constructor(
    private loadingService: Ng4LoadingSpinnerService,
    private tableModelService: TableModelService,
    private authService: AuthorizationAndTransmitService) {
  }

  ngOnInit() {
    this.loadingService.show();
    this.alerts = new Map<number, boolean>();
    this.attendances = new Map<number, Attendance[]>();
    this.days = new DaysOfWeek<Lesson>();
    this.currentLessons = [];

    this.subscriptions.push(this.authService.currentProfessorLessons.subscribe(req => {
      this.days = DaysOfWeek.transformLessonsToDaysOfWeek(req);
      this.currentLessons = DaysOfWeek.getLessonsAccordingToWeekDay(this.days);

      //add in map all non-repeat groups and create alerts for them
      for (let lesson of this.currentLessons) {
        for (let group of lesson.groups) {
          if (!this.attendances.has(group.id)) {
            this.attendances.set(group.id, []);
            this.alerts.set(group.id, false);
          }
        }
      }
      this.loadingService.hide();
    }))
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  getAttendanceByGroupId(groupId: number, lessonId: number): void {
    this.loadingService.show();
    let currentDate: Date = new Date();
    this.subscriptions.push(this.tableModelService.getGroupAttendance(3, groupId, lessonId, currentDate, currentDate).subscribe(req => {
      this.attendances.set(groupId, req as Attendance[]);
      console.log('Req: ', req);
      console.log('Att:', this.attendances);
      console.log('Alerts:', this.alerts);
      this.loadingService.hide();
    }));
  }

  saveAttendances(groupId: number, lessonId: number): void {
    this.loadingService.show();
    let temp: Attendance[] = this.attendances.get(groupId);
    let counter: number = 0;

    for (let attendance of temp) {
      if (attendance.status != 3)
        counter++;
    }
    if (counter < temp.length) {
      this.alerts.set(groupId, true);
      this.loadingService.hide();
      return;
    }

    this.subscriptions.push(this.tableModelService.saveAttendance(this.attendances.get(groupId)).subscribe(req => {
      this.getAttendanceByGroupId(groupId, lessonId);
      this.loadingService.hide();
    }));
  }
}

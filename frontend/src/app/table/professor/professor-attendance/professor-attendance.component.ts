import {Component, OnDestroy, OnInit} from '@angular/core';
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TableModelService} from "../../../service/table-model.service";
import {AuthorizationService} from "../../../service/authorization.service";
import {Subscription} from "rxjs";
import {DaysOfWeek} from "../../../model/DaysOfWeek";
import {Lesson} from "../../../model/lesson";
import {Attendance} from "../../../model/Attendance";
import {Group} from "../../../model/group";

@Component({
  selector: 'professor-attendance',
  templateUrl: './professor-attendance.component.html',
  styleUrls: ['./professor-attendance.component.css']
})
export class ProfessorAttendanceComponent implements OnInit, OnDestroy {

  private subscriptions: Subscription[] = [];
  public days: DaysOfWeek<Lesson>;
  public attendances: Attendance[] = [];
  public currentLessons: Lesson[];

  constructor(
    private loadingService: Ng4LoadingSpinnerService,
    private tableModelService: TableModelService,
    private authService: AuthorizationService) {
  }

  ngOnInit() {
    this.loadingService.show();
    this.days = new DaysOfWeek<Lesson>();
    this.subscriptions.push(this.authService.currentProfessorLessons.subscribe(req => {
      this.days = DaysOfWeek.transformLessonsToDaysOfWeek(req);
      this.currentLessons = DaysOfWeek.getLessonsAccordingToWeekDay(this.days);
      this.loadingService.hide();
    }))
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  getAttendanceByGroupId(groupId: number): void {
    this.loadingService.show();
    this.attendances = [];
    let currentDate: Date = new Date();
    this.subscriptions.push(this.tableModelService.getGroupAttendance(3, groupId, currentDate, currentDate).subscribe(req => {
      this.attendances = req as Attendance[];
      this.loadingService.hide();
    }));
  }
}

import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {StudentAccount} from "../../../model/student-account";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TableModelService} from "../../../service/table-model.service";
import {Subscription} from "rxjs";
import {Attendance} from "../../../model/Attendance";

@Component({
  selector: 'student-attendance',
  templateUrl: './student-attendance.component.html',
  styleUrls: ['./student-attendance.component.css']
})
export class StudentAttendanceComponent implements OnInit, OnDestroy {

  @Input()
  public student: StudentAccount;

  public dates: Date[];
  private subscriptions: Subscription[] = [];
  public attendances: Attendance[];
  public minDate: Date;
  public maxDate: Date;

  constructor(private loadingService: Ng4LoadingSpinnerService,
              private tableModelService: TableModelService) {
  }

  ngOnInit() {
    this.maxDate = new Date();
    this.minDate = new Date();
    if (this.maxDate.getMonth() >= 8) {
      this.maxDate.setFullYear(this.maxDate.getFullYear() + 1, 6, 5);
      this.minDate.setFullYear(this.maxDate.getFullYear() - 1, 8, 1);
    } else {
      this.maxDate.setFullYear(this.maxDate.getFullYear(), 6, 5);
      this.minDate.setFullYear(this.maxDate.getFullYear() - 1, 8, 1);
    }
    this.dates = [this.minDate, new Date(this.minDate.getFullYear(), this.minDate.getMonth() + 1, this.minDate.getDate())]
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  getStudentAttendance() {
    if (this.dates) {
      this.loadingService.show();
      console.log(this.dates);
      this.subscriptions.push(this.tableModelService.
      getStudentAttendance(2, this.student.id, this.dates[0], this.dates[1]).
      subscribe(req => {
        this.attendances = req as Attendance[];
        this.loadingService.hide();
      }));
    }
  }
}

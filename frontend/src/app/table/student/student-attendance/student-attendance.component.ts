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

  public dates: Date[] = [new Date(2018, 0, 1), new Date(2018, 0 ,2)];
  private subscriptions: Subscription[] = [];
  public attendances: Attendance[];

  constructor(private loadingService: Ng4LoadingSpinnerService,
              private tableModelService: TableModelService) {
  }

  ngOnInit() {
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

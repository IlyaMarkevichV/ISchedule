import {Component, Input, OnInit} from '@angular/core';
import {StudentAccount} from "../../../model/student-account";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TableModelService} from "../../../service/table-model.service";
import {AuthorizationService} from "../../../service/authorization.service";

@Component({
  selector: 'student-attendance',
  templateUrl: './student-attendance.component.html',
  styleUrls: ['./student-attendance.component.css']
})
export class StudentAttendanceComponent implements OnInit {

  @Input()
  public student: StudentAccount;

  constructor(private loadingService: Ng4LoadingSpinnerService,
              private tableModelService: TableModelService) {
  }

  ngOnInit() {
  }

}

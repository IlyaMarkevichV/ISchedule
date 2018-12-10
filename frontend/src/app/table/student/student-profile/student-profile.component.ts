import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {TableModel} from "../../../model/TableModel";
import {ProfessorAccount} from "../../../model/professor-account";
import {StudentAccount} from "../../../model/student-account";
import {Subscription} from "rxjs";
import {TableModelService} from "../../../service/table-model.service";
import {TokenStorage} from "../../../service/token-storage.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {AuthorizationService} from "../../../service/authorization.service";
import {Lesson} from "../../../model/lesson";
import {DaysOfWeek} from "../../../model/DaysOfWeek";
import {BsModalService} from "ngx-bootstrap";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'student-profile',
  templateUrl: './student-profile.component.html',
  styleUrls: ['./student-profile.component.css']
})
export class StudentProfileComponent implements OnInit, OnDestroy {

  @Input()
  public student: StudentAccount;

  private subscriptions: Subscription[] = [];
  public days: DaysOfWeek<Lesson>;

  constructor(private loadingService: Ng4LoadingSpinnerService,
              private tableModelService: TableModelService,
              private modalService: BsModalService,
              private datePipe: DatePipe,
              private authService: AuthorizationService) {
  }

  ngOnInit() {
    this.loadingService.show();
    this.days = new DaysOfWeek<Lesson>();

    this.subscriptions.push(this.authService.currentStudentLessons.subscribe(req => {
      this.days = DaysOfWeek.transformLessonsToDaysOfWeek(req);
      this.loadingService.hide();
    }));
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}

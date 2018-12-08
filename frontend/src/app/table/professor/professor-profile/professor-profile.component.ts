import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TableModelService} from "../../../service/table-model.service";
import {BsModalService} from "ngx-bootstrap";
import {DatePipe} from "@angular/common";
import {ProfessorAccount} from "../../../model/professor-account";
import {DaysOfWeek} from "../../../model/DaysOfWeek";
import {Lesson} from "../../../model/lesson";
import {Subscription} from "rxjs";
import {AuthorizationService} from "../../../service/authorization.service";

@Component({
  selector: 'professor-profile',
  templateUrl: './professor-profile.component.html',
  styleUrls: ['./professor-profile.component.css']
})
export class ProfessorProfileComponent implements OnInit, OnDestroy {

  @Input()
  public professor: ProfessorAccount;

  private currDay: Date;
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
    this.currDay = new Date();
    this.subscriptions.push(this.authService.currentDays.subscribe(req => this.days = req));
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

}

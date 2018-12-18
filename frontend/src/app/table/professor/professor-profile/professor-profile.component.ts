import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TableModelService} from "../../../service/table-model.service";
import {BsModalService} from "ngx-bootstrap";
import {DatePipe} from "@angular/common";
import {ProfessorAccount} from "../../../model/professor-account";
import {DaysOfWeek} from "../../../model/DaysOfWeek";
import {Lesson} from "../../../model/lesson";
import {Subscription} from "rxjs";
import {AuthorizationAndTransmitService} from "../../../service/authorization-and-transmit.service";

@Component({
  selector: 'professor-profile',
  templateUrl: './professor-profile.component.html',
  styleUrls: ['./professor-profile.component.css']
})
export class ProfessorProfileComponent implements OnInit, OnDestroy {

  @Input()
  public professor: ProfessorAccount;

  private subscriptions: Subscription[] = [];
  public days: DaysOfWeek<Lesson>;
  public professorLessons: Array<string>;

  constructor(private loadingService: Ng4LoadingSpinnerService,
              private tableModelService: TableModelService,
              private modalService: BsModalService,
              private datePipe: DatePipe,
              private authService: AuthorizationAndTransmitService) {
  }

  ngOnInit() {
    this.loadingService.show();
    this.days = new DaysOfWeek<Lesson>();
    this.professorLessons = new Array<string>();

    this.subscriptions.push(this.authService.currentProfessorLessons.subscribe(req => {
      console.log("profile: ", req);
      let set: Set<string> = new Set<string>();
      req.forEach(lesson => set.add(lesson.lessonInfo.lessonName));
      this.professorLessons = Array.from(set);

      this.days = DaysOfWeek.transformLessonsToDaysOfWeek(req);
      this.loadingService.hide();
    }));
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

}

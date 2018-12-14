import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {TableModel} from "../../../model/TableModel";
import {Subscription} from "rxjs";
import {DaysOfWeek} from "../../../model/DaysOfWeek";
import {Lesson} from "../../../model/lesson";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TableModelService} from "../../../service/table-model.service";
import {Page} from "../../../model/page";
import {ProfessorAccount} from "../../../model/professor-account";
import {RequestHelper} from "../../../model/RequestHelper";
import {Constants} from "../../../share/constants";

@Component({
  selector: 'student-professors',
  templateUrl: './student-professors.component.html',
  styleUrls: ['./student-professors.component.css']
})
export class StudentProfessorsComponent implements OnInit, OnDestroy {

  @Input()
  public tableModel: TableModel;

  private subscriptions: Subscription[] = [];
  public studentMap: Map<number, DaysOfWeek<Lesson>>;

  public professorPage: Page<ProfessorAccount>;
  public sortDirection: boolean = false;
  public itemsPerPage: number = Constants.NUMBER_OF_ROWS_ON_ONE_PAGE;

  constructor(
    private loadingService: Ng4LoadingSpinnerService,
    private tableModelService: TableModelService) {
  }

  ngOnInit() {
    this.loadingService.show();
    this.professorPage = new Page<ProfessorAccount>();
    this.studentMap = new Map<number, DaysOfWeek<Lesson>>();

    this.subscriptions.push(this.tableModelService.getPageObservable<ProfessorAccount>(
      RequestHelper.PROFESSOR,
      0,
      Constants.NUMBER_OF_ROWS_ON_ONE_PAGE,
      'id,' + (this.sortDirection ? 'desc' : 'asc'))
      .subscribe(req => {
        this.professorPage = req as Page<ProfessorAccount>;
        this.professorPage.number += 1;
        this.professorPage.content.forEach(professor =>
          this.studentMap.set(professor.id, new DaysOfWeek<Lesson>()));
        this.loadingService.hide();
      }));
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  getProfessorSchedule(professorId: number) {
    this.loadingService.show();
    if (!this.studentMap.has(professorId)) {
      this.studentMap.set(professorId, new DaysOfWeek<Lesson>());
    }

    this.subscriptions.push(
      this.tableModelService.getProfessorLessons(professorId, new Date()).subscribe(req => {
        let tempDays: DaysOfWeek<Lesson> = DaysOfWeek.transformLessonsToDaysOfWeek(req as Lesson[]);
        this.studentMap.set(professorId, tempDays);
        this.loadingService.hide();
      }));
  }

  getPage(pageNumber: number) {
    this.loadingService.show();
    console.log(pageNumber);
    console.log('id,' + (this.sortDirection ? 'desc' : 'asc'));
    this.subscriptions.push(this.tableModelService.getPageObservable<ProfessorAccount>(
      RequestHelper.PROFESSOR,
      pageNumber - 1,
      Constants.NUMBER_OF_ROWS_ON_ONE_PAGE,
      'id,' + (this.sortDirection ? 'desc' : 'asc'))
      .subscribe(req => {
        this.professorPage = req as Page<ProfessorAccount>;
        this.professorPage.number += 1;
        this.loadingService.hide();
      }));
  }
}

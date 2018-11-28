import {Component, EventEmitter, Input, OnDestroy, OnInit, Output, ViewChild} from "@angular/core";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TableModel} from "../model/TableModel";
import {TableModelService} from "../service/table-model.service";
import {Subscription} from "rxjs";
import {Faculty} from "../model/faculty";
import {Group} from "../model/group";
import {UserAccount} from "../model/UserAccount";
import {ActivatedRoute} from "@angular/router";
import {Role} from "../model/role";
import {ProfessorAccount} from "../model/professor-account";
import {StudentAccount} from "../model/student-account";
import {AuthorizationService} from "../service/authorization.service";
import {Lesson} from "../model/lesson";
import {LessonTime} from "../model/lessonTime";
import {LessonInfo} from "../model/lessonInfo";
import {Day} from "../model/day";

@Component({
  selector: 'table-component',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit, OnDestroy {

  public logginedUserAccount: UserAccount;

  private subscriptions: Subscription[] = [];

  public tableModel: TableModel;

  constructor(
    private loadingService: Ng4LoadingSpinnerService,
    private tableModelService: TableModelService,
    private authService: AuthorizationService) {
  }

  ngOnInit() {
    //TODO: get logginedAcc
    this.authService.currentAuthorizedUser.subscribe(user => {
      this.logginedUserAccount = user as UserAccount;
      console.log("Auth User in table component: " + this.logginedUserAccount);
    });

    this.tableModel = new TableModel();

    this.loadRoles();
    this.loadFaculties();
    this.loadGroups();
    this.loadProfessors();
    this.loadStudents();
    this.loadLessonInfos();
    this.loadLessons();
    this.loadLessonTimes();
    this.loadStudyDays();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  public loadFaculties(): void {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.getFaculties().subscribe(faculties => {
      this.tableModel.faculties = faculties as Faculty[];
      this.loadingService.hide();
    }))
  }

  public loadGroups(): void {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.getGroups().subscribe(gr => {
      this.tableModel.groups = gr as Group[];
      this.loadingService.hide();
    }));
  }

  public loadStudents(): void {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.getStudents().subscribe(accounts => {
      this.tableModel.students = accounts as StudentAccount[];
      this.loadingService.hide();
    }));
  }

  public loadProfessors(): void {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.getProfessors().subscribe(accounts => {
      this.tableModel.professors = accounts as ProfessorAccount[];
      this.loadingService.hide();
    }));
  }

  public loadRoles(): void {
    this.loadingService.show();
    this.subscriptions.push((this.tableModelService.getRoles().subscribe(roles => {
      this.tableModel.roles = roles as Role[];
      this.loadingService.hide();
    })));
  }

  public loadLessons(): void {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.getLessons().subscribe(lessons => {
      this.tableModel.lessons = lessons as Lesson[];
      this.loadingService.hide();
    }));
  }

  public loadLessonTimes(): void {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.getLessonTimes().subscribe(times => {
      this.tableModel.lessonTimes = times as LessonTime[];
      this.loadingService.hide();
    }));
  }

  public loadLessonInfos(): void {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.getLessonInfos().subscribe(infos => {
      this.tableModel.lessonInfos = infos as LessonInfo[];
      this.loadingService.hide();
    }));
  }

  public loadStudyDays(): void {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.getDays().subscribe(days => {
      this.tableModel.studyDays = days as Day[];
      this.loadingService.hide();
    }))
  }
}

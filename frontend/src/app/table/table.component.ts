import {Component,  OnDestroy, OnInit} from "@angular/core";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TableModel} from "../model/TableModel";
import {TableModelService} from "../service/table-model.service";
import {Subscription} from "rxjs";
import {Faculty} from "../model/faculty";
import {Group} from "../model/group";
import {Role} from "../model/role";
import {ProfessorAccount} from "../model/professor-account";
import {StudentAccount} from "../model/student-account";
import {AuthorizationService} from "../service/authorization.service";
import {Lesson} from "../model/lesson";
import {LessonTime} from "../model/lessonTime";
import {LessonInfo} from "../model/lessonInfo";
import {Day} from "../model/day";
import {Page} from "../model/page";
import {TokenStorage} from "../service/token-storage.service";

@Component({
  selector: 'table-component',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit, OnDestroy {

  public userRole: string;

  private subscriptions: Subscription[] = [];

  public tableModel: TableModel;

/*  public professor: ProfessorAccount;*/
/*  public student: StudentAccount;*/

  constructor(
    private loadingService: Ng4LoadingSpinnerService,
    private tableModelService: TableModelService,
    private authService: AuthorizationService,
    private tokenStorage: TokenStorage) {
  }

  ngOnInit() {
    this.loadingService.show();

    let jwtData = this.tokenStorage.getToken().split('.')[1];
    let decodedJwtJsonData = window.atob(jwtData);
    let decodedJwtData = JSON.parse(decodedJwtJsonData);

    this.userRole = decodedJwtData.scopes;

    this.tableModel = new TableModel();

    this.loadGroups();
    this.loadRoles();
    this.loadFaculties();
    this.loadProfessors();
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
      this.tableModel.groups = (gr as Page<Group>).content;
      this.loadingService.hide();
    }));
  }

  public loadProfessors(): void {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.getProfessors().subscribe(accounts => {
      this.tableModel.professors = (accounts as Page<ProfessorAccount>).content;
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
      this.tableModel.lessons = (lessons as Page<Lesson>).content;
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

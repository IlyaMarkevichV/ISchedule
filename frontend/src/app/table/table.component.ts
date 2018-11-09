import {Component, OnDestroy, OnInit} from "@angular/core";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TableModel} from "../model/TableModel";
import {TableModelService} from "../service/table-model.service";
import {Subscription} from "rxjs";
import {Faculty} from "../model/faculty";
import {Group} from "../model/group";
import {ProfessorAccount} from "../model/professor-account";
import {StudentAccount} from "../model/student-account";

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit, OnDestroy {

  private subscriptions: Subscription[] = [];

  public tableModel: TableModel;

  constructor(
    private loadingService: Ng4LoadingSpinnerService,
    private tableModelService: TableModelService) {

    this.tableModel = new TableModel();
  }

  ngOnInit() {
    this.loadAllData();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  /*data update/upload*/
  public loadAllData() {
    this.loadFaculties();
    this.loadGroups();
    this.loadProfessorAccounts();
    this.loadStudentAccounts();
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

  public loadProfessorAccounts(): void {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.getProfessorAccounts().subscribe(accounts => {
      this.tableModel.professors = accounts as ProfessorAccount[];
      this.loadingService.hide();
    }));
  }

  public loadStudentAccounts(): void {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.getStudentAccounts().subscribe(accounts => {
      this.tableModel.students = accounts as StudentAccount[];
      // consoLe.log(this.studentAccounts);
      this.loadingService.hide();
    }));
  }
}

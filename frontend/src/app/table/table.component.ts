import {Component, OnDestroy, OnInit, ViewChild} from "@angular/core";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TableModel} from "../model/TableModel";
import {TableModelService} from "../service/table-model.service";
import {Subscription} from "rxjs";
import {Faculty} from "../model/faculty";
import {Group} from "../model/group";
import {ProfessorAccount} from "../model/professor-account";
import {StudentAccount} from "../model/student-account";
import {StudentComponent} from "./student/student.component";
import {ProfessorComponent} from "./professor/professor.component";
import {GroupComponent} from "./group/group.component";

@Component({
  selector: 'table-component',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit, OnDestroy {

  @ViewChild(StudentComponent)
  protected studentComponent: StudentComponent;

  @ViewChild(ProfessorComponent)
  protected professorComponent: ProfessorComponent;

  @ViewChild(GroupComponent)
  protected groupComponent: GroupComponent;

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
    this.groupComponent.loadGroups();
    this.professorComponent.updateProfessorAccounts();
    this.studentComponent.updateStudentAccounts();
  }

  public loadFaculties(): void {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.getFaculties().subscribe(faculties => {
      this.tableModel.faculties = faculties as Faculty[];
      this.loadingService.hide();
    }))
  }
}

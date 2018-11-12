import {Component, Input, OnDestroy, OnInit, Output, ViewChild} from '@angular/core';
import {TableModel} from "../../model/TableModel";
import {Subscription} from "rxjs";
import {ProfessorTabComponent} from "./professor-tab/professor-tab.component";
import {GroupTabComponent} from "./group-tab/group-tab.component";
import {StudentTabComponent} from "./student-tab/student-tab.component";

@Component({
  selector: 'administrator',
  templateUrl: './administrator.component.html',
  styleUrls: ['./administrator.component.css']
})
export class AdministratorComponent implements OnInit, OnDestroy {
  @ViewChild(StudentTabComponent)
  protected studentComponent: StudentTabComponent;

  @ViewChild(ProfessorTabComponent)
  protected professorComponent: ProfessorTabComponent;

  @ViewChild(GroupTabComponent)
  protected groupComponent: GroupTabComponent;

  private subscriptions: Subscription[] = [];

  @Input()
  public tableModel: TableModel;

  constructor() {
  }

  ngOnInit() {
    this.loadAllData();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  /*data update/upload*/
  public loadAllData() {
    this.groupComponent.loadGroups();
    this.professorComponent.updateProfessorAccounts();
    this.studentComponent.updateStudentAccounts();
  }
}

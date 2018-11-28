import {Component, EventEmitter, Input, OnDestroy, OnInit, Output, ViewChild} from '@angular/core';
import {TableModel} from "../../model/TableModel";
import {ProfessorTabComponent} from "./professor-tab/professor-tab.component";
import {GroupTabComponent} from "./group-tab/group-tab.component";
import {StudentTabComponent} from "./student-tab/student-tab.component";

@Component({
  selector: 'administrator',
  templateUrl: './administrator.component.html',
  styleUrls: ['./administrator.component.css']
})
export class AdministratorComponent {

  @ViewChild(StudentTabComponent)
  protected studentComponent: StudentTabComponent;

  @ViewChild(ProfessorTabComponent)
  protected professorComponent: ProfessorTabComponent;

  @ViewChild(GroupTabComponent)
  protected groupComponent: GroupTabComponent;

  @Input()
  public tableModel: TableModel;

  @Output()
  loadFaculties: EventEmitter<any> = new EventEmitter<any>();

  @Output()
  loadGroups: EventEmitter<any> = new EventEmitter<any>();

  @Output()
  loadProfessors: EventEmitter<any> = new EventEmitter<any>();

  @Output()
  loadStudents: EventEmitter<any> = new EventEmitter<any>();

  @Output()
  loadLessons: EventEmitter<any> = new EventEmitter<any>();

  constructor() {
  }
}

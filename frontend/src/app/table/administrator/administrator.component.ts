import {Component, EventEmitter, Input, OnDestroy, OnInit, Output, ViewChild} from '@angular/core';
import {TableModel} from "../../model/TableModel";
import {ProfessorTabComponent} from "./professor-tab/professor-tab.component";
import {GroupTabComponent} from "./group-tab/group-tab.component";
import {StudentTabComponent} from "./student-tab/student-tab.component";
import {ScheduleTabComponent} from "./schedule-tab/schedule-tab.component";
import {LessonTabComponent} from "./lesson-tab/lesson-tab.component";

@Component({
  selector: 'administrator',
  templateUrl: './administrator.component.html',
  styleUrls: ['./administrator.component.css']
})
export class AdministratorComponent {

  @ViewChild(LessonTabComponent)
  public lessonComponent: LessonTabComponent;

  @ViewChild(StudentTabComponent)
  public studentComponent: StudentTabComponent;

  @ViewChild(ProfessorTabComponent)
  public professorComponent: ProfessorTabComponent;

  @ViewChild(GroupTabComponent)
  public groupComponent: GroupTabComponent;

  @ViewChild(ScheduleTabComponent)
  public scheduleComponent: ScheduleTabComponent;

  @Input()
  public tableModel: TableModel;

  @Output()
  loadFaculties: EventEmitter<any> = new EventEmitter<any>();

  @Output()
  loadGroups: EventEmitter<any> = new EventEmitter<any>();

  @Output()
  loadProfessors: EventEmitter<any> = new EventEmitter<any>();

  constructor() {
  }
}

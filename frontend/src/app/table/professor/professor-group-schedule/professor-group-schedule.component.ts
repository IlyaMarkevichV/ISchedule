import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {TableModel} from "../../../model/TableModel";
import {TableModelService} from "../../../service/table-model.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Subscription} from "rxjs";
import {DaysOfWeek} from "../../../model/DaysOfWeek";
import {Lesson} from "../../../model/lesson";
import {AuthorizationService} from "../../../service/authorization.service";
import {Group} from "../../../model/group";

@Component({
  selector: 'professor-group-schedule',
  templateUrl: './professor-group-schedule.component.html',
  styleUrls: ['./professor-group-schedule.component.css']
})
export class ProfessorGroupScheduleComponent implements OnInit, OnDestroy {

  @Input()
  public tableModel: TableModel;

  private subscriptions: Subscription[] = [];
  public professorMap: Map<number, DaysOfWeek<Lesson>>;
  public isGroupsScheduleCollapsed: boolean = false;
  public professorGroups: Group[] = [];

  constructor(
    private loadingService: Ng4LoadingSpinnerService,
    private tableModelService: TableModelService,
    private authService: AuthorizationService) { }

  ngOnInit() {
    this.professorMap = new Map<number, DaysOfWeek<Lesson>>();
    this.tableModel.groups.forEach(group => this.professorMap.set(group.id, new DaysOfWeek<Lesson>()));
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  getGroupSchedule(groupId: number) {
   this.loadingService.show();
    if (!this.professorMap.has(groupId)) {
      this.professorMap.set(groupId, new DaysOfWeek<Lesson>());
    }

    this.subscriptions.push(
      this.tableModelService.getGroupLessons(groupId, new Date()).subscribe(req => {
        console.log(req as Lesson[]);
        let tempDays: DaysOfWeek<Lesson> = DaysOfWeek.transformLessonsToDaysOfWeek(req as Lesson[]);
        console.log(tempDays);
        this.professorMap.set(groupId, tempDays);
        this.loadingService.hide();
      }));
  }
}

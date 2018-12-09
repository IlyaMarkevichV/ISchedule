import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {TableModel} from "../../../model/TableModel";
import {TableModelService} from "../../../service/table-model.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Subscription} from "rxjs";
import {DaysOfWeek} from "../../../model/DaysOfWeek";
import {Lesson} from "../../../model/lesson";

@Component({
  selector: 'professor-group-schedule',
  templateUrl: './professor-group-schedule.component.html',
  styleUrls: ['./professor-group-schedule.component.css']
})
export class ProfessorGroupScheduleComponent implements OnInit, OnDestroy {

  @Input()
  public tableModel: TableModel;

  private subscriptions: Subscription[] = [];
  public daysMap: Map<number, DaysOfWeek<Lesson>>;

  constructor(
    private loadingService: Ng4LoadingSpinnerService,
    private tableModelService: TableModelService) { }

  ngOnInit() {
    this.daysMap = new Map<number, DaysOfWeek<Lesson>>();
    this.tableModel.groups.forEach(group => this.daysMap.set(group.id, new DaysOfWeek<Lesson>()));
    console.log('============================================', new DaysOfWeek<Lesson>().monday);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  getGroupSchedule(groupId: number) {
   this.loadingService.show();
    if (!this.daysMap.has(groupId)) {
      this.daysMap.set(groupId, new DaysOfWeek<Lesson>());
    }

    this.subscriptions.push(
      this.tableModelService.getGroupLessons(groupId, new Date()).subscribe(req => {
        let tempDays: DaysOfWeek<Lesson> = DaysOfWeek.transformLessonToDaysOfWeek(req as Lesson[]);
        this.daysMap.set(groupId, tempDays);
        this.loadingService.hide();
      }));
  }
}

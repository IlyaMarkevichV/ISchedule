import {Component, Input, OnInit} from '@angular/core';
import {Lesson} from "../../model/lesson";

@Component({
  selector: 'mini-schedule-table',
  templateUrl: './mini-schedule-table.component.html',
  styleUrls: ['./mini-schedule-table.component.css']
})
export class MiniScheduleTableComponent implements OnInit {

  @Input()
  public lessons: Lesson[];

  @Input()
  public dayName: string;

  constructor() { }

  ngOnInit() {
  }

}

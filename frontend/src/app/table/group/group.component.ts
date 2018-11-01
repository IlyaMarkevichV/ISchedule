import {Component, EventEmitter, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent implements OnInit {

  @Input() tab: string = 'nothing';

  closeOthers: boolean = true;
  groups: any;

  constructor() { }

  ngOnInit() {
  }

}

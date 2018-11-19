import {Component, OnChanges, Output, SimpleChanges, ViewChild} from '@angular/core';
import {TableComponent} from "./table/table.component";
import {UserAccount} from "./model/UserAccount";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent{
  public seeTables: boolean = false;
}

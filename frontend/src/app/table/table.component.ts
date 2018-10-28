import {Component, OnInit, TemplateRef} from "@angular/core";
import {StudentAccount} from "../model/student-account";
import {StudentAccountService} from "../service/student-account.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TabDirective} from "ngx-bootstrap/tabs";
import {Subscription} from "rxjs";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import * as $ from 'jquery';
import {AppComponent} from "../app.component";

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  // TODO: delete person
  public person: string;

  // Dependency injection
  constructor() {
  }

  ngOnInit() {
  }

  onSelect(data: TabDirective) {
    this.person = data.heading;
  }
}

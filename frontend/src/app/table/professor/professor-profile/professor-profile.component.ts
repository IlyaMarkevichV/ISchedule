import {Component, Input, OnInit} from '@angular/core';
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TableModelService} from "../../../service/table-model.service";
import {BsModalService} from "ngx-bootstrap";
import {DatePipe} from "@angular/common";
import {ProfessorAccount} from "../../../model/professor-account";

@Component({
  selector: 'professor-profile',
  templateUrl: './professor-profile.component.html',
  styleUrls: ['./professor-profile.component.css']
})
export class ProfessorProfileComponent implements OnInit {

  @Input()
  public professor: ProfessorAccount;

  constructor(private loadingService: Ng4LoadingSpinnerService,
              private tableModelService: TableModelService,
              private modalService: BsModalService,
              private datePipe: DatePipe) { }

  ngOnInit() {
  }

}

import {Component, EventEmitter, Input, OnDestroy, OnInit, Output, ViewChild} from "@angular/core";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TableModel} from "../model/TableModel";
import {TableModelService} from "../service/table-model.service";
import {Subscription} from "rxjs";
import {Faculty} from "../model/faculty";
import {Group} from "../model/group";
import {UserAccount} from "../model/UserAccount";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'table-component',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit, OnDestroy {

  public logginedUserAccount: UserAccount;

  private subscriptions: Subscription[] = [];

  public tableModel: TableModel;

  constructor(
    private loadingService: Ng4LoadingSpinnerService,
    private tableModelService: TableModelService,
     private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.logginedUserAccount = new UserAccount();

    this.route.params.subscribe(params => {
      this.logginedUserAccount.accountId = params['id'];
      this.subscriptions.push(this.tableModelService.getAccountById(this.logginedUserAccount.accountId).subscribe( acc => {
        this.logginedUserAccount = acc as UserAccount;
      }));
    });

    this.tableModel = new TableModel();

    this.loadFaculties();
    this.loadGroups();
    this.loadProfessors();
    this.loadStudents();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  public loadFaculties(): void {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.getFaculties().subscribe(faculties => {
      this.tableModel.faculties = faculties as Faculty[];
      this.loadingService.hide();
    }))
  }

  public loadGroups(): void {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.getGroups().subscribe(gr => {
      this.tableModel.groups = gr as Group[];
      this.loadingService.hide();
    }));
  }

  public loadStudents(): void {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.getStudents().subscribe(accounts => {
      this.tableModel.students = accounts as UserAccount[];
      this.loadingService.hide();
    }));
  }

  public loadProfessors(): void {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.getProfessors().subscribe(accounts => {
      this.tableModel.professors = accounts as UserAccount[];
      this.loadingService.hide();
    }));
  }
}

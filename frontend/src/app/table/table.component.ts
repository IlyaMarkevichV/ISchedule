import {Component, OnInit, TemplateRef} from "@angular/core";
import {StudentAccount} from "../model/student-account";
import {StudentAccountService} from "../service/student-account.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TabDirective} from "ngx-bootstrap/tabs";
import {Subscription} from "rxjs";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import * as $ from 'jquery';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  // TODO: delete value
  public value: string;
  public studentAccounts: StudentAccount[];
  private modalRef: BsModalRef;

  public editMode: boolean = false;

  public editableStudent: StudentAccount = new StudentAccount();

  private subscriptions: Subscription[] = [];

  // Dependency injection
  constructor(private loadingService: Ng4LoadingSpinnerService,
              private studentAccountService: StudentAccountService,
              private modalService: BsModalService) {
  }

  ngOnInit() {
    this.loadStudentAccounts();
  }

  public filterStudents() {
    $(document).ready(function () {
      $("#searchStr").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#tableBody tr").filter(function () {
          $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
      });
    });
  }

  onSelect(data: TabDirective) {
    this.value = data.heading;
  }

  private loadStudentAccounts(): void {
    this.loadingService.show();
    // Get data from StudentAccountService
    this.subscriptions.push(this.studentAccountService.getStudentAccounts().subscribe(accounts => {
      // Parse json responce into local array
      this.studentAccounts = accounts as StudentAccount[];
      // Check data in console
      // consoLe.log(this.studentAccounts);
      console.log(this.studentAccounts);
      this.loadingService.hide();
    }));
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  openModal(template: TemplateRef<any>, studentAccount?: StudentAccount) {
    if (studentAccount) {
      this.editableStudent = StudentAccount.cloneStudentAccount(studentAccount);
      this.editMode = true;
    } else {
      this.refreshEditableStudent();
      this.editMode = false;
    }
    this.modalRef = this.modalService.show(template);
  }

  public closeModal(): void {
    this.modalRef.hide();
  }

  public updateStudentAccounts(): void {
    this.loadStudentAccounts();
  }

  public refreshEditableStudent() {
    this.editableStudent = new StudentAccount();
  }

  public addStudentAccount(): void {
    this.loadingService.show();
    this.subscriptions.push(this.studentAccountService.saveStudentAccount(this.editableStudent).subscribe(() => {
      this.updateStudentAccounts();
      this.closeModal();
      this.loadingService.hide();
      this.refreshEditableStudent();
    }));
  }

  public deleteStudentAccount(studentAccountId: string): void {
    this.loadingService.show();
    this.subscriptions.push(this.studentAccountService.deleteStudentAccount(studentAccountId).subscribe(() => {
       this.updateStudentAccounts();
    }));
  }
}

import {Component, OnInit, TemplateRef, OnDestroy} from '@angular/core';
import {StudentAccount} from "../../model/student-account";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Subscription} from "rxjs";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {StudentAccountService} from "../../service/student-account.service";

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit, OnDestroy {

  public searchButton: string = 'Search by';
  public searchText: string;
  public tab: string;

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

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  private loadStudentAccounts(): void {
    this.loadingService.show();
    // Get data from StudentAccountService
    this.subscriptions.push(this.studentAccountService.getStudentAccounts().subscribe(accounts => {
      // Parse json responce into local array
      this.studentAccounts = accounts as StudentAccount[];
      // Check data in console
      // consoLe.log(this.studentAccounts);
      this.loadingService.hide();
    }));
  }

  openModal(template: TemplateRef<any>, studentAccount?: StudentAccount): void {
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

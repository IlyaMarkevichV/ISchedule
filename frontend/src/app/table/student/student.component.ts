import {Component, OnInit, TemplateRef, OnDestroy} from '@angular/core';
import {StudentAccount} from "../../model/student-account";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Subscription} from "rxjs";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {StudentAccountService} from "../../service/student-account.service";
import {FormControl, FormGroup} from '@angular/forms';
import {Group} from "../../model/group";
import {GroupService} from "../../service/group.service";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit, OnDestroy {

  public searchButtonName: string = 'Search by';
  public searchText: string;
  public studentField: string;

  public tempStudentForFilter: StudentAccount = new StudentAccount();

  public studentAccounts: StudentAccount[];
  private modalRef: BsModalRef;

  public editMode: boolean = false;
  public isNotCorrect: boolean = false;

  public editableStudent: StudentAccount = new StudentAccount();

  private subscriptions: Subscription[] = [];

  // Dependency injection
  constructor(private loadingService: Ng4LoadingSpinnerService,
              private studentAccountService: StudentAccountService,
              private groupService: GroupService,
              private modalService: BsModalService,
              private datePipe: DatePipe) {
  }

  ngOnInit() {
    this.loadStudentAccounts();
    this.tempStudentForFilter.group = new Group();
    this.editableStudent.group = new Group();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  public searchTrigger() {
    this.tempStudentForFilter = new StudentAccount();
    this.tempStudentForFilter.group = new Group();
    switch (this.studentField) {
      case 'firstName':
        this.tempStudentForFilter.firstName = this.searchText;
        break;
      case 'lastName':
        this.tempStudentForFilter.lastName = this.searchText;
        break;
      case 'birthday':
        this.tempStudentForFilter.birthday = this.searchText;
        break;
      case 'groupId':
        if (this.searchText !== '')
          this.tempStudentForFilter.group.groupId = Number(this.searchText);
        break;
      case 'studentId':
        if (this.searchText !== '')
          this.tempStudentForFilter.studentId = Number(this.searchText);
        break;
      case 'address':
        this.tempStudentForFilter.address = this.searchText;
        break;
      case 'email':
        this.tempStudentForFilter.email = this.searchText;
        break;
    }
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
    this.editableStudent.group = new Group();
  }

  public addStudentAccount(): void {
    this.loadingService.show();
    this.subscriptions.push(this.groupService.getGroupById(this.editableStudent.group.groupId).subscribe(gr => {
      console.log('-----------------------' + gr + '----------------1-------------------------');
      if (gr) {
        this.editableStudent.group = gr as Group;
      } else {
        this.loadingService.hide();
        this.isNotCorrect = false;
        return;
      }

      if (this.editableStudent.group !== null)
        this.editableStudent.birthday = this.datePipe.transform(this.editableStudent.birthday, 'yyyy-MM-dd');
        this.subscriptions.push(this.studentAccountService.saveStudentAccount(this.editableStudent).subscribe(() => {
          this.updateStudentAccounts();
          this.closeModal();
          this.loadingService.hide();
          this.refreshEditableStudent();
        }));

    }));
  }

  public deleteStudentAccount(studentAccountId: string): void {
    this.loadingService.show();
    this.subscriptions.push(this.studentAccountService.deleteStudentAccount(studentAccountId).subscribe(() => {
      this.updateStudentAccounts();
    }));
  }

}

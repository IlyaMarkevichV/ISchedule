import {Component, OnInit, TemplateRef, OnDestroy, Input, Inject, forwardRef} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Subscription} from "rxjs";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {DatePipe} from "@angular/common";
import {StudentAccount} from "../../../model/student-account";
import {Group} from "../../../model/group";
import {TableModelService} from "../../../service/table-model.service";
import {TableModel} from "../../../model/TableModel";


@Component({
  selector: 'student-tab',
  templateUrl: './student-tab.component.html',
  styleUrls: ['./student-tab.component.css']
})
export class StudentTabComponent implements OnInit, OnDestroy {

  @Input()
  public tableModel: TableModel;

  /*info for pagination*/
  page: number = 1;
  totalNumberOfEntities: number;

  public searchButtonName: string = 'Search by';
  public searchText: string;

  /* needed for filterBy*/
  public studentField: string;
  public tempStudentForFilter: StudentAccount = new StudentAccount();

  private modalRef: BsModalRef;

  public editMode: boolean = false;
  public editableStudent: StudentAccount = new StudentAccount();

  private subscriptions: Subscription[] = [];

  public tempGroupId: number;

  constructor(private loadingService: Ng4LoadingSpinnerService,
              private tableModelService: TableModelService,
              private modalService: BsModalService,
              private datePipe: DatePipe) {
  }

  ngOnInit() {
    this.tempStudentForFilter.group = new Group();
    this.editableStudent.group = new Group();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  openModal(template: TemplateRef<any>, studentAccount?: StudentAccount): void {
    if (studentAccount) {
      this.editableStudent = StudentAccount.cloneStudentAccount(studentAccount);
      this.editMode = true;
      this.tempGroupId = this.editableStudent.group.groupId;
    } else {
      this.refreshEditableStudent();
      this.editMode = false;
      this.tempGroupId = this.tableModel.groups.length != 0 ? this.tableModel.groups[0].groupId : 0;
    }
    this.modalRef = this.modalService.show(template);
  }

  public closeModal(): void {
    this.modalRef.hide();
  }

  public updateStudentAccounts(): void {
    this.loadStudentAccounts();
    this.page = 1;
  }

  public refreshEditableStudent() {
    this.editableStudent = new StudentAccount();
    this.editableStudent.group = new Group();
  }

  public loadStudentAccounts(): void {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.getStudentAccounts().subscribe(accounts => {
      this.tableModel.students = accounts as StudentAccount[];
      // consoLe.log(this.studentAccounts);
      this.totalNumberOfEntities = this.tableModel.students.length;
      this.loadingService.hide();
    }));
  }

  public addStudentAccount(): void {
    this.loadingService.show();

    if (this.editableStudent.group !== null)
      this.editableStudent.birthday = this.datePipe.transform(this.editableStudent.birthday, 'yyyy-MM-dd');

    /*add group to student-tab according to chosen id*/
    for (let group of this.tableModel.groups){
      if (group.groupId == this.tempGroupId) {
        this.editableStudent.group = group;
        break;
      }
    }

    this.subscriptions.push(this.tableModelService.saveStudentAccount(this.editableStudent).subscribe(() => {
      this.updateStudentAccounts();
      this.closeModal();
      this.loadingService.hide();
      this.refreshEditableStudent();
    }));
  }

  public deleteStudentAccount(studentAccountId: string): void {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.deleteStudentAccount(studentAccountId).subscribe(() => {
      /*refresh all stored data in tableModel in case when we can delete parent node */
      this.updateStudentAccounts();
    }));
  }

  public searchTrigger(): void {
    if (this.searchButtonName === 'Search by')
      return;

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
}


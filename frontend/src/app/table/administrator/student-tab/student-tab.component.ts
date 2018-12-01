import {
  Component,
  OnInit,
  TemplateRef,
  OnDestroy,
  Input,
  EventEmitter,
  Output
} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Subscription} from "rxjs";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {DatePipe} from "@angular/common";
import {StudentAccount} from "../../../model/student-account";
import {Group} from "../../../model/group";
import {TableModelService} from "../../../service/table-model.service";
import {TableModel} from "../../../model/TableModel";
import {Role} from "../../../model/role";
import {UserAccount} from "../../../model/UserAccount";


@Component({
  selector: 'student-tab',
  templateUrl: './student-tab.component.html',
  styleUrls: ['./student-tab.component.css']
})
export class StudentTabComponent implements OnInit, OnDestroy {

  @Input()
  public tableModel: TableModel;

  @Output()
  loadStudents: EventEmitter<any> = new EventEmitter<any>();

  /*info for pagination*/
  page: number = 1;
  totalNumberOfEntities: number;

  public searchButtonName: string = 'Search by';
  public searchText: string;

  private studentRole: Role;

  /* needed for filterBy*/
  public studentField: string;
  public tempStudentForFilter: StudentAccount;

  private modalRef: BsModalRef;

  public editMode: boolean = false;
  public editableStudent: StudentAccount;

  private subscriptions: Subscription[] = [];

  constructor(private loadingService: Ng4LoadingSpinnerService,
              private tableModelService: TableModelService,
              private modalService: BsModalService,
              private datePipe: DatePipe) {
  }

  ngOnInit() {
    this.tempStudentForFilter = new StudentAccount();
    this.tempStudentForFilter.account = new UserAccount();
    this.tempStudentForFilter.group = new Group();

    this.editableStudent = new StudentAccount();
    this.editableStudent.account = new UserAccount();
    this.editableStudent.group = new Group();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  openModal(template: TemplateRef<any>, studentAccount?: StudentAccount): void {
    if (studentAccount) {
      this.editableStudent = StudentAccount.cloneStudentAccount(studentAccount);
      this.editMode = true;
    } else {
      this.refreshEditableStudent();
      this.editMode = false;
      this.editableStudent.group = this.tableModel.groups.length != 0 ? this.tableModel.groups[0] : null;
    }
    this.modalRef = this.modalService.show(template);
  }

  compareFn(obj1: any, obj2: any): boolean {
    return obj1 && obj2 ? obj1.id === obj2.id : obj1 === obj2;
  }

  public closeModal(): void {
    this.modalRef.hide();
  }

  public updateStudents(): void {
    this.loadStudents.emit();
    this.page = 1;
    this.totalNumberOfEntities = this.tableModel.students.length;
  }

  public refreshEditableStudent() {
    this.editableStudent = new StudentAccount();
    this.editableStudent.account = new UserAccount();
    this.editableStudent.group = new Group();

    if (!this.studentRole) {
      for (let role of this.tableModel.roles) {
        if (role.roleName === 'student') {
          this.studentRole = role;
          break;
        }
      }
    }
    this.editableStudent.account.role = this.studentRole;
  }

  public addStudentAccount(): void {
    this.loadingService.show();

    /*    if (this.editableStudent.studentProfessor.group !== null)*/
    this.editableStudent.account.birthday =
      this.datePipe.transform(this.editableStudent.account.birthday, 'yyyy-MM-dd');

    this.subscriptions.push(this.tableModelService.saveStudent(this.editableStudent).subscribe(() => {
      this.updateStudents();
      this.closeModal();
      this.refreshEditableStudent();
      this.loadingService.hide()
    }));
  }

  public deleteStudentAccount(student: StudentAccount): void {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.deleteStudent(student).subscribe(() => {
      this.updateStudents();
    }));
  }

  public searchTrigger(): void {
    if (this.searchButtonName === 'Search by')
      return;

    this.tempStudentForFilter = new StudentAccount();
    this.tempStudentForFilter.account = new UserAccount();
    this.tempStudentForFilter.group = new Group();
    switch (this.studentField) {
      case 'firstName':
        this.tempStudentForFilter.account.firstName = this.searchText;
        break;
      case 'lastName':
        this.tempStudentForFilter.account.lastName = this.searchText;
        break;
      case 'birthday':
        this.tempStudentForFilter.account.birthday = this.searchText;
        break;
      case 'groupId':
        if (this.searchText !== '')
          this.tempStudentForFilter.group.id = Number(this.searchText);
        break;
    }
  }
}


import {
  Component,
  OnInit,
  TemplateRef,
  OnDestroy,
  Input
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
import {Constants} from "../../../share/constants";
import {Page} from "../../../model/page";
import {RequestHelper} from "../../../model/RequestHelper";


@Component({
  selector: 'student-tab',
  templateUrl: './student-tab.component.html',
  styleUrls: ['./student-tab.component.css']
})
export class StudentTabComponent implements OnInit, OnDestroy {

  @Input()
  public tableModel: TableModel;

  private studentRole: Role;

  public tempStudentForFilter: StudentAccount;

  private modalRef: BsModalRef;

  public editMode: boolean = false;
  public editableStudent: StudentAccount;

  private subscriptions: Subscription[] = [];

  public studentPage: Page<StudentAccount>;
  public itemsPerPage: number = Constants.NUMBER_OF_ROWS_ON_ONE_PAGE;
  public sortDirection: boolean = false;

  public maxDate: Date;
  public minDate: Date;

  public wrongLogin: boolean = false;

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

    this.studentPage = new Page<StudentAccount>();
    this.getPage(1);

    this.maxDate = new Date();
    this.maxDate.setFullYear(this.maxDate.getFullYear() - 15, 0, 1);
    this.minDate = new Date();
    this.minDate.setFullYear(this.maxDate.getFullYear() - 30, 0, 1);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  openModal(template: TemplateRef<any>, studentAccount?: StudentAccount): void {
    if (studentAccount) {
      this.editableStudent = StudentAccount.cloneStudentAccount(studentAccount);
      this.editMode = true;
      this.editableStudent.account.password = null;
    } else {
      this.refreshEditableStudent();
      this.editMode = false;
      this.editableStudent.group = this.tableModel.groups.length != 0 ? this.tableModel.groups[0] : null;
    }
    this.modalRef = this.modalService.show(template);
  }

  public closeModal(): void {
    this.modalRef.hide();
  }

  public updateStudents(): void {
    this.getPage(1);
  }

  public refreshEditableStudent() {
    this.editableStudent = new StudentAccount();
    this.editableStudent.account = new UserAccount();
    this.editableStudent.group = new Group();

    if (!this.studentRole) {
      for (let role of this.tableModel.roles) {
        if (role.roleName === 'STUDENT') {
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

    this.subscriptions.push(this.tableModelService.saveStudent(this.editableStudent).subscribe(student => {
      if (student && student.id == -1 && student.account.id == -1) {
        this.wrongLogin = true;
      } else {
        this.wrongLogin = false;
        this.updateStudents();
        this.closeModal();
        this.refreshEditableStudent();
      }
      this.loadingService.hide()
    }));
  }

  public deleteStudentAccount(student: StudentAccount): void {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.deleteStudent(student).subscribe(() => {
      this.updateStudents();
    }));
  }

  getPage(pageNumber: number) {
    this.loadingService.show();
    console.log(pageNumber);
    console.log('id,' + (this.sortDirection ? 'desc' : 'asc'));
    this.subscriptions.push(this.tableModelService.getPageObservable<StudentAccount>(
      RequestHelper.STUDENT,
      pageNumber - 1,
      this.itemsPerPage,
      'id,' + (this.sortDirection ? 'desc' : 'asc'))
      .subscribe(req => {
        this.studentPage = req as Page<StudentAccount>;
        this.studentPage.number += 1;
        this.loadingService.hide();
      }));
  }

  compareFn(obj1: any, obj2: any): boolean {
    return obj1 && obj2 ? obj1.id === obj2.id : obj1 === obj2;
  }
}


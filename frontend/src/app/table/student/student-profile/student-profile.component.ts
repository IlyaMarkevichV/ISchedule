import {Component, Input, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import {TableModel} from "../../../model/TableModel";
import {ProfessorAccount} from "../../../model/professor-account";
import {StudentAccount} from "../../../model/student-account";
import {Subscription} from "rxjs";
import {TableModelService} from "../../../service/table-model.service";
import {TokenStorage} from "../../../service/token-storage.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {AuthorizationAndTransmitService} from "../../../service/authorization-and-transmit.service";
import {Lesson} from "../../../model/lesson";
import {DaysOfWeek} from "../../../model/DaysOfWeek";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {DatePipe} from "@angular/common";
import {Group} from "../../../model/group";
import {UserAccount} from "../../../model/UserAccount";

@Component({
  selector: 'student-profile',
  templateUrl: './student-profile.component.html',
  styleUrls: ['./student-profile.component.css']
})
export class StudentProfileComponent implements OnInit, OnDestroy {

  @Input()
  public student: StudentAccount;

  private subscriptions: Subscription[] = [];
  public days: DaysOfWeek<Lesson>;

  public currentPassword: string;
  public password: string;
  public wrongPassword: boolean = false;
  private modalRef: BsModalRef;

  constructor(private loadingService: Ng4LoadingSpinnerService,
              private tableModelService: TableModelService,
              private modalService: BsModalService,
              private datePipe: DatePipe,
              private authService: AuthorizationAndTransmitService) {
  }

  ngOnInit() {
    this.loadingService.show();
    this.days = new DaysOfWeek<Lesson>();

    this.subscriptions.push(this.authService.currentStudentLessons.subscribe(req => {
      this.days = DaysOfWeek.transformLessonsToDaysOfWeek(req);
      console.log(this.days);
      this.loadingService.hide();
    }));
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
  openModal(template: TemplateRef<any>, group?: Group): void {
    this.currentPassword = null;
    this.password = null;
    this.modalRef = this.modalService.show(template);
  }

  public closeModal(): void {
    this.modalRef.hide();
  }

  changePass(): void {
    this.loadingService.show();
    let acc: UserAccount = new UserAccount();
    acc.login = this.student.account.login;
    acc.password = this.currentPassword;
    this.subscriptions.push(this.tableModelService.validatePass(acc).subscribe(num => {
      if (num == 0) {
        this.wrongPassword = true;
        this.loadingService.hide();
        return;
      }
      this.wrongPassword = false;
      this.student.account.password = this.password;
      this.subscriptions.push(this.tableModelService.saveStudent(this.student).subscribe(p => {
        this.student = p;
        this.closeModal();
        this.loadingService.hide();
      }))
    }));
  }
}

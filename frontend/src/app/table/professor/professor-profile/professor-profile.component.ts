import {Component, Input, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TableModelService} from "../../../service/table-model.service";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {DatePipe} from "@angular/common";
import {ProfessorAccount} from "../../../model/professor-account";
import {DaysOfWeek} from "../../../model/DaysOfWeek";
import {Lesson} from "../../../model/lesson";
import {Subscription} from "rxjs";
import {AuthorizationAndTransmitService} from "../../../service/authorization-and-transmit.service";
import {Group} from "../../../model/group";
import {UserAccount} from "../../../model/UserAccount";
import {a} from "@angular/core/src/render3";

@Component({
  selector: 'professor-profile',
  templateUrl: './professor-profile.component.html',
  styleUrls: ['./professor-profile.component.css']
})
export class ProfessorProfileComponent implements OnInit, OnDestroy {

  @Input()
  public professor: ProfessorAccount;

  private subscriptions: Subscription[] = [];
  public days: DaysOfWeek<Lesson>;
  public professorLessons: Array<string>;

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
    this.professorLessons = new Array<string>();

    this.subscriptions.push(this.authService.currentProfessorLessons.subscribe(req => {
      console.log("profile: ", req);
      let set: Set<string> = new Set<string>();
      req.forEach(lesson => set.add(lesson.lessonInfo.lessonName));
      this.professorLessons = Array.from(set);

      this.days = DaysOfWeek.transformLessonsToDaysOfWeek(req);
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
    acc.login = this.professor.account.login;
    acc.password = this.currentPassword;
    this.subscriptions.push(this.tableModelService.validatePass(acc).subscribe(num => {
      if (num == 0) {
        this.wrongPassword = true;
        this.loadingService.hide();
        return;
      }
      this.wrongPassword = false;
      this.professor.account.password = this.password;
      this.subscriptions.push(this.tableModelService.saveProfessor(this.professor).subscribe(p => {
        this.professor = p;
        this.closeModal();
        this.loadingService.hide();
      }))
    }));
  }
}

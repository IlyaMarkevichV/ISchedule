import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {TableModel} from "../../model/TableModel";
import {UserAccount} from "../../model/UserAccount";
import {ProfessorAccount} from "../../model/professor-account";
import {Subscription} from "rxjs";
import {TableModelService} from "../../service/table-model.service";
import {TokenStorage} from "../../service/token-storage.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {AuthorizationAndTransmitService} from "../../service/authorization-and-transmit.service";
import {Lesson} from "../../model/lesson";
import {StudentAccount} from "../../model/student-account";

@Component({
  selector: 'student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit, OnDestroy {

  @Input()
  public tableModel: TableModel;

  @Input()
  public student: StudentAccount;

  private subscriptions: Subscription[] = [];

  constructor(
    private tableModelService: TableModelService,
    private tokenStorage: TokenStorage,
    private loadingService: Ng4LoadingSpinnerService,
    private authService: AuthorizationAndTransmitService) {
  }

  ngOnInit() {
    this.loadingService.show();
    let jwtData = this.tokenStorage.getToken().split('.')[1];
    let decodedJwtJsonData = window.atob(jwtData);
    let decodedJwtData = JSON.parse(decodedJwtJsonData);

    this.subscriptions.push(
      this.tableModelService.getStudentByAccountLogin(decodedJwtData.sub).subscribe(request => {
        this.student = request as StudentAccount;
        this.authService.transmitStudent(this.student);

        //get schedule
        this.subscriptions.push(this.tableModelService.getGroupLessons(
          this.student.group.id, new Date()).subscribe(request => {
          this.authService.transmitStudentLessons(request as Lesson[]);
        }));

        this.loadingService.hide();
      }));
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}

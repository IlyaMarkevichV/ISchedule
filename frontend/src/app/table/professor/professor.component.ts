import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {TableModel} from "../../model/TableModel";
import {UserAccount} from "../../model/UserAccount";
import {ProfessorAccount} from "../../model/professor-account";
import {AuthorizationAndTransmitService} from "../../service/authorization-and-transmit.service";
import {TableModelService} from "../../service/table-model.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Subscription} from "rxjs";
import {TokenStorage} from "../../service/token-storage.service";
import {DaysOfWeek} from "../../model/DaysOfWeek";
import {Lesson} from "../../model/lesson";

@Component({
  selector: 'professor',
  templateUrl: './professor.component.html',
  styleUrls: ['./professor.component.css']
})
export class ProfessorComponent implements OnInit, OnDestroy {

  @Input()
  public tableModel: TableModel;

  @Input()
  public professor: ProfessorAccount;

  private subscriptions: Subscription[] = [];

  @Input()
  public t: boolean = false;


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
      this.tableModelService.getProfessorByAccountLogin(decodedJwtData.sub).subscribe(request => {
        this.professor = request as ProfessorAccount;
        this.authService.transmitProfessor(this.professor);

        //get schedule
        this.subscriptions.push(this.tableModelService.getProfessorLessons(
          this.professor.id, new Date()).subscribe(request => {
          this.authService.transmitProfessorLessons(request as Lesson[]);
          this.loadingService.hide();
        }));

      }));
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}

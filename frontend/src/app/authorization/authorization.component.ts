import {Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Router} from "@angular/router";

import {Subscription} from "rxjs";
import {UserAccount} from "../model/UserAccount";
import {TableModelService} from "../service/table-model.service";


@Component({
  selector: 'authorization',
  templateUrl: './authorization.component.html',
  styleUrls: ['./authorization.component.css']
})
export class AuthorizationComponent implements OnInit, OnDestroy {

  public alertUserAboutError: boolean = false;

  public login: string;
  public password: string;

  private subscriptions: Subscription[] = [];

  @Output()
  public authorizationInfoEvent: EventEmitter<UserAccount> = new EventEmitter<UserAccount>();

  private authorizationAccount: UserAccount;

  constructor(private loadingService: Ng4LoadingSpinnerService,
              private router: Router,
              private tableModelService: TableModelService) {
  }

  ngOnInit() {
    this.authorizationAccount = new UserAccount();
    this.authorizationAccount.role = 'administrator';
    this.authorizationInfoEvent.emit(this.authorizationAccount);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  public authorization(): void {

    this.loadingService.show();

    this.subscriptions.push(this.tableModelService.getUserByLoginAndPassword(this.login, this.password).subscribe(acc => {
      this.authorizationAccount = acc as UserAccount;

      this.authorizationInfoEvent.emit(this.authorizationAccount);
      if (!acc.login && !acc.password && !acc.role) {
        this.alertUserAboutError = false;
      } else {
        this.alertUserAboutError = true;

        switch (acc.role) {
          case "student":
            this.router.navigate(['/student/', acc.accountId]);
            break;
          case "professor":
            this.router.navigate(['/professor', acc.accountId]);
            break;
          case "administrator":
            this.router.navigate(['/administrator', acc.accountId]);
            break;
        }
      }
      this.loadingService.hide();
    }));
  }
}

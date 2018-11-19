import {Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Router} from "@angular/router";
import {TableModelService} from "../service/table-model.service";
import {UserAccount} from "../model/UserAccount";
import {Subscription} from "rxjs";

@Component({
  selector: 'authorization',
  templateUrl: './authorization.component.html',
  styleUrls: ['./authorization.component.css']
})
export class AuthorizationComponent implements OnInit, OnDestroy {

  @Output()
  public authorized: EventEmitter<boolean> = new EventEmitter<boolean>();
  public alertUser: boolean = false;

  public login: string;
  public password: string;

  private subscriptions: Subscription[] = [];

  private authorizationAccount: UserAccount;

  constructor(private loadingService: Ng4LoadingSpinnerService,
              private router: Router,
              private tableModelService: TableModelService) {
  }

  ngOnInit() {
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  /*  public loginFunction(): void {
      this.loadingService.show();
      setTimeout(() => {
          this.authorized.emit(true);
          this.user = "administrator";
          this.router.navigate(['administrator'])
        }
        , 1500);
      this.loadingService.hide();
    }*/

  public authorization(): void {
    console.log('auth method');
    this.loadingService.show();

    this.subscriptions.push(this.tableModelService.getUserByLoginAndPassword(this.login, this.password).subscribe(acc => {
      this.authorizationAccount = acc as UserAccount;

      console.log(this.authorizationAccount);
      if (!acc.login && !acc.password && !acc.role) {
        this.alertUser = true;
      } else {
        this.alertUser = false;
        switch (acc.role) {
          case "student":
            this.router.navigate(['student']);
            break;
          case "professor":
            this.router.navigate(['professor']);
            break;
          case "administrator":
            this.router.navigate(['administrator']);
            break;
        }
      }
      this.loadingService.hide();
    }));
  }

}

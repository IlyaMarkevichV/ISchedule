import {Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Router} from "@angular/router";

import {Subscription} from "rxjs";
import {UserAccount} from "../model/UserAccount";
import {TableModelService} from "../service/table-model.service";
import {Constants} from "../share/constants";
import {Token} from "../model/token";
import {TokenStorage} from "../service/token-storage.service";



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
  private authorizationAccount: UserAccount;

  constructor(private loadingService: Ng4LoadingSpinnerService,
              private router: Router,
              private tableModelService: TableModelService,
              private tokeStorage: TokenStorage) {
  }

  ngOnInit() {
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  public authorization(): void {

    this.loadingService.show();

    this.subscriptions.push(this.tableModelService.attemptAuth(this.login, this.password).subscribe(authToken => {
      let token: Token = authToken as Token;
      this.tokeStorage.saveToken(token.token);
      let decodedJwtJsonData = window.atob(token.token);
      this.authorizationAccount = JSON.parse(decodedJwtJsonData);
      console.log(this.authorizationAccount);

      if (!this.authorizationAccount) {
        this.alertUserAboutError = false;
      } else {
        this.alertUserAboutError = true;

        switch (this.authorizationAccount.role.roleName) {
          case "student":
            this.router.navigate(['/student']);
            break;
          case "professor":
            this.router.navigate(['/professor']);
            break;
          case "administrator":
            this.router.navigate(['/administrator']);
            break;
        }
      }

      this.loadingService.hide();
    }));
  }
}

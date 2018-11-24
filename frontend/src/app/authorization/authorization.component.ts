import {Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Router} from "@angular/router";

import {Subscription} from "rxjs";
import {UserAccount} from "../model/UserAccount";
import {TableModelService} from "../service/table-model.service";
import {Constants} from "../share/constants";
import {Token} from "../model/token";
import {TokenStorage} from "../service/token-storage.service";
import {AuthorizationService} from "../service/authorization.service";
import {Role} from "../model/role";
import {TableModel} from "../model/TableModel";


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
              private tokeStorage: TokenStorage,
              private authService: AuthorizationService) {
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
      let jwtData = token.token.split('.')[1]
      let decodedJwtJsonData = window.atob(jwtData)
      let decodedJwtData = JSON.parse(decodedJwtJsonData);

      console.log(decodedJwtData);

      /*
      *
        exp: 1543095228
        iat: 1543082268
        scopes: "admin"
        sub: "qwe@qwe.com"
      * */

      this.tableModelService.getUserByLogin(decodedJwtData.sub).subscribe(user => {
        this.authorizationAccount = user as UserAccount;

        console.log(this.authorizationAccount);

        if (!this.authorizationAccount) {
          this.alertUserAboutError = true;
        } else {
          this.alertUserAboutError = false;

          this.authService.changeAuthorizedUser(this.authorizationAccount);

          switch (this.authorizationAccount.role.roleName) {
            case "student":
              this.router.navigate(['student']);
              break;
            case "professor":
              this.router.navigate(['professor']);
              break;
            case "admin":
              this.router.navigate(['administrator']);
              break;
          }
        }

        this.loadingService.hide();
      });
    }));
  }
}

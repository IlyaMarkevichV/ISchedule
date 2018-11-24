import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {UserAccount} from "../model/UserAccount";

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {

  private messageSource = new BehaviorSubject(new UserAccount());
  currentAuthorizedUser = this.messageSource.asObservable();

  constructor() { }

  changeAuthorizedUser(user: UserAccount) {
    this.messageSource.next(user);
  }
}

import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {UserAccount} from "../model/UserAccount";
import {ProfessorAccount} from "../model/professor-account";
import {Constants} from "../share/constants";

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {

  private userAccount = new BehaviorSubject(new UserAccount());
  private professor = new BehaviorSubject(new ProfessorAccount());

  public currentAuthorizedUser = this.userAccount.asObservable();
  public currentProfessor = this.professor.asObservable();

  constructor() { }

  transmitAuthorizedUser(user: UserAccount) {
    this.userAccount.next(user);
  }

  transmitProfessor(professorAccount: ProfessorAccount) {
    this.professor.next(professorAccount);
    console.log('qweqweqweqweqw');
  }
}

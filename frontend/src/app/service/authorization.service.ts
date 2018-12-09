import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {UserAccount} from "../model/UserAccount";
import {ProfessorAccount} from "../model/professor-account";
import {Constants} from "../share/constants";
import {DaysOfWeek} from "../model/DaysOfWeek";
import {Lesson} from "../model/lesson";

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {

  private userAccount = new BehaviorSubject(new UserAccount());
  private professor = new BehaviorSubject(new ProfessorAccount());
  private days = new BehaviorSubject(new Array<Lesson>());

  public currentAuthorizedUser = this.userAccount.asObservable();
  public currentProfessor = this.professor.asObservable();
  public currentDays = this.days.asObservable();

  constructor() { }

  transmitAuthorizedUser(user: UserAccount) {
    this.userAccount.next(user);
  }

  transmitProfessor(professorAccount: ProfessorAccount) {
    this.professor.next(professorAccount);
  }

  transmitDays(lessons: Lesson[]) {
    let array: Array<Lesson> = new Array<Lesson>();
    lessons.forEach(lesson => array.push(lesson));
    this.days.next(array);
  }
}

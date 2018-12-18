import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {UserAccount} from "../model/UserAccount";
import {ProfessorAccount} from "../model/professor-account";
import {Constants} from "../share/constants";
import {DaysOfWeek} from "../model/DaysOfWeek";
import {Lesson} from "../model/lesson";
import {StudentAccount} from "../model/student-account";
import {st} from "@angular/core/src/render3";

@Injectable({
  providedIn: 'root'
})
export class AuthorizationAndTransmitService {

  private userAccount = new BehaviorSubject(new UserAccount());
  private professor = new BehaviorSubject(new ProfessorAccount());
  private student = new BehaviorSubject(new StudentAccount());
  private daysProfessor = new BehaviorSubject(new Array<Lesson>());
  private daysStudent = new BehaviorSubject(new Array<Lesson>());

  public currentAuthorizedUser = this.userAccount.asObservable();
  public currentProfessor = this.professor.asObservable();
  public currentStudent = this.student.asObservable();
  public currentProfessorLessons = this.daysProfessor.asObservable();
  public currentStudentLessons = this.daysStudent.asObservable();

  constructor() { }

  transmitAuthorizedUser(user: UserAccount) {
    this.userAccount.next(user);
  }

  transmitProfessor(professorAccount: ProfessorAccount) {
    this.professor.next(professorAccount);
  }

  transmitProfessorLessons(lessons: Lesson[]) {
    let array: Array<Lesson> = new Array<Lesson>();
    lessons.forEach(lesson => array.push(lesson));
    this.daysProfessor.next(lessons);
  }

  transmitStudentLessons(lessons: Lesson[]) {
    let array: Array<Lesson> = new Array<Lesson>();
    lessons.forEach(lesson => array.push(lesson));
    this.daysStudent.next(array);
  }

  transmitStudent(student: StudentAccount) {
    this.student.next(student);
  }
}

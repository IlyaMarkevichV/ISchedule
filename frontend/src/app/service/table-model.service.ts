import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Faculty} from "../model/faculty";
import {Group} from "../model/group";
import {UserAccount} from "../model/UserAccount";

@Injectable({
  providedIn: 'root'
})
export class TableModelService {

  constructor(private http: HttpClient) {
  }

/*
  getStudentAccounts(): Observable<StudentAccount[]> {
    return this.http.get<StudentAccount[]>( '/api/ba-user-group');
  }

  saveStudentAccount(studentAccount: StudentAccount): Observable<StudentAccount> {
    return this.http.post<StudentAccount>('/api/ba-user-group', studentAccount);
  }

  deleteStudentAccount(studentAccountId: string): Observable<void> {
    return this.http.delete<void>( '/api/ba-user-group/' + studentAccountId);
  }

  getProfessorAccountById(id: number): Observable<ProfessorAccount> {
    return this.http.get<ProfessorAccount>('/api/ba-users/' + id);
  }

  getProfessorAccounts(): Observable<ProfessorAccount[]> {
    return this.http.get<ProfessorAccount[]>('/api/ba-users?role=professor');
  }

  saveProfessorAccount(professorAccount: ProfessorAccount): Observable<ProfessorAccount> {
    return this.http.post<ProfessorAccount>('/api/ba-users', professorAccount);
  }

  deleteProfessorAccount(professorAccountId: string): Observable<void> {
    return this.http.delete<void>('/api/ba-users/' + professorAccountId);
  }
*/


  /***************************************************************************************************/

  getProfessors(): Observable<UserAccount[]> {
    return this.http.get<UserAccount[]>('/api/ba-account?role=professor');
  }

  saveProfessor(professor: UserAccount): Observable<UserAccount> {
    return this.http.post<UserAccount>('/api/ba-account', professor);
  }

  deleteProfessor(professor: UserAccount): Observable<void> {
    return this.http.delete<void>('/api/ba-account/' + professor.accountId + '?role=' + professor.role);
  }

  /*****/

  getStudents(): Observable<UserAccount[]> {
    return this.http.get<UserAccount[]>('/api/ba-account?role=student');
  }

  saveStudent(student: UserAccount): Observable<UserAccount> {
    return this.http.post<UserAccount>('/api/ba-account', student);
  }

  deleteStudent(student: UserAccount): Observable<void> {
    return this.http.delete<void>('/api/ba-account/' + student.accountId + '?role=' + student.role);
  }

  getUserByLoginAndPassword(login: string, password: string): Observable<UserAccount> {
    console.log("/api/ba-account/auth?login=" + login + "&password=" + password);
    return this.http.get<UserAccount>("/api/ba-account/auth?login=" + login + "&password=" + password);
  }

  /***************************************************************************************************/
  getFacultyById(id: number): Observable<Faculty> {
    return this.http.get<Faculty>('/api/ba-faculty/' + id);
  }

  getFaculties(): Observable<Faculty[]> {
    return this.http.get<Faculty[]>('/api/ba-faculty/');
  }

  saveFaculty(faculty: Faculty): Observable<Faculty> {
    return this.http.post<Faculty>('/api/ba-faculty', faculty);
  }

  deleteFaculty(id: number): Observable<void> {
    return this.http.delete<void>('api/ba-faculty/' + id);
  }

  getGroupById(id: number): Observable<Group> {
    return this.http.get<Group>('/api/ba-group/' + id);
  }

  getGroups(): Observable<Group[]> {
    return this.http.get<Group[]>('/api/ba-group/');
  }

  saveGroup(group: Group): Observable<Group> {
    return this.http.post<Group>('/api/ba-group', group);
  }

  deleteGroup(groupId: number): Observable<void> {
    return this.http.delete<void>('/api/ba-group/' + groupId);
  }
}

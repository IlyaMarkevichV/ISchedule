import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Faculty} from "../model/faculty";
import {Group} from "../model/group";
import {ProfessorAccount} from "../model/professor-account";
import {StudentAccount} from "../model/student-account";

@Injectable({
  providedIn: 'root'
})
export class TableModelService {

  constructor(private http: HttpClient) {
  }

  getStudentAccountById(id: number): Observable<StudentAccount> {
    return this.http.get<StudentAccount>( '/api/ba-student/' + id);
  }

  getStudentAccounts(): Observable<StudentAccount[]> {
    return this.http.get<StudentAccount[]>( '/api/ba-student');
  }

  saveStudentAccount(studentAccount: StudentAccount): Observable<StudentAccount> {
    return this.http.post<StudentAccount>('/api/ba-student', studentAccount);
  }

  deleteStudentAccount(studentAccountId: string): Observable<void> {
    return this.http.delete<void>( '/api/ba-student/' + studentAccountId);
  }

  getProfessorAccountById(id: number): Observable<ProfessorAccount> {
    return this.http.get<ProfessorAccount>('/api/ba-professor/' + id);
  }

  getProfessorAccounts(): Observable<ProfessorAccount[]> {
    return this.http.get<ProfessorAccount[]>('/api/ba-professor');
  }

  saveProfessorAccount(professorAccount: ProfessorAccount): Observable<ProfessorAccount> {
    return this.http.post<ProfessorAccount>('/api/ba-professor', professorAccount);
  }

  deleteProfessorAccount(professorAccountId: string): Observable<void> {
    return this.http.delete<void>('/api/ba-professor/' + professorAccountId);
  }

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

import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Faculty} from "../model/faculty";
import {Group} from "../model/group";
import {UserAccount} from "../model/UserAccount";
import {Token} from "../model/token";
import {StudentAccount} from "../model/student-account";
import {ProfessorAccount} from "../model/professor-account";
import {Role} from "../model/role";
import {Day} from "../model/day";
import {LessonTime} from "../model/lessonTime";
import {LessonInfo} from "../model/lessonInfo";
import {Lesson} from "../model/lesson";
import {Page} from "../model/page";

@Injectable({
  providedIn: 'root'
})
export class TableModelService {

  constructor(private http: HttpClient) {
  }

  getProfessorByAccountLogin(login: number): Observable<ProfessorAccount> {
    let q = this.http.get<ProfessorAccount>('/api/ba-professors/search?login=' + login);
    console.log('--------------------------------------',
      q.subscribe(req => console.log("----", (req as ProfessorAccount) )));
    return q;
  }

  getProfessors(): Observable<Page<ProfessorAccount>> {
    return this.http.get<Page<ProfessorAccount>>('/api/ba-professors?page=0');
  }

  saveProfessor(professor: ProfessorAccount): Observable<ProfessorAccount> {
    return this.http.post<ProfessorAccount>('/api/ba-professors', professor);
  }

  deleteProfessor(professor: ProfessorAccount): Observable<void> {
    return this.http.delete<void>('/api/ba-professors/' + professor.id);
  }

  /*****/

  getStudents(): Observable<Page<StudentAccount>> {
    return this.http.get<Page<StudentAccount>>('/api/ba-students?page=0');
  }

  saveStudent(student: StudentAccount): Observable<StudentAccount> {
    return this.http.post<StudentAccount>('/api/ba-students', student);
  }

  deleteStudent(student: StudentAccount): Observable<void> {
    return this.http.delete<void>('/api/ba-students/' + student.id);
  }

  /*security*/
  attemptAuth(login: string, password: string): Observable<Token> {
    const credentials = {login: login, password: password};
    return this.http.post<Token>('/token/generate-token', credentials);
  }

  getAccountById(id: number): Observable<UserAccount> {
    return this.http.get<UserAccount>("/api/ba-accounts/" + id);
  }

  getUserByLogin(login: string): Observable<UserAccount> {
    return this.http.get<UserAccount>("/api/ba-accounts/auth?login=" + login);
  }

  /**/

  /***************************************************************************************************/
  getFacultyById(id: number): Observable<Faculty> {
    return this.http.get<Faculty>('/api/ba-faculties/' + id);
  }

  getFaculties(): Observable<Faculty[]> {
    return this.http.get<Faculty[]>('/api/ba-faculties/');
  }

  saveFaculty(faculty: Faculty): Observable<Faculty> {
    return this.http.post<Faculty>('/api/ba-faculties', faculty);
  }

  deleteFaculty(id: number): Observable<void> {
    return this.http.delete<void>('api/ba-faculties/' + id);
  }

  getGroupById(id: number): Observable<Group> {
    return this.http.get<Group>('/api/ba-groups/' + id);
  }

  getGroups(): Observable<Page<Group>> {
    return this.http.get<Page<Group>>('/api/ba-groups?page=0');
  }

  saveGroup(group: Group): Observable<Group> {
    return this.http.post<Group>('/api/ba-groups', group);
  }

  deleteGroup(groupId: number): Observable<void> {
    return this.http.delete<void>('/api/ba-groups/' + groupId);
  }

  getRoles(): Observable<Role[]> {
    return this.http.get<Role[]>('/api/ba-roles');
  }

  getDays(): Observable<Day[]> {
    return this.http.get<Day[]>('/api/ba-days');
  }

  getLessonTimes(): Observable<LessonTime[]> {
    return this.http.get<LessonTime[]>('/api/ba-lesson-times');
  }

  getLessonInfos(): Observable<LessonInfo[]> {
    return this.http.get<LessonInfo[]>('/api/ba-lesson-infos');
  }

  getLessons(): Observable<Page<Lesson>> {
    return this.http.get<Page<Lesson>>('/api/ba-lessons?page=0');
  }

  saveLesson(lesson: Lesson): Observable<Lesson> {
    return this.http.post<Lesson>('/api/ba-lessons', lesson);
  }

  deleteLesson(lessonId: number): Observable<void> {
    return this.http.delete<void>('/api/ba-lessons/' + lessonId);
  }

  getPageObservable<T>(entityName: string, page: number, size: number, sort?: string): Observable<Page<T>> {
      return this.http.get<Page<T>>('/api/ba-'.concat(entityName).concat('?')
        .concat('page=' + page)
        .concat(size != 0 ? ('&size=' + size) : '')
        .concat(sort ? ('&sort=' + sort) : ''));
  }
}

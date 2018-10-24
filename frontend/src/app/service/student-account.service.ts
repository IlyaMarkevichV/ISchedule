import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {StudentAccount} from '../model/student-account';

@Injectable({
  providedIn: 'root'
})
// Data service
export class StudentAccountService {

  constructor(private http: HttpClient) {
  }

// Ajax request for student account data
  getStudentAccounts(): Observable<StudentAccount[]> {
    return this.http.get<StudentAccount[]>('/api/ba');
  }

  saveStudentAccount(studentAccount: StudentAccount): Observable<StudentAccount> {
    return this.http.post<StudentAccount>('/api/ba', studentAccount);
  }

  deleteStudentAccount(studentAccount: StudentAccount): Observable<void> {
    return this.http.delete<void>('/api/ba' + studentAccount);
  }
}

import {ProfessorAccount} from "../model/professor-account";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProfessorAccountService {

  constructor(private http: HttpClient) {
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
}

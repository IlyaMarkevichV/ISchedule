import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Group} from "../model/group";

@Injectable({
  providedIn: 'root'
})
export class GroupService {

  constructor(private http: HttpClient) {
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

import {Component, EventEmitter, Input, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {StudentAccountService} from "../../service/student-account.service";
import {GroupService} from "../../service/group.service";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {DatePipe} from "@angular/common";
import {Subscription} from "rxjs";
import {FacultyService} from "../../service/faculty.service";
import {Group} from "../../model/group";
import {Faculty} from "../../model/faculty";

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent implements OnInit, OnDestroy {

  private subscriptions: Subscription[] = [];
  public groups: Group[];
  public faculties: Faculty[];
  public editMode: boolean = false;

  public editableGroup: Group = new Group();

  private modalRef: BsModalRef;

  public isCorrect: boolean = true;

  constructor(private loadingService: Ng4LoadingSpinnerService,
              private groupService: GroupService,
              private facultyService: FacultyService,
              private modalService: BsModalService,
              private datePipe: DatePipe) { }

  ngOnInit() {
    this.editableGroup.faculty = new Faculty();
    this.loadGroups();
    this.loadFaculties();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  public freshEditableGroup() {
    this.editableGroup = new Group();
    this.editableGroup.faculty = new Faculty();
  }

  public loadGroups(): void {
    this.loadingService.show();
    this.subscriptions.push(this.groupService.getGroups().subscribe(grp => {
      this.groups = grp as Group[];
      this.loadingService.hide();
    }));
  }

  public loadFaculties(): void {
    this.loadingService.show();
    this.subscriptions.push(this.facultyService.getFaculties().subscribe(fc => {
      this.faculties = fc as Faculty[];
      this.loadingService.hide();
    }));
  }

  openModal(template: TemplateRef<any>, group?: Group): void {
    if (group) {
      this.editableGroup = Group.cloneGroup(group);
      this.editMode = true;
    } else {
      this.freshEditableGroup();
      this.editMode = false;
    }
    this.modalRef = this.modalService.show(template);
  }

  public closeModal(): void {
    this.modalRef.hide();
  }

  public updateGroups(): void {
    this.loadGroups();
  }

  public addGroup(): void {
    this.loadingService.show();
    this.subscriptions.push(this.facultyService.getFacultyById(this.editableGroup.faculty.facultyId).subscribe(faculty => {
      if (faculty) {
        this.editableGroup.faculty = faculty as Faculty;
        this.isCorrect = true;
      } else {
        this.loadingService.hide();
        this.isCorrect = false;
        return;
      }

      this.editableGroup.date = this.datePipe.transform(this.editableGroup.date, 'yyyy-MM-dd');
      this.subscriptions.push(this.groupService.saveGroup(this.editableGroup).subscribe(() => {
        this.updateGroups();
        this.closeModal();
        this.loadingService.hide();
        this.freshEditableGroup();
      }));
    }));
  }

  public deleteGroup(groupId: number): void {
    this.loadingService.show();
    this.subscriptions.push(this.groupService.deleteGroup(groupId).subscribe(() => {
      this.updateGroups();
    }));
  }

  checkFaculty(name: string): boolean {
    if (name === this.editableGroup.faculty.facultyName)
      return true;
    else
      return false;
  }
}

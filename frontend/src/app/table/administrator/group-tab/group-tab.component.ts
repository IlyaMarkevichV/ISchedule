import {
  Component,
  EventEmitter,
  Input,
  OnDestroy,
  OnInit,
  Output,
  TemplateRef
} from '@angular/core';
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {DatePipe} from "@angular/common";
import {Subscription} from "rxjs";
import {TableModel} from "../../../model/TableModel";
import {Group} from "../../../model/group";
import {TableModelService} from "../../../service/table-model.service";
import {Faculty} from "../../../model/faculty";


@Component({
  selector: 'group-tab',
  templateUrl: './group-tab.component.html',
  styleUrls: ['./group-tab.component.css']
})
export class GroupTabComponent implements OnInit, OnDestroy {

  @Input()
  public tableModel: TableModel;

  @Output()
  loadFaculties: EventEmitter<any> = new EventEmitter<any>();

  @Output()
  loadGroups: EventEmitter<any> = new EventEmitter<any>();

  /*info for pagination*/
  page: number = 1;
  totalNumberOfEntities: number;

  public tempGroupForFilter: Group = new Group();

  public searchButtonName: string = 'Search by';
  public groupField: string;
  public searchText: string;

  private subscriptions: Subscription[] = [];
  public editMode: boolean = false;
  public addFac: string = 'true';

  public editableGroup: Group = new Group();

  private modalRef: BsModalRef;

  public isCorrect: boolean = true;

  public tempFacultyId: number;

  constructor(private loadingService: Ng4LoadingSpinnerService,
              private tableModelService: TableModelService,
              private modalService: BsModalService,
              private datePipe: DatePipe) {
  }

  ngOnInit() {
    this.editableGroup.faculty = new Faculty();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  public refreshEditableGroup() {
    this.editableGroup = new Group();
    this.editableGroup.faculty = new Faculty();
  }

  openModal(template: TemplateRef<any>, group?: Group): void {
    if (group) {
      this.editableGroup = Group.cloneGroup(group);
      this.editMode = true;
      this.tempFacultyId = this.editableGroup.faculty.facultyId;
    } else {
      this.refreshEditableGroup();
      this.editMode = false;
      this.tempFacultyId = this.tableModel.faculties.length != 0 ? this.tableModel.faculties[0].facultyId : 0;
    }
    this.modalRef = this.modalService.show(template);
  }

  public closeModal(): void {
    this.modalRef.hide();
  }

  public updateGroups(): void {
    this.loadGroups.emit();
    this.page = 1;
    this.totalNumberOfEntities = this.tableModel.groups.length;
  }

  public addFaculty(): void {
    this.loadingService.show();
    this.editableGroup.faculty.facultyId = 3;
    console.log(this.editableGroup.faculty);
    this.subscriptions.push(this.tableModelService.saveFaculty(this.editableGroup.faculty).subscribe(() => {
      this.loadFaculties.emit();
      this.closeModal();
      this.loadingService.hide();
    }));
  }

  public deleteFaculty(): void {
    this.loadingService.show();
    console.log(this.editableGroup.faculty);
    this.subscriptions.push(this.tableModelService.deleteFaculty(this.tempFacultyId).subscribe(() => {
      this.closeModal();
      this.loadFaculties.emit();
      this.loadingService.hide();
    }));
  }

  /* check for the same group id*/
  public addGroup(): void {
    this.loadingService.show();

    /*convert data*/
    this.editableGroup.groupId = Number(this.editableGroup.groupId);
    this.editableGroup.grade = Number(this.editableGroup.grade);
    this.editableGroup.date = this.datePipe.transform(this.editableGroup.date, 'yyyy-MM-dd');

    /*add faculty to editableGroup*/
    for (let faculty of this.tableModel.faculties) {
      if (this.tempFacultyId == faculty.facultyId) {
        this.editableGroup.faculty = faculty;
        break;
      }
    }

    this.subscriptions.push(this.tableModelService.saveGroup(this.editableGroup).subscribe(() => {
      this.updateGroups();
      this.closeModal();
      this.loadingService.hide();
      this.refreshEditableGroup();
    }));
  }

  public deleteGroup(groupId: number): void {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.deleteGroup(groupId).subscribe(() => {
      this.updateGroups();
      this.loadingService.hide();
    }));
  }

  searchTrigger(): void {
    if (this.searchButtonName === 'Search by')
      return;

    this.tempGroupForFilter = new Group();
    this.tempGroupForFilter.faculty = new Faculty();
    switch (this.groupField) {
      case 'groupId':
        if (this.searchText !== '')
          this.tempGroupForFilter.groupId = Number(this.searchText);
        break;
      case 'grade':
        if (this.searchText !== '')
          this.tempGroupForFilter.grade = Number(this.searchText);
        break;
      case 'facultyName':
        this.tempGroupForFilter.faculty.facultyName = this.searchText;
        break;
      case 'date':
        this.tempGroupForFilter.date = this.searchText;
        break;
    }
  }
}
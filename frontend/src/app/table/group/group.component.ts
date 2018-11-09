import {Component, EventEmitter, forwardRef, Inject, Input, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {DatePipe} from "@angular/common";
import {Subscription} from "rxjs";
import {Group} from "../../model/group";
import {Faculty} from "../../model/faculty";
import {TableModel} from "../../model/TableModel";
import {TableModelService} from "../../service/table-model.service";
import {TableComponent} from "../table.component";

@Component({
  selector: 'group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent implements OnInit, OnDestroy {

  @Input()
  public tableModel: TableModel;

  public tempGroupForFilter: Group = new Group();

  public searchButtonName: string = 'Search by';
  public groupField: string;
  public searchText: string;

  private subscriptions: Subscription[] = [];
  public editMode: boolean = false;

  public editableGroup: Group = new Group();

  private modalRef: BsModalRef;

  public tempFacultyId: number;

  constructor(private loadingService: Ng4LoadingSpinnerService,
              private tableModelService: TableModelService,
              private modalService: BsModalService,
              private datePipe: DatePipe,
              @Inject(forwardRef(() => TableComponent)) private parent: TableComponent) {
  }

  ngOnInit() {
    this.editableGroup.faculty = new Faculty();

  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  public freshEditableGroup() {
    this.editableGroup = new Group();
    this.editableGroup.faculty = new Faculty();
  }

  openModal(template: TemplateRef<any>, group?: Group): void {
    if (group) {
      this.editableGroup = Group.cloneGroup(group);
      this.editMode = true;
      this.tempFacultyId = this.editableGroup.faculty.facultyId;
    } else {
      this.freshEditableGroup();
      this.editMode = false;
      this.tempFacultyId = this.tableModel.faculties.length != 0 ? this.tableModel.faculties[0].facultyId : 0;
    }
    this.modalRef = this.modalService.show(template);
  }

  public closeModal(): void {
    this.modalRef.hide();
  }

  public updateGroups(): void {
    this.parent.loadGroups();
  }

  public addGroup(): void {
    this.loadingService.show();

    /*convert data*/
    this.editableGroup.groupId = Number(this.editableGroup.groupId);
    this.editableGroup.grade = Number(this.editableGroup.grade);
    this.editableGroup.date = this.datePipe.transform(this.editableGroup.date, 'yyyy-MM-dd');

    this.subscriptions.push(this.tableModelService.saveGroup(this.editableGroup).subscribe(() => {
      this.updateGroups();
      this.closeModal();
      this.loadingService.hide();
      this.freshEditableGroup();
    }));
  }

  public deleteGroup(groupId: number): void {
    this.subscriptions.push(this.tableModelService.deleteGroup(groupId).subscribe(() => {
      /*refresh all stored data in tableModel in case when we can delete parent node */
      this.parent.loadAllData();
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

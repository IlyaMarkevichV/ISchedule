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
import {Lesson} from "../../../model/lesson";
import {RequestHelper} from "../../../model/RequestHelper";
import {Constants} from "../../../share/constants";
import {Page} from "../../../model/page";


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

  private subscriptions: Subscription[] = [];
  public editMode: boolean = false;
  public addFac: string = 'true';

  public editableGroup: Group = new Group();

  private modalRef: BsModalRef;

  public groupPage: Page<Group>;
  public itemsPerPage: number = Constants.NUMBER_OF_ROWS_ON_ONE_PAGE;
  public sortDirection: boolean = false;

  constructor(private loadingService: Ng4LoadingSpinnerService,
              private tableModelService: TableModelService,
              private modalService: BsModalService,
              private datePipe: DatePipe) {
  }

  ngOnInit() {
    this.editableGroup.faculty = new Faculty();
    this.groupPage = new Page<Group>();
    this.getPage(1);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  compareFn(obj1: any, obj2: any): boolean {
    return obj1 && obj2 ? obj1.id === obj2.id : obj1 === obj2;
  }

  public refreshEditableGroup() {
    this.editableGroup = new Group();
    this.editableGroup.faculty = new Faculty();
  }

  openModal(template: TemplateRef<any>, group?: Group): void {
    if (group) {
      this.editableGroup = Group.cloneGroup(group);
      this.editMode = true;
    } else {
      this.refreshEditableGroup();
      this.editMode = false;
      this.editableGroup.faculty = this.tableModel.faculties.length != 0 ? this.tableModel.faculties[0] : null;
    }
    this.modalRef = this.modalService.show(template);
  }

  public closeModal(): void {
    this.modalRef.hide();
  }

  public updateGroups(): void {
    this.loadGroups.emit();
    this.getPage(1);
  }

  public addFaculty(): void {
    this.loadingService.show();
    this.editableGroup.faculty.id = 3;
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
    this.subscriptions.push(this.tableModelService.deleteFaculty(this.editableGroup.faculty.id).subscribe(() => {
      this.closeModal();
      this.loadFaculties.emit();
      this.loadingService.hide();
    }));
  }

  /* check for the same group id*/
  public addGroup(): void {
    this.loadingService.show();

    /*convert data*/
    this.editableGroup.id = Number(this.editableGroup.id);
    this.editableGroup.grade = Number(this.editableGroup.grade);
    this.editableGroup.graduation = this.datePipe.transform(this.editableGroup.graduation, 'yyyy-MM-dd');

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

  getPage(pageNumber: number) {
    this.loadingService.show();
    console.log(pageNumber);
    console.log('id,'  + (this.sortDirection ? 'desc' : 'asc'));
    this.subscriptions.push(this.tableModelService.getPageObservable<Group>(
      RequestHelper.GROUP,
      pageNumber - 1,
      this.itemsPerPage,
      'id,' + (this.sortDirection ? 'desc' : 'asc'))
      .subscribe(req => {
        this.groupPage = req as Page<Group>;
        this.groupPage.number += 1;
        this.loadingService.hide();
      }));
  }
}

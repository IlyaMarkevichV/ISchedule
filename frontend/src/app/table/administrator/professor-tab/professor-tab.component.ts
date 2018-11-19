import {
  Component,
  EventEmitter,
  forwardRef,
  Inject,
  Input,
  OnDestroy,
  OnInit,
  Output,
  TemplateRef
} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Subscription} from "rxjs";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {ProfessorAccount} from "../../../model/professor-account";
import {TableModelService} from "../../../service/table-model.service";
import {TableModel} from "../../../model/TableModel";
import {UserAccount} from "../../../model/UserAccount";
import {DatePipe} from "@angular/common";
import {StudentProfessor} from "../../../model/StudentProfessor";
import {Group} from "../../../model/group";


@Component({
  selector: 'professor-tab',
  templateUrl: './professor-tab.component.html',
  styleUrls: ['./professor-tab.component.css']
})
export class ProfessorTabComponent implements OnInit, OnDestroy {

  @Input()
  public tableModel: TableModel;

  @Output()
  loadProfessors: EventEmitter<any> = new EventEmitter<any>();

  /*info for pagination*/
  page: number = 1;
  totalNumberOfEntities: number;

  public searchButtonName: string = "Search by";
  public searchText: string;
  public professorField: string;

  private modalRef: BsModalRef;

  public editMode: boolean = false;

  public tempProfessorForFilter: UserAccount = new UserAccount();
  public editableProfessor: UserAccount = new UserAccount();

  private subscriptions: Subscription[] = [];

  // Dependency injection
  constructor(private loadingService: Ng4LoadingSpinnerService,
              private tableModelService: TableModelService,
              private modalService: BsModalService,
              private datePipe: DatePipe) {
  }

  ngOnInit() {
    this.editableProfessor = new UserAccount();
    this.editableProfessor.studentProfessor = new StudentProfessor();
    this.editableProfessor.studentProfessor.group = new Group();
    this.editableProfessor.role = 'professor';
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  addProfessorAccount(): void {
    this.loadingService.show();

    this.editableProfessor.studentProfessor.birthday =
      this.datePipe.transform(this.editableProfessor.studentProfessor.birthday, 'yyyy-MM-dd');
    this.subscriptions.push(this.tableModelService.saveProfessor(this.editableProfessor).subscribe(() => {
      this.updateProfessors();
      this.closeModal();
      this.loadingService.hide();
      this.refreshEditableProfessor();
    }));
  }

  deleteProfessorAccount(professor: UserAccount): void {
    this.subscriptions.push(this.tableModelService.deleteProfessor(professor).subscribe(() => {
      /*refresh all stored data in tableModel in case when we can delete parent node */
      this.updateProfessors();
    }));
  }

  updateProfessors(): void {
    this.loadProfessors.emit();
    this.page = 1;
    this.totalNumberOfEntities = this.tableModel.professors.length;
  }

  private refreshEditableProfessor(): void {
    this.editableProfessor = new UserAccount();
    this.editableProfessor.studentProfessor = new StudentProfessor();
    this.editableProfessor.studentProfessor.group = new Group();
    this.editableProfessor.role = 'professor';
  }

  openModal(template: TemplateRef<any>, professorAccount?: UserAccount): void {
    if (professorAccount) {
      this.editableProfessor = UserAccount.cloneAccount(professorAccount);
      this.editMode = true;
    } else {
      this.refreshEditableProfessor();
      this.editMode = false;
    }
    this.modalRef = this.modalService.show(template);
  }

  closeModal(): void {
    this.modalRef.hide();
  }

  public searchTrigger(): void {
    if (this.searchButtonName === 'Search by')
      return;

    this.tempProfessorForFilter = new UserAccount();
    switch (this.professorField) {
      case 'firstName':
        this.tempProfessorForFilter.studentProfessor.firstName = this.searchText;
        break;
      case 'lastName':
        this.tempProfessorForFilter.studentProfessor.lastName = this.searchText;
        break;
      case 'birthday':
        this.tempProfessorForFilter.studentProfessor.birthday = this.searchText;
        break;
    }
  }
}

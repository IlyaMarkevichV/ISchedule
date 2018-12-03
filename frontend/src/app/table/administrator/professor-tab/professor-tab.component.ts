import {
  Component,
  EventEmitter,
  Input,
  OnDestroy,
  OnInit,
  Output,
  TemplateRef
} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Subscription} from "rxjs";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TableModelService} from "../../../service/table-model.service";
import {TableModel} from "../../../model/TableModel";
import {UserAccount} from "../../../model/UserAccount";
import {DatePipe} from "@angular/common";
import {ProfessorAccount} from "../../../model/professor-account";
import {Role} from "../../../model/role";
import {Group} from "../../../model/group";
import {RequestHelper} from "../../../model/RequestHelper";
import {Constants} from "../../../share/constants";
import {Page} from "../../../model/page";


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

  private professorRole: Role;

  public searchButtonName: string = "Search by";
  public searchText: string;
  public professorField: string;

  private modalRef: BsModalRef;

  public editMode: boolean = false;

  public tempProfessorForFilter: ProfessorAccount;
  public editableProfessor: ProfessorAccount;

  private subscriptions: Subscription[] = [];

  public professorPage: Page<ProfessorAccount>;
  public sortDirection: boolean = false;
  public itemsPerPage: number = Constants.NUMBER_OF_ROWS_ON_ONE_PAGE;

  // Dependency injection
  constructor(private loadingService: Ng4LoadingSpinnerService,
              private tableModelService: TableModelService,
              private modalService: BsModalService,
              private datePipe: DatePipe) {
  }

  ngOnInit() {
    this.tempProfessorForFilter = new ProfessorAccount();
    this.tempProfessorForFilter.account = new UserAccount();

    this.editableProfessor = new ProfessorAccount();
    this.editableProfessor.account = new UserAccount();

    this.professorPage = new Page<ProfessorAccount>();
    this.getPage(1);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  addProfessorAccount(): void {
    this.loadingService.show();

    this.editableProfessor.account.birthday =
      this.datePipe.transform(this.editableProfessor.account.birthday, 'yyyy-MM-dd');

    this.subscriptions.push(this.tableModelService.saveProfessor(this.editableProfessor).subscribe(() => {
      this.updateProfessors();
      this.closeModal();
      this.refreshEditableProfessor();
      this.loadingService.hide();
    }));
  }

  deleteProfessorAccount(professor: ProfessorAccount): void {
    this.subscriptions.push(this.tableModelService.deleteProfessor(professor).subscribe(() => {
      this.updateProfessors();
    }));
  }

  updateProfessors(): void {
    this.loadProfessors.emit();
    this.page = 1;
    this.totalNumberOfEntities = this.tableModel.professors.length;
  }

  private refreshEditableProfessor(): void {
    this.editableProfessor = new ProfessorAccount();
    this.editableProfessor.account = new UserAccount();

    if (!this.professorRole) {
      for (let role of this.tableModel.roles) {
        if (role.roleName === 'professor') {
          this.professorRole = role;
          break;
        }
      }
    }

    this.editableProfessor.account.role = this.professorRole;
  }

  openModal(template: TemplateRef<any>, professorAccount?: ProfessorAccount): void {
    if (professorAccount) {
      this.editableProfessor = ProfessorAccount.cloneAcc(professorAccount);
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

    this.tempProfessorForFilter = new ProfessorAccount();
    switch (this.professorField) {
      case 'firstName':
        this.tempProfessorForFilter.account.firstName = this.searchText;
        break;
      case 'lastName':
        this.tempProfessorForFilter.account.lastName = this.searchText;
        break;
      case 'birthday':
        this.tempProfessorForFilter.account.birthday = this.searchText;
        break;
    }
  }

  getPage(pageNumber: number) {
    this.loadingService.show();
    console.log(pageNumber);
    console.log('id,'  + (this.sortDirection ? 'desc' : 'asc'));
    this.subscriptions.push(this.tableModelService.getPageObservable<ProfessorAccount>(
      RequestHelper.PROFESSOR,
      pageNumber - 1,
      Constants.NUMBER_OF_ROWS_ON_ONE_PAGE,
      'id,' + (this.sortDirection ? 'desc' : 'asc'))
      .subscribe(req => {
        this.professorPage = req as Page<ProfessorAccount>;
        this.professorPage.number += 1;
        this.loadingService.hide();
      }));
  }
}

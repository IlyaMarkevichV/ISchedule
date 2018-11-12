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


@Component({
  selector: 'professor-tab',
  templateUrl: './professor-tab.component.html',
  styleUrls: ['./professor-tab.component.css']
})
export class ProfessorTabComponent implements OnInit, OnDestroy {

  @Input()
  public tableModel: TableModel;

  /*info for pagination*/
  page: number = 1;
  totalNumberOfEntities: number;

  public searchButtonName: string = "Search by";
  public searchText: string;
  public professorField: string;

  private modalRef: BsModalRef;

  public editMode: boolean = false;

  public tempProfessorForFilter: ProfessorAccount = new ProfessorAccount();
  public editableProfessor: ProfessorAccount = new ProfessorAccount();

  private subscriptions: Subscription[] = [];

  // Dependency injection
  constructor(private loadingService: Ng4LoadingSpinnerService,
              private tableModelService: TableModelService,
              private modalService: BsModalService,
              private datePipe: DatePipe) {
  }

  ngOnInit() {
    this.editableProfessor.account = new UserAccount();
    this.editableProfessor.account.email = 'qwe@asd.qwe';
    this.editableProfessor.account.password = 'zxcas';
    this.editableProfessor.account.role = 'professor';
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  public loadProfessorAccounts(): void {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.getProfessorAccounts().subscribe(accounts => {
      this.tableModel.professors = accounts as ProfessorAccount[];
      this.totalNumberOfEntities = this.tableModel.professors.length;
      this.loadingService.hide();
    }));
  }

  addProfessorAccount(): void {
    this.loadingService.show();
    console.log('Add account: -------');
    console.log(this.editableProfessor);

    this.editableProfessor.birthday = this.datePipe.transform(this.editableProfessor.birthday, 'yyyy-MM-dd');
    this.subscriptions.push(this.tableModelService.saveProfessorAccount(this.editableProfessor).subscribe(() => {
      this.updateProfessorAccounts();
      this.closeModal();
      this.loadingService.hide();
      this.refreshEditableProfessor();
    }));
  }

  deleteProfessorAccount(professorAccountId: string): void {
    this.subscriptions.push(this.tableModelService.deleteProfessorAccount(professorAccountId).subscribe(() => {
      /*refresh all stored data in tableModel in case when we can delete parent node */
      this.updateProfessorAccounts();
    }));
  }

  updateProfessorAccounts(): void {
    this.loadProfessorAccounts();
    this.page = 1;
  }

  private refreshEditableProfessor(): void {
    this.editableProfessor = new ProfessorAccount();
    this.editableProfessor.account = new UserAccount();
    this.editableProfessor.account.email = 'qwe@asd.qwe';
    this.editableProfessor.account.password = 'zxcas';
    this.editableProfessor.account.role = 'professor';
  }

  openModal(template: TemplateRef<any>, professorAccount?: ProfessorAccount): void {
    if (professorAccount) {
      this.editableProfessor = ProfessorAccount.cloneProfessorAccount(professorAccount);
      this.editMode = true;
    } else {
      this.refreshEditableProfessor();
      console.log('Open modal:---------');
      console.log(this.editableProfessor);
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
        this.tempProfessorForFilter.firstName = this.searchText;
        break;
      case 'lastName':
        this.tempProfessorForFilter.lastName = this.searchText;
        break;
      case 'birthday':
        this.tempProfessorForFilter.birthday = this.searchText;
        break;
      case 'address':
        this.tempProfessorForFilter.address = this.searchText;
        break;
      case 'email':
        this.tempProfessorForFilter.email = this.searchText;
        break;
      case 'id':
        if (this.searchText !== '')
          this.tempProfessorForFilter.professorId = Number(this.searchText);
        break;
    }
  }
}

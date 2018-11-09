import {Component, forwardRef, Inject, Input, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import {ProfessorAccount} from "../../model/professor-account";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Subscription} from "rxjs";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TableModel} from "../../model/TableModel";
import {TableModelService} from "../../service/table-model.service";
import {TableComponent} from "../table.component";

@Component({
  selector: 'professor',
  templateUrl: './professor.component.html',
  styleUrls: ['./professor.component.css']
})
export class ProfessorComponent implements OnInit, OnDestroy {

  @Input()
  public tableModel: TableModel;

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
              @Inject(forwardRef(() => TableComponent)) private parent: TableComponent) {
  }

  ngOnInit() {
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  addProfessorAccount(): void {
    this.loadingService.show();
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
      this.parent.loadAllData();
    }));
  }

  updateProfessorAccounts(): void {
    this.parent.loadProfessorAccounts();
  }

  private refreshEditableProfessor(): void {
    this.editableProfessor = new ProfessorAccount();
  }

  openModal(template: TemplateRef<any>, professorAccount?: ProfessorAccount): void {
    if (professorAccount) {
      this.editableProfessor = ProfessorAccount.cloneProfessorAccount(professorAccount);
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

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

  private professorRole: Role;

  private modalRef: BsModalRef;

  public editMode: boolean = false;

  public tempProfessorForFilter: ProfessorAccount;
  public editableProfessor: ProfessorAccount;

  private subscriptions: Subscription[] = [];

  public professorPage: Page<ProfessorAccount>;
  public sortDirection: boolean = false;
  public itemsPerPage: number = Constants.NUMBER_OF_ROWS_ON_ONE_PAGE;

  public maxDate: Date;
  public minDate: Date;

  public wrongLogin: boolean = false;

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

    this.maxDate = new Date();
    this.maxDate.setFullYear(this.maxDate.getFullYear() - 25, 0, 1);
    this.minDate = new Date();
    this.minDate.setFullYear(this.maxDate.getFullYear() - 75, 0, 1);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  addProfessorAccount(): void {
    this.loadingService.show();

    this.editableProfessor.account.birthday =
      this.datePipe.transform(this.editableProfessor.account.birthday, 'yyyy-MM-dd');

    this.subscriptions.push(this.tableModelService.saveProfessor(this.editableProfessor).subscribe(professor => {
      if (professor && professor.id == -1 && professor.account.id == -1) {
        this.wrongLogin = true;
      } else {
        this.wrongLogin = false;
        this.updateProfessors();
        this.closeModal();
        this.refreshEditableProfessor();
      }
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
    this.getPage(1);
  }

  private refreshEditableProfessor(): void {
    this.editableProfessor = new ProfessorAccount();
    this.editableProfessor.account = new UserAccount();

    if (!this.professorRole) {
      for (let role of this.tableModel.roles) {
        if (role.roleName === 'PROFESSOR') {
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

  getPage(pageNumber: number) {
    this.loadingService.show();
    console.log(pageNumber);
    console.log('id,' + (this.sortDirection ? 'desc' : 'asc'));
    this.subscriptions.push(this.tableModelService.getPageObservable<ProfessorAccount>(
      RequestHelper.PROFESSOR,
      pageNumber - 1,
      this.itemsPerPage,
      'id,' + (this.sortDirection ? 'desc' : 'asc'))
      .subscribe(req => {
        this.professorPage = req as Page<ProfessorAccount>;
        this.professorPage.number += 1;
        this.loadingService.hide();
      }));
  }
}

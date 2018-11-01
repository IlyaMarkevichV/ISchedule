import {Component, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import {ProfessorAccount} from "../../model/professor-account";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Subscription} from "rxjs";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {ProfessorAccountService} from "../../service/professor-account.service";

@Component({
  selector: 'app-professor',
  templateUrl: './professor.component.html',
  styleUrls: ['./professor.component.css']
})
export class ProfessorComponent implements OnInit, OnDestroy {

  public professorAccounts: ProfessorAccount[];
  private modalRef: BsModalRef;

  public editMode: boolean = false;

  public editableProfessor: ProfessorAccount = new ProfessorAccount();

  private subscriptions: Subscription[] = [];

  // Dependency injection
  constructor(private loadingService: Ng4LoadingSpinnerService,
              private professorAccountService: ProfessorAccountService,
              private modalService: BsModalService) {
  }

  ngOnInit() {
    this.loadProfessorAccounts();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  loadProfessorAccounts(): void {
    this.loadingService.show();

    // wait for GET response and make it as array.
    this.subscriptions.push(this.professorAccountService.getProfessorAccounts().subscribe(accounts => {
      this.professorAccounts = accounts as ProfessorAccount[];
      console.log(accounts);
      this.loadingService.hide();
    }));
  }

  addProfessorAccount(): void {
    this.loadingService.show();
    this.subscriptions.push(this.professorAccountService.saveProfessorAccount(this.editableProfessor).subscribe(() => {
      this.updateProfessorAccounts();
      this.closeModal();
      this.loadingService.hide();
      this.refreshEditableProfessor();
    }));
  }

  deleteProfessorAccount(professorAccountId: string): void {
    this.loadingService.show();
    this.subscriptions.push(this.professorAccountService.deleteProfessorAccount(professorAccountId).subscribe(() => {
      this.updateProfessorAccounts();
      this.loadingService.hide();
    }));
  }

  updateProfessorAccounts(): void {
    this.loadProfessorAccounts();
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
}

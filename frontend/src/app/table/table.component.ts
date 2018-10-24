import {Component, OnInit} from "@angular/core";
import {StudentAccount} from "../model/student-account";
import {StudentAccountService} from "../service/student-account.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TabDirective} from "ngx-bootstrap/tabs";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  public value: string;
  public studentAccounts: StudentAccount[];

  private subscriptions: Subscription[] = [];

  // Dependency injection
  constructor(private loadingService: Ng4LoadingSpinnerService,
              private studentAccountService: StudentAccountService) {
  }

  ngOnInit() {
    this.loadStudentAccounts();
  }

  onSelect(data: TabDirective) {
    this.loadingService.show();
    setTimeout(() => {
      this.loadingService.hide();
    }, 2500);
    this.value = data.heading;
  }

  private loadStudentAccounts(): void {
    this.loadingService.show();
    // Get data from StudentAccountService
    this.subscriptions.push(this.studentAccountService.getStudentAccounts().subscribe(accounts => {
      // Parse json responce into local array
      this.studentAccounts = accounts as StudentAccount[];
      // Check data in console
      // consoLe.log(this.studentAccounts);
      this.loadingService.hide();
    }));
  }
}

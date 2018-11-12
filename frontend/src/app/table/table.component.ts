import {Component, OnDestroy, OnInit, ViewChild} from "@angular/core";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TableModel} from "../model/TableModel";
import {TableModelService} from "../service/table-model.service";
import {Subscription} from "rxjs";
import {Faculty} from "../model/faculty";

@Component({
  selector: 'table-component',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit, OnDestroy {

  private subscriptions: Subscription[] = [];

  public tableModel: TableModel;

  public temp: string;

  constructor(
    private loadingService: Ng4LoadingSpinnerService,
    private tableModelService: TableModelService) {

    this.tableModel = new TableModel();
  }

  ngOnInit() {
    this.loadFaculties();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  public loadFaculties(): void {
    this.loadingService.show();
    this.subscriptions.push(this.tableModelService.getFaculties().subscribe(faculties => {
      this.tableModel.faculties = faculties as Faculty[];
      console.log(this.tableModel.faculties);
      this.loadingService.hide();
    }))
  }

  public qwe(): void  {
    this.temp = 'qweqweqweqwe';
  }
}

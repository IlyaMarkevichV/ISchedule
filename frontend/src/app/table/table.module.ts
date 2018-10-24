import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableComponent } from './table.component';
import {Ng4LoadingSpinnerModule} from "ng4-loading-spinner";
import {AlertModule, TabsModule} from 'ngx-bootstrap';

@NgModule({
  imports: [
    Ng4LoadingSpinnerModule.forRoot(),
    CommonModule,
    AlertModule.forRoot(),
    TabsModule.forRoot()
  ],
  declarations: [
    TableComponent
  ],
  exports: [
    TableComponent
  ]
})
export class TableModule { }

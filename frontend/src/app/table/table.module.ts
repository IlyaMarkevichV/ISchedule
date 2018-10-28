import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableComponent } from './table.component';
import {Ng4LoadingSpinnerModule} from "ng4-loading-spinner";
import {AlertModule, ModalModule, TabsModule} from 'ngx-bootstrap';
import { FormsModule } from '@angular/forms';
import {FilterPipeModule} from "ngx-filter-pipe";
import { StudentComponent } from './student/student.component';
import { ProfessorComponent } from './professor/professor.component';

@NgModule({
  imports: [
    FilterPipeModule,
    FormsModule,
    Ng4LoadingSpinnerModule.forRoot(),
    CommonModule,
    AlertModule.forRoot(),
    TabsModule.forRoot(),
    ModalModule.forRoot()
  ],
  declarations: [
    TableComponent,
    StudentComponent,
    ProfessorComponent
  ],
  exports: [
    TableComponent
  ]
})
export class TableModule { }

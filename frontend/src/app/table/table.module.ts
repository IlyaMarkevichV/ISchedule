import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableComponent } from './table.component';
import {Ng4LoadingSpinnerModule} from "ng4-loading-spinner";
import {AlertModule, BsDropdownModule, ModalModule, TabsModule} from 'ngx-bootstrap';
import { FormsModule } from '@angular/forms';
import {FilterPipeModule} from "ngx-filter-pipe";
import { StudentComponent } from './student/student.component';
import { ProfessorComponent } from './professor/professor.component';
import { GroupComponent } from './group/group.component';
import { AccordionModule } from 'ngx-bootstrap/accordion';
import { TypeaheadModule } from 'ngx-bootstrap/typeahead';

@NgModule({
  imports: [
    FilterPipeModule,
    FormsModule,
    Ng4LoadingSpinnerModule.forRoot(),
    CommonModule,
    AlertModule.forRoot(),
    TabsModule.forRoot(),
    ModalModule.forRoot(),
    AccordionModule.forRoot(),
    BsDropdownModule.forRoot(),
    TypeaheadModule.forRoot()
  ],
  declarations: [
    TableComponent,
    StudentComponent,
    ProfessorComponent,
    GroupComponent
  ],
  exports: [
    TableComponent
  ]
})
export class TableModule { }

import { NgModule } from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import { TableComponent } from './table.component';
import {Ng4LoadingSpinnerModule} from "ng4-loading-spinner";
import {AlertModule, BsDropdownModule, DatepickerModule, ModalModule, TabsModule} from 'ngx-bootstrap';
import { FormsModule} from '@angular/forms';
import {FilterPipeModule} from "ngx-filter-pipe";
import { StudentComponent } from './student/student.component';
import { ProfessorComponent } from './professor/professor.component';
import { GroupComponent } from './group/group.component';
import { AccordionModule } from 'ngx-bootstrap/accordion';
import { TypeaheadModule } from 'ngx-bootstrap/typeahead';
import { BsDatepickerModule } from 'ngx-bootstrap';

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
    TypeaheadModule.forRoot(),
    BsDatepickerModule.forRoot(),
    AlertModule.forRoot()
  ],
  declarations: [
    TableComponent,
    StudentComponent,
    ProfessorComponent,
    GroupComponent
  ],
  exports: [
    TableComponent
  ],
  providers: [DatePipe]
})
export class TableModule { }

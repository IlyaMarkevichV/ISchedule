import {NgModule} from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {TableComponent} from './table.component';
import {Ng4LoadingSpinnerModule} from "ng4-loading-spinner";
import {AlertModule, BsDropdownModule, ModalModule, TabsModule} from 'ngx-bootstrap';
import {FormsModule} from '@angular/forms';
import {FilterPipeModule} from "ngx-filter-pipe";
import {AccordionModule} from 'ngx-bootstrap/accordion';
import {TypeaheadModule} from 'ngx-bootstrap/typeahead';
import {BsDatepickerModule} from 'ngx-bootstrap';
import {NgxPaginationModule} from 'ngx-pagination';
import {AdministratorComponent} from "./administrator/administrator.component";
import {GroupTabComponent} from "./administrator/group-tab/group-tab.component";
import {ProfessorTabComponent} from "./administrator/professor-tab/professor-tab.component";
import {StudentTabComponent} from "./administrator/student-tab/student-tab.component";
import { StudentComponent } from './student/student.component';
import { ProfessorComponent } from './professor/professor.component';
import { ProfessorProfileComponent } from './professor/professor-profile/professor-profile.component';
import { ProfessorSubjectComponent } from './professor/professor-subject/professor-subject.component';
import { ProfessorGroupScheduleComponent } from './professor/professor-group-schedule/professor-group-schedule.component';
import { ProfessorGroupInfoComponent } from './professor/professor-group-info/professor-group-info.component';
import {AppRoutingModule} from "../app-routing.module";
import { ScheduleTabComponent } from './administrator/schedule-tab/schedule-tab.component';



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
    AlertModule.forRoot(),
    NgxPaginationModule,
    AppRoutingModule
  ],
  declarations: [
    TableComponent,
    GroupTabComponent,
    ProfessorTabComponent,
    StudentTabComponent,
    AdministratorComponent,
    StudentComponent,
    ProfessorComponent,
    ProfessorProfileComponent,
    ProfessorSubjectComponent,
    ProfessorGroupScheduleComponent,
    ProfessorGroupInfoComponent,
    ScheduleTabComponent
  ],
  exports: [
    TableComponent,
  ],
  providers: [DatePipe]
})
export class TableModule {
}

import {NgModule} from '@angular/core';
import {PreloadAllModules, RouterModule, Routes} from '@angular/router';

import {StudentComponent} from "./table/student/student.component";
import {ProfessorComponent} from "./table/professor/professor.component";
import {AdministratorComponent} from "./table/administrator/administrator.component";
import {TableComponent} from "./table/table.component";
import {AuthorizationComponent} from "./authorization/authorization.component";


const routes: Routes = [
  /*  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
    { path: 'dashboard', component: DashboardComponent },
    { path: 'detail/:id', component: HeroDetailComponent },
    { path: 'heroes', component: HeroesComponent }*/
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'login', component: AuthorizationComponent},
  {path: 'administrator/:id', component: TableComponent},
  {path: 'student/:id', component: TableComponent},
  {path: 'professor/:id', component: TableComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

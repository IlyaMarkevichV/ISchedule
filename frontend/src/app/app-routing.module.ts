import {NgModule} from '@angular/core';
import {PreloadAllModules, RouterModule, Routes} from '@angular/router';
import {AuthorizationComponent} from "./authorization/authorization.component";
import {StudentComponent} from "./table/student/student.component";
import {ProfessorComponent} from "./table/professor/professor.component";
import {AdministratorComponent} from "./table/administrator/administrator.component";
import {TableComponent} from "./table/table.component";

const routes: Routes = [
  /*  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
    { path: 'dashboard', component: DashboardComponent },
    { path: 'detail/:id', component: HeroDetailComponent },
    { path: 'heroes', component: HeroesComponent }*/
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'login', component: AuthorizationComponent},
  {path: 'administrator', component: AdministratorComponent},
  {path: 'student', component: StudentComponent},
  {path: 'professor', component: ProfessorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {preloadingStrategy: PreloadAllModules})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

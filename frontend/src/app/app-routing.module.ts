import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {TableComponent} from "./table/table.component";
import {AuthorizationComponent} from "./authorization/authorization.component";


const routes: Routes = [
  /*  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
    { path: 'dashboard', component: DashboardComponent },
    { path: 'detail/:id', component: HeroDetailComponent },
    { path: 'heroes', component: HeroesComponent }*/
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'login', component: AuthorizationComponent},
  {path: 'administrator', component: TableComponent},
  {path: 'student', component: TableComponent},
  {path: 'professor', component: TableComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

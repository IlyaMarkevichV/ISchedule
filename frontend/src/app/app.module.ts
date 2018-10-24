import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {AlertModule, BsDropdownModule, TabsModule} from 'ngx-bootstrap';
import { Ng4LoadingSpinnerModule } from 'ng4-loading-spinner';
import { NavbarComponent } from './navbar/navbar.component';
import {StudentAccountService} from "./service/student-account.service";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {TableModule} from "./table/table.module";

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
  ],
  imports: [
    FormsModule,
    HttpClientModule,
    Ng4LoadingSpinnerModule.forRoot(),
    BsDropdownModule.forRoot(),
    TabsModule.forRoot(),
    AlertModule.forRoot(),
    BrowserModule,
    TableModule
  ],
  providers: [StudentAccountService],
  bootstrap: [AppComponent]
})
export class AppModule { }

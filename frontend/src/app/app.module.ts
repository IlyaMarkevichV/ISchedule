import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {AlertModule, BsDatepickerModule, BsDropdownModule, TabsModule} from 'ngx-bootstrap';
import { Ng4LoadingSpinnerModule } from 'ng4-loading-spinner';
import { NavbarComponent } from './navbar/navbar.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {TableModule} from "./table/table.module";
import { AppRoutingModule } from './app-routing.module';
import {adapterFactory} from "angular-calendar/date-adapters/date-fns";
import {CalendarModule, DateAdapter} from "angular-calendar";
import { AuthorizationComponent } from './authorization/authorization.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    AuthorizationComponent
  ],
  imports: [
    FormsModule,
    HttpClientModule,
    Ng4LoadingSpinnerModule.forRoot(),
    BsDropdownModule.forRoot(),
    TabsModule.forRoot(),
    AlertModule.forRoot(),
    BrowserModule,
    TableModule,
    BsDatepickerModule.forRoot(),
    ReactiveFormsModule,
    AppRoutingModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory
    }),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

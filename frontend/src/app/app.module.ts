import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {AlertModule, BsDatepickerModule, BsDropdownModule, TabsModule} from 'ngx-bootstrap';
import { Ng4LoadingSpinnerModule } from 'ng4-loading-spinner';
import { NavbarComponent } from './navbar/navbar.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {TableModule} from "./table/table.module";
import { AppRoutingModule } from './app-routing.module';
import {adapterFactory} from "angular-calendar/date-adapters/date-fns";
import {CalendarModule, DateAdapter} from "angular-calendar";
import { AuthorizationComponent } from './authorization/authorization.component';
import {Interceptor} from "./service/interceptor.service";
import {TokenStorage} from "./service/token-storage.service";
import { MiniScheduleTableComponent } from './share/mini-schedule-table/mini-schedule-table.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    AuthorizationComponent,
    MiniScheduleTableComponent
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
  providers: [
    Interceptor,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi: true
    },
    TokenStorage
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

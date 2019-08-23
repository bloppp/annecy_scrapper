import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './component/app.component';
import { LeftBarComponent } from './component/left-bar/left-bar.component';
import { CalendarComponent } from './component/calendar/calendar.component';
import { ListboxModule } from 'primeng/listbox';
import { FormsModule } from '@angular/forms'
import { CategoryService } from './api/category.service';
import { AuthService } from './shared/auth.service';
import { CookiesStorageService } from 'ngx-store';
import { HttpClientModule } from '@angular/common/http';
import { UserService } from './api/user.service';
import { SessionService } from './api/session.service';


@NgModule({
  declarations: [
    AppComponent,
    LeftBarComponent,
    CalendarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ListboxModule,
    FormsModule,
    CardModule,
    ButtonModule,
    HttpClientModule
  ],
  providers: [
    CategoryService,
    AuthService,
    UserService,
    SessionService,
    CookiesStorageService,
    HttpClientModule],
  bootstrap: [AppComponent]
})
export class AppModule { }

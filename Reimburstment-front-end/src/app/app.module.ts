import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HttpModule } from '@angular/http';
import { FormsModule }   from '@angular/forms';
import {RouterModule, Router} from '@angular/router';

import { LoginFormComponent } from './login-form/login-form.component';
import { NavbarComponent } from './navbar/navbar.component';
import { NewRequestComponent } from './new-request/new-request.component';
import { MyRequestComponent } from './my-request/my-request.component';
import { HomeComponent } from './home/home.component';
import { LoginService } from './services/login.service';
import { EmployeeRequestService } from './services/employee-request.service';
import {ManagerRequestService } from './services/manager-request.service';
import { ViewRequestComponent } from './view-request/view-request.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    NavbarComponent,
    NewRequestComponent,
    MyRequestComponent,
    HomeComponent,
    ViewRequestComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot([
      {path: 'login', component:LoginFormComponent},
      {path: 'myrequest', component:MyRequestComponent},
      {path: 'newrequest', component:NewRequestComponent},
      {path: 'viewrequest', component:ViewRequestComponent},
      {path: 'profile', component:LoginFormComponent},
      {path: '', component:HomeComponent,pathMatch:'full'},
      {path: '**', component:HomeComponent,pathMatch:'full'},
    ])
  ],
  providers: [LoginService,EmployeeRequestService,ManagerRequestService],
  bootstrap: [AppComponent]
})
export class AppModule { }

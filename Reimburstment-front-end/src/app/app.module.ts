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

@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    NavbarComponent,
    NewRequestComponent,
    MyRequestComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot([
      {path: 'login', component:LoginFormComponent},
      {path: 'myrequest', component:MyRequestComponent},
      {path: 'newrequest', component:NewRequestComponent},
      {path: 'profile', component:LoginFormComponent},
      {path: '', component:LoginFormComponent,pathMatch:'full'},
      {path: '**', component:LoginFormComponent,pathMatch:'full'},
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

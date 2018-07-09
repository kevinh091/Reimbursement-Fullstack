import { Component, OnInit } from '@angular/core';
import {LoginService} from '../services/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  title:string = 'Reimbursement';
  account: string;
  get login() {
    return this._login;
  }

  constructor(private _login :LoginService){  //invalid
    
  }

}

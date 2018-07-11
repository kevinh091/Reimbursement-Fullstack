import { Component, OnInit } from '@angular/core';
import {LoginService} from '../services/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit{
  title:string = 'Reimbursement';
  get login() {
    return this._login;
  }

  constructor(private _login :LoginService){  //invalid
    
  }

  async ngOnInit(){
    await this._login.isLoggedIn();
  }

  async logout(){
    await this._login.logout();
  }

}

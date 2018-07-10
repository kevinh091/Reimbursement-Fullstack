import { Component, OnInit } from '@angular/core';
import {LoginService} from '../services/login.service';

@Component({
  selector: 'app-new-request',
  templateUrl: './new-request.component.html',
  styleUrls: ['./new-request.component.css']
})
export class NewRequestComponent implements OnInit {
  loggedIn:boolean;

  constructor(private _loginService: LoginService) {
  }

  async ngOnInit() {
    await this._loginService.isLoggedIn();
    this.loggedIn = this._loginService.user?true:false;
  }
}

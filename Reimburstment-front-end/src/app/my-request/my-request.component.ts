import { Component, OnInit } from '@angular/core';
import {LoginService} from '../services/login.service';

@Component({
  selector: 'app-my-request',
  templateUrl: './my-request.component.html',
  styleUrls: ['./my-request.component.css']
})
export class MyRequestComponent implements OnInit {
  get loginService(){
    return this._loginService;
  }

  constructor(private _loginService: LoginService) {
  }

  async ngOnInit() {
    await this._loginService.isLoggedIn();
  }
}

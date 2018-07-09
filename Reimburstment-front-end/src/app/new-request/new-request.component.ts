import { Component, OnInit } from '@angular/core';
import {LoginService} from '../services/login.service';

@Component({
  selector: 'app-new-request',
  templateUrl: './new-request.component.html',
  styleUrls: ['./new-request.component.css']
})
export class NewRequestComponent implements OnInit {
  loggedIn:boolean;

  constructor(private loginService: LoginService) {
  }

  async ngOnInit() {
    if(await this.loginService.isLoggedIn()){
      this.loggedIn=true;
    }
    else{
      this.loggedIn=false;
    }
  }

}

import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';
import { EmployeeRequestService } from '../services/employee-request.service';

@Component({
  selector: 'app-my-request',
  templateUrl: './my-request.component.html',
  styleUrls: ['./my-request.component.css']
})
export class MyRequestComponent implements OnInit {
  get loginService() {
    return this._loginService;
  }

  get requestService(){
    return this._requestService;
  }

  constructor(private _loginService: LoginService, private _requestService: EmployeeRequestService) {
  }

  async ngOnInit() {
    await this._loginService.isLoggedIn();
    if (this._loginService.user) {
      await this._requestService.getMyrequests();
    }
  }
}

import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';
import {ManagerRequestService } from '../services/manager-request.service';

@Component({
  selector: 'app-view-request',
  templateUrl: './view-request.component.html',
  styleUrls: ['./view-request.component.css']
})

export class ViewRequestComponent implements OnInit {
  hoveredIndex:number;
  get loginService() {
    return this._loginService;
  }

  get requestService(){
    return this._requestService;
  }

  constructor(private _loginService: LoginService, private _requestService: ManagerRequestService) {
  }

  async ngOnInit() {
    await this._loginService.isLoggedIn();
    if (this._loginService.user) {
      await this._requestService.viewRequest('1');
    }
  }

  highlight(event:MouseEvent){
    event.srcElement.setAttribute('style','background-color:rgb(225, 225, 225);');
  }
  dehighlight(event:MouseEvent){
    event.srcElement.removeAttribute("style");
  }

  go(){
    this._requestService.updateRequest(102,2);
  }
}

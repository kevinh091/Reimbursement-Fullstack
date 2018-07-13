import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';
import {ManagerRequestService } from '../services/manager-request.service';
import { DescriptionDialogComponent } from '../description-dialog/description-dialog.component';

@Component({
  selector: 'app-view-request',
  templateUrl: './view-request.component.html',
  styleUrls: ['./view-request.component.css']
})

export class ViewRequestComponent implements OnInit {
  hoveredIndex:number;
  openDescription:boolean;
  message:string;
  get loginService() {
    return this._loginService;
  }

  get requestService(){
    return this._requestService;
  }

  constructor(private _loginService: LoginService, private _requestService: ManagerRequestService,
    public dialog:DescriptionDialogComponent) {
  }

  async ngOnInit() {
    await this._loginService.isLoggedIn();
    if (this._loginService.user) {
      await this._requestService.viewRequest('*');
    }
  }
  description(des:string){
    this.message = des;
    this.openDescription = true;
  }
  receiveMessage(event) {
    this.openDescription = false;
  }

  highlight(event:MouseEvent){
    event.srcElement.setAttribute('style','background-color:rgb(225, 225, 225);');
  }
  dehighlight(event:MouseEvent){
    event.srcElement.removeAttribute("style");
  }

  approveDeny(table:MouseEvent,reimbId:number, status:number){
    //console.log(reimbId,status);
    if(this._requestService.updateRequest(reimbId,status)){
      table.srcElement.parentElement.parentElement.remove();
    }
  }

  sort(input){
    this._requestService.sort(input);
  }
}

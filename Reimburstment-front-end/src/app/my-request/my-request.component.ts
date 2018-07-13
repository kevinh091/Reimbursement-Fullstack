import { Component,OnInit} from '@angular/core';
import { LoginService } from '../services/login.service';
import { EmployeeRequestService } from '../services/employee-request.service';
import { DescriptionDialogComponent } from '../description-dialog/description-dialog.component';

@Component({
  selector: 'app-my-request',
  templateUrl: './my-request.component.html',
  styleUrls: ['./my-request.component.css']
})
export class MyRequestComponent implements OnInit {
  openDescription:boolean;
  message:string;

  get loginService() {
    return this._loginService;
  }

  get requestService(){
    return this._requestService;
  }


  constructor(private _loginService: LoginService, 
    private _requestService: EmployeeRequestService,
    public dialog:DescriptionDialogComponent) {
  }

  async ngOnInit() {
    await this._loginService.isLoggedIn();
    if (this._loginService.user) {
      await this._requestService.getMyrequests();
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

  sort(input){
    this._requestService.sort(input);
  }
}

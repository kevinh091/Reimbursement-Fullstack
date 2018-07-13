import { Component, OnInit } from '@angular/core';
import {LoginService} from '../services/login.service';
import { EmployeeRequestService } from '../services/employee-request.service';
import { NgForm } from '../../../node_modules/@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-new-request',
  templateUrl: './new-request.component.html',
  styleUrls: ['./new-request.component.css']
})
export class NewRequestComponent implements OnInit {
  get loginService(){
    return this._loginService;
  }

  constructor(private _loginService: LoginService, private requestService:EmployeeRequestService,private router:Router) {
  }

  async ngOnInit() {
    await this._loginService.isLoggedIn();
  }

  async onSubmit(requestForm:NgForm){
    await this.requestService.newRequest(requestForm.value.amount,requestForm.value.description,requestForm.value.type);
    console.log(requestForm.value.description);
    this.router.navigateByUrl('/myrequest');
  }
}

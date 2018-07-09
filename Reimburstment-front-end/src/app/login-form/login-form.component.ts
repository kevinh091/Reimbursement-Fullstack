import { Component, OnInit} from '@angular/core';
import { NgForm } from '@angular/forms';
import {LoginService} from '../services/login.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit{

  constructor(private loginService: LoginService) { }

  ngOnInit() {
  }

  async onSubmit(loginForm:NgForm){
    await this.loginService.login(loginForm);
    console.log(this.loginService.user);
  }
}



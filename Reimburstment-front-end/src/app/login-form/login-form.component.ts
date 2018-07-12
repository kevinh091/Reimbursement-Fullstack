import { Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import { NgForm } from '@angular/forms';
import {LoginService} from '../services/login.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit{
  get router(){
    return this._router;
  }
  get loginService(){
    return this._loginService;
  }

  constructor(private _loginService: LoginService, private _router:Router) { }

  //check if the user is already logged. If yes, go to home page.(take care of back button)
  async ngOnInit() {
    await this._loginService.isLoggedIn;
    if(this._loginService.user){
      this._router.navigateByUrl('/');
    }
  }

  async onSubmit(loginForm:NgForm){
    await this.loginService.login(loginForm.value.username, loginForm.value.password);
    console.log(this.loginService.user);
  }
}



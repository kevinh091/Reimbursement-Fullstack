import { Injectable } from '@angular/core';
import {User} from '../shared/user';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  user:User; //if not logged in, user is undefined; if logged in, check User interface out;

  constructor(private router: Router) {
  }

  async isLoggedIn(){ //check if client has a session on server 
    const res = await fetch('http://localhost:9005/api/Authentication/login',{credentials: 'include'});
    const js = await res.json();
    this.user = (typeof js.username==='undefined')? this.user:js; 
    return (typeof js.username==='undefined')?false:true;
  }
  
  
  async login(loginForm) {
    const res = await fetch('http://localhost:9005/api/Authentication/login', {
      method: 'POST',
      credentials: 'include',
      headers: {
        "Content-Type": "application/json; charset=utf-8",
      },
      body: `${loginForm.value.username};${loginForm.value.password}`
    });
    const js = await res.json();  //if login not successful, js.username is undefined
    if(typeof js.username!=='undefined'){
      this.user=js;
      this.router.navigateByUrl('/');
    }
  }

  async logout(){
    const res =await fetch('http://localhost:9005/api/Authentication/logout', {
      method: 'GET',
      credentials: 'include',
      mode:"cors"
    });
    this.user = undefined;
    this.router.navigateByUrl('/');
  }
}

import { Component, OnInit, AfterViewInit} from '@angular/core';
import { Http } from '@angular/http';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit,AfterViewInit{

  constructor(private http: Http) { }

  ngAfterViewInit(){
    window.document.body.style.backgroundImage=
    "url('http://mapiraj.me/wp-content/uploads/2018/04/website-background-images-1.jpg')";
  }
  ngOnInit() {
  }

  onSubmit(loginForm:NgForm){
    let opts = {username:loginForm.value.username, password:loginForm.value.password};
    console.log(opts);
    console.log(JSON.stringify(opts));
    fetch('http://localhost:9005/Reimbursement/api/authentication',{
      method: 'POST',
      mode: "cors",
      headers: {
        "Content-Type": "application/json; charset=utf-8",
      },
      body: `${loginForm.value.username};${loginForm.value.password}`
    })
    .then(response=>{
      response.json();  // response in json form
    })
  }
}



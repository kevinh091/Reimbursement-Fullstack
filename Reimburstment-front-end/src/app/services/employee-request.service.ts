import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmployeeRequestService {
  requestsHoler:Array<any>;

  constructor() { }

  async getMyrequests(){
    const res = await fetch('http://localhost:9005/api/Request/my', {credentials: 'include'});
    const js:Array<any> = await res.json();
    this.requestsHoler = js.map(input=>{
      switch(input.status){
        case (1):input.status='Pending';break;
        case (2):input.status='Approved';break;
        case (3):input.status='Denied';break;
      }
      return input;
    });
    console.log(this.requestsHoler);
  }

  async newRequest(amount:number, description:string, type:number) {
    const res = await fetch('http://localhost:9005/api/Request/new', {
      method: 'POST',
      credentials: 'include',
      headers: {
        "Content-Type": "application/json; charset=utf-8",
      },
      body: JSON.stringify({"amount":amount,"description":description,"type":type})
    });
    const js = await res.text();  //if login not successful, js.username is undefined
    if( js ==='yes'){
      console.log('successed');
    }
  }
}

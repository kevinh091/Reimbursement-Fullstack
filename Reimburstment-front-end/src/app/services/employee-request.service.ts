import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmployeeRequestService {

  constructor() { }

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

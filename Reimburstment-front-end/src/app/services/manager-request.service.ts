import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ManagerRequestService {
  requestsHoler:Array<any>;

  constructor() { }

  async viewRequest(input:string) {
    const res = await fetch('http://localhost:9005/api/Request/manager', {
      method: 'POST',
      credentials: 'include',
      headers: {
        "Content-Type": "application/json; charset=utf-8",
      },
      body: input
    });
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

  async updateRequest(reimbId:number, status:number) {
    const res = await fetch('http://localhost:9005/api/Request/update', {
      method: 'POST',
      credentials: 'include',
      headers: {
        "Content-Type": "application/json; charset=utf-8",
      },
      body: `${reimbId};${status}`
    });
    const js:string = await res.text();
    console.log(js);
  }
}

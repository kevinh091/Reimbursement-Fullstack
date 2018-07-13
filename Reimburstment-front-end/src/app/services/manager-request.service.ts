import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ManagerRequestService {
  requestsHoler: Array<any>;

  constructor() { }

  async viewRequest(input: string) {
    const res = await fetch('http://localhost:9005/api/Request/manager', {
      method: 'POST',
      credentials: 'include',
      headers: {
        "Content-Type": "application/json; charset=utf-8",
      },
      body: input
    });
    const js: Array<any> = await res.json();
    this.requestsHoler = js.map(input => {
      switch (input.status) {
        case (1): input.status = 'Pending'; break;
        case (2): input.status = 'Approved'; break;
        case (3): input.status = 'Denied'; break;
      }
      return input;
    });
    console.log(this.requestsHoler);
  }

  async updateRequest(reimbId: number, status: number) {
    const res = await fetch('http://localhost:9005/api/Request/update', {
      method: 'POST',
      credentials: 'include',
      headers: {
        "Content-Type": "application/json; charset=utf-8",
      },
      body: `${reimbId};${status}`
    });
    const js: string = await res.text();
    console.log(js);
    if (js === 'yes') {
      return "yes";
    }
  }

  sorted = { //0: not sorted; 1:in asscending order; -1 in descending order
    id: 0,
    author: 0,
    amount: 0,
    dateC: 0,
    status: 0,
    type:0,
    resolved:0,
    resolver:0,

  }

  sort(on: string) {
    let i:number = this.sorted[on]===1?-1:1;
    this.sorted[on]= this.sorted[on]===0?1:this.sorted[on]*-1;
    if (on === 'author') {
      this.requestsHoler.sort((a, b) => {
        if (a[on].firstname > b[on].firstname) return -1*i;
        if (a[on].firstname < b[on].firstname) return 1*i;
        if (a[on].firstname === b[on].firstname) {
          if (a[on].lastname > b[on].lastname) return -1*i;
          if (a[on].lastname < b[on].lastname) return 1*i;
        }
        return 0;
      });
    }else{
      this.requestsHoler.sort((a, b) => {
        if (a[on] > b[on]) {
          if(a===undefined) return -1*i;
          if(b===undefined) return 1*i;
          return -1*i;
        };
        if (a[on] < b[on]) {
          if(a===undefined) return -1*i;
          if(b===undefined) return 1*i;
          return 1*i;
        };
        return 0;
      });
    }

  }
}

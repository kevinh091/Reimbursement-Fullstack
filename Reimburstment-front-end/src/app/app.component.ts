import { Component,AfterViewInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit{

  ngAfterViewInit(){
    window.document.body.style.backgroundImage=
    "url('http://mapiraj.me/wp-content/uploads/2018/04/website-background-images-1.jpg')";
  }
}

import { HttpClient } from '@angular/common/http';
import { AppService } from './../app.service';
import { Component, OnInit } from '@angular/core';

@Component({
  templateUrl: './home.component.html',
})
export class HomeComponent {

  title = 'Demo';
  greeting = {};
  
  constructor(private app: AppService, private http: HttpClient) {
   }

  authenticated() { return this.app.authenticated }

}

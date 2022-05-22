import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-error-page',
  templateUrl: './error-page.component.html',
  styleUrls: ['./error-page.component.scss']
})
export class ErrorPageComponent implements OnInit {

  isLoggedIn$!: Observable<boolean>;

  constructor(public auth: AuthenticationService) { }

  ngOnInit(){
    this.isLoggedIn$ = this.auth.isLoggedIn;
  }

}

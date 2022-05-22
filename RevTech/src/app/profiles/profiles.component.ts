import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { faUserAstronaut, faUserSecret } from '@fortawesome/free-solid-svg-icons';
import { AuthenticationService } from '../services/authentication.service';
import { User } from '../dto/user';

@Component({
  selector: 'app-profiles',
  templateUrl: './profiles.component.html',
  styleUrls: ['./profiles.component.scss']
})
export class ProfilesComponent implements OnInit {

  constructor(public auth: AuthenticationService) { }
  public faUserAstronaut = faUserAstronaut;
  public faUserSecret = faUserSecret;

  // data
  public firstName = sessionStorage.getItem('firstname');
  public lastName = sessionStorage.getItem('lastname');
  public name = this.firstName + ' ' + this.lastName;
  public username = sessionStorage.getItem('username');
  public password = sessionStorage.getItem('password');
  public email = sessionStorage.getItem('email');
  public phone = sessionStorage.getItem('phone');

  ngOnInit(): void {
  }

}

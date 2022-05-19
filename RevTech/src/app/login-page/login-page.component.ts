import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { faFaceRollingEyes } from '@fortawesome/free-solid-svg-icons';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})

export class LoginPageComponent implements OnInit {

  public faFaceRollingEyes = faFaceRollingEyes;

  constructor(private auth: AuthenticationService) { }

  ngOnInit(): void {
  }

  public login(){
    this.auth.login();
  }

  // variable - default false
  show: boolean = false;
  // click event function toggle
  password() {
    this.show = !this.show;
  }
}

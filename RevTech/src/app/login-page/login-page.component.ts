import { Component, OnInit } from '@angular/core';
import { faFaceRollingEyes } from '@fortawesome/free-solid-svg-icons';
import { NgForm } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';


@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})

export class LoginPageComponent implements OnInit {

  constructor(private authService: AuthenticationService) {}

  ngOnInit(): void {
    sessionStorage.clear();
  }

  public faFaceRollingEyes = faFaceRollingEyes;

  // variable - default false
  show: boolean = false;
  // click event function toggle
  showPassword() {
    this.show = !this.show;
  }

  public loginUser(loginForm: NgForm) {
    this.authService.login(loginForm);
  }
}

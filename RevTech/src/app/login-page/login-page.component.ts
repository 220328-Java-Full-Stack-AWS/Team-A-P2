import { Component, OnInit } from '@angular/core';
import { faFaceRollingEyes } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  public faFaceRollingEyes = faFaceRollingEyes;

  constructor() { }

  ngOnInit(): void {
  }

  // variable - default false
  show: boolean = false;
  // click event function toggle
  password() {
    this.show = !this.show;
  }
}

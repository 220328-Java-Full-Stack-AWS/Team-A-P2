import { Component, OnInit } from '@angular/core';
import { faFaceRollingEyes } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.scss']
})
export class RegisterPageComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }


  public faFaceRollingEyes = faFaceRollingEyes;
  // variable - default false
  show: boolean = false;
  show2: boolean = false;
  // click event function toggle
  password() {
    this.show = !this.show;
  }
  confirmPassword() {
    this.show2 = !this.show2;
  }
}

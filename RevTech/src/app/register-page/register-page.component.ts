import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { faFaceRollingEyes } from '@fortawesome/free-solid-svg-icons';
import { User } from '../dto/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.scss']
})
export class RegisterPageComponent implements OnInit {

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }
  // Back end work
  public registerUser(registerForm: NgForm): void {
    document.getElementById('register-btn')?.click();
    this.userService.registerUser(registerForm.value).subscribe(
      (response: User) => {
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      },
    );
  }

  // Front end fun
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

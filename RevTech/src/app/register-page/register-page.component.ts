import { Component, OnInit } from '@angular/core';
import { faFaceRollingEyes } from '@fortawesome/free-solid-svg-icons';
import { NgForm } from '@angular/forms';
import { User } from '../dto/user';
import { UserService } from '../services/user.service';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';


@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.scss']
})
export class RegisterPageComponent implements OnInit {

  constructor(private userService: UserService,  private router: Router) { }

  ngOnInit(): void {
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

  public registerUser(registerForm: NgForm): void {
    this.userService.registerUser(registerForm.value).subscribe(
      (response: User) => {
        console.log(response);
        this.router.navigateByUrl('/login');
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      },
    );
  }
}

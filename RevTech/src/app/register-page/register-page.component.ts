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

  private completeRegistrationCheck(registerForm: NgForm, validation: boolean){
    if((registerForm.value.password == registerForm.value.confirmPassword) && validation){
      this.userService.registerUser(registerForm.value).subscribe(
        (response: User) => {
          console.log(response);
          alert("Registration successful!");
          this.router.navigateByUrl('/login');
        },
        (error: HttpErrorResponse) => {
          alert("Registration unsuccessful");
        },
      );
    } else if((registerForm.value.password != registerForm.value.confirmPassword)) {
      let invpass = document.getElementById("nomatchpass");
      if(invpass != null){
        invpass.style.display = "flex";
      }
    }
  }

  private checkEmail(registerForm: NgForm, validation: boolean){
    let invemail = document.getElementById("emailused");
    this.userService.getUserByEmail(registerForm.value.email).subscribe(
      (response: User) => {
        if (invemail != null) {
          validation = false;
          invemail.style.display = "flex";
          this.completeRegistrationCheck(registerForm, validation);
        }
      },
      (error: HttpErrorResponse) => {
        this.completeRegistrationCheck(registerForm, validation);
      },
    );
  }

  private checkPhone(registerForm: NgForm, validation: boolean){
    let invphone = document.getElementById("phoneused");
      this.userService.getUserByPhone(registerForm.value.phone).subscribe(
        (response: User) => {
          if (invphone != null) {
            validation = false;
            invphone.style.display = "flex";
            this.checkEmail(registerForm, validation);
          }
        },
        (error: HttpErrorResponse) => {
          this.checkEmail(registerForm, validation);
        },
      ); 
  }

  public registerUser(registerForm: NgForm): void {
    let invpass = document.getElementById("nomatchpass");
    let invuser = document.getElementById("usernametaken");
    let invphone = document.getElementById("phoneused");
    let invemail = document.getElementById("emailused");
    let validation: boolean = true;

    if(invpass != null){
      invpass.style.display = "none";
    }
    if(invuser != null){
      invuser.style.display = "none";
    }
    if(invphone != null){
      invphone.style.display = "none";
    }
    if(invemail != null){
      invemail.style.display = "none";
    }

    this.userService.getUserByUsername(registerForm.value.username).subscribe(
      (response: User) => {
        if (invuser != null) {
          validation = false;
          invuser.style.display = "flex";
          this.checkPhone(registerForm, validation);
        }
      },
      (error: HttpErrorResponse) => {
        this.checkPhone(registerForm, validation);
      },
    );
  } 
}

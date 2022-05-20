import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { faFaceRollingEyes } from '@fortawesome/free-solid-svg-icons';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { User } from '../dto/user';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})

export class LoginPageComponent implements OnInit {

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    sessionStorage.clear();
  }

    public faFaceRollingEyes = faFaceRollingEyes;
    // variable - default false
    show: boolean = false;
    // click event function toggle
    password() {
      this.show = !this.show;
    }

    public loginUser(loginForm: NgForm){

      let user : User = {
        userId: 0,
        username: loginForm.value.usern,
        email: "",
        password: loginForm.value.password,
        firstName: "",
        lastName: "",
        phone: "",
        address: {
          addressId: 0,
          address: "",
          city: "",
          state: "",
          zip: 0,
          country: "",
          userId: 0
        },
        payment: {
          paymentId: 0,
          cardNumber: 0,
          expirationDate: "",
          cvc: 0,
          userId: 0
        }
      }

      console.log(user);

      this.userService.loginUser(user).subscribe(
        (response: User) => {
          user = response;
          sessionStorage.setItem('userid', user.userId.toString());
          sessionStorage.setItem('username', user.username);
          sessionStorage.setItem('email', user.email);
          sessionStorage.setItem('password', user.password);
          sessionStorage.setItem('firstname', user.firstName);
          sessionStorage.setItem('lastname', user.lastName);
          sessionStorage.setItem('phonenumber', user.phone);
          
          this.router.navigateByUrl('/shop');
        },
        (error: HttpErrorResponse) => {
          console.log(error.message)
        }
      );
    }
}

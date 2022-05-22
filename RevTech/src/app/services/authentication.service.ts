import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../dto/user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {


  constructor( private router: Router, private http: HttpClient) { }


  private apiServiceUrl = environment.apiBaseUrl;

  public username =  new BehaviorSubject<any>(sessionStorage.getItem('username'));

  public loggedIn = new BehaviorSubject<boolean>(this.checkLoginStatus());

  get isLoggedIn(){
    return this.loggedIn.asObservable();
  }

  checkLoginStatus() : boolean {
    var loginCookie = sessionStorage.getItem('LoggedIn');
    if(loginCookie == "1"){
      return true;
    }else{
    return false;
    }
  }

  public login(loginForm: NgForm):void{
    let user : User = {
      userId: 0,
      username: loginForm.value.username,
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
        country: ""
      },
      payment: {
        paymentId: 0,
        cardNumber: 0,
        expirationDate: "",
        cvc: 0
      }
    }

    this.Login(user).subscribe(
      (response: User) => {
        user = response;
        sessionStorage.setItem('userid', user.userId.toString());
        sessionStorage.setItem('username', user.username);
        sessionStorage.setItem('email', user.email);
        sessionStorage.setItem('password', user.password);
        sessionStorage.setItem('firstname', user.firstName);
        sessionStorage.setItem('lastname', user.lastName);
        sessionStorage.setItem('phone', user.phone);

        this.loggedIn.next(true);
        this.router.navigateByUrl('/shop');

        sessionStorage.setItem('LoggedIn', '1');

        this.username.next(sessionStorage.getItem('username'));

      },
      (error: HttpErrorResponse) => {
        const message = document.getElementById('invalid');
        message?.classList.toggle('show');
        console.log(error);
      }
    )
  }

  private Login(user: User): Observable<User>{
    return this.http.post<User>(
      `${this.apiServiceUrl}/users`,// url
      user, // object being passed
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'mode': 'login'
        })
      }
    );
  }

  public logout(){
    this.loggedIn.next(false);
    this.router.navigateByUrl('/login');
    sessionStorage.clear();
  }
}

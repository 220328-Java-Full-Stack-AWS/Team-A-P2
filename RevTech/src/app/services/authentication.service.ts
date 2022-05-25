import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Address } from '../dto/address';
import { User } from '../dto/user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  constructor(private router: Router, private http: HttpClient) { }


  private apiServiceUrl = environment.apiBaseUrl;

  public username = new BehaviorSubject<any>(sessionStorage.getItem('username'));
  public userId = new BehaviorSubject<number>(parseInt(sessionStorage.getItem('username')!));

  public loggedIn = new BehaviorSubject<boolean>(this.checkLoginStatus());

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }

  checkLoginStatus(): boolean {
    var loginCookie = sessionStorage.getItem('LoggedIn');
    if (loginCookie == "1") {
      return true;
    } else {
      return false;
    }
  }

  public login(loginForm: NgForm): void {
    let user: User = {
      userId: 0,
      username: loginForm.value.username,
      email: "",
      password: loginForm.value.password,
      firstName: "",
      lastName: "",
      phone: "",
    }

    this.Login(user).subscribe(
      (response: User) => {
        user = response;
        sessionStorage.setItem('userid', response.userId.toString());
        sessionStorage.setItem('username', response.username);
        sessionStorage.setItem('email', response.email);
        sessionStorage.setItem('firstname', response.firstName);
        sessionStorage.setItem('lastname', response.lastName);
        sessionStorage.setItem('phone', response.phone);
        sessionStorage.setItem('password', response.password);

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

  private Login(user: User): Observable<User> {
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

  public logout() {
    this.loggedIn.next(false);
    this.router.navigateByUrl('/login');
    sessionStorage.clear();
  }
}

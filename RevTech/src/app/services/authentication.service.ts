import { Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../dto/user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor( private router: Router) { }

  public loggedIn = new BehaviorSubject<boolean>(false);

  get isLoggedIn(){
    return this.loggedIn.asObservable();
  }

  public login(user: User){
      this.loggedIn.next(true);
      this.router.navigateByUrl('/shop');
  }

  public logout(){
    this.loggedIn.next(false);
    this.router.navigateByUrl('/login');
  }

}

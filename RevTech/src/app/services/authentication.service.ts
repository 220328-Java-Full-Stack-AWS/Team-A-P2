import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor( private router: Router) { }

  public loggedIn = false;

  public login(){
    this.loggedIn = true;
    this.router.navigateByUrl('/shop');
  }

  public logout(){
    this.loggedIn = false;
    this.router.navigateByUrl('/login');
  }

}

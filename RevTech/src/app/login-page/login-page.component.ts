import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { faFaceRollingEyes } from '@fortawesome/free-solid-svg-icons';
import { User } from '../dto/user';
import { UserService } from '../services/user.service';

import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})

export class LoginPageComponent implements OnInit {

  public faFaceRollingEyes = faFaceRollingEyes;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  public login(){
    sessionStorage.setItem('username', 'leobarriento02');
    this.router.navigateByUrl('/shop');
  }

  // variable - default false
  show: boolean = false;
  // click event function toggle
  password() {
    this.show = !this.show;
  }
}

import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { faFaceAngry } from '@fortawesome/free-solid-svg-icons';
import { User } from '../dto/user';
import { AuthenticationService } from '../services/authentication.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-orders-page',
  templateUrl: './orders-page.component.html',
  styleUrls: ['./orders-page.component.scss']
})
export class OrdersPageComponent implements OnInit {

  constructor(public auth: AuthenticationService, public userService: UserService,) { }

  public data: any = {};

  ngOnInit(): void {
    this.getUserData();
  }

  public faFaceAngry = faFaceAngry;

  public getUserData(){
    this.userService.getUserByUsername(this.auth.username.value).subscribe(
      (response: User) => {
        this.data = response;
        console.log(this.data);
      },
      (error: HttpErrorResponse) => {
        console.log(error.message)
      }
    )
  }


}

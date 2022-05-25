import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { faFaceAngry } from '@fortawesome/free-solid-svg-icons';
import { AuthenticationService } from '../services/authentication.service';
import { OrderService } from '../services/order.service';
import { SaleService } from '../services/sale.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-orders-page',
  templateUrl: './orders-page.component.html',
  styleUrls: ['./orders-page.component.scss']
})
export class OrdersPageComponent implements OnInit {

  constructor(public auth: AuthenticationService, public userService: UserService) { }

  public data: any = {};
  public username = sessionStorage.getItem('username');

  ngOnInit(): void {
    this.getUserData();
  }

  public faFaceAngry = faFaceAngry;
  public shippmentStatus: string = "Shipped";
  public getUserData() {
    this.userService.getUserByUsername(this.username).subscribe(
      (response: any) => {
        this.data = response;
        console.log(this.data);
      },
      (error: HttpErrorResponse) => {
        console.log(error.message)
      }
    )
  }
}

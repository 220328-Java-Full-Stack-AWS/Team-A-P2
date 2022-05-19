import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { faCartPlus } from '@fortawesome/free-solid-svg-icons';
import { Order } from "../dto/order";
import { Sale } from "../dto/sale";
import { OrderService } from '../services/order.service';
import { SaleService } from '../services/sale.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {

  // fa icon
  public faCartPlus = faCartPlus;
  public orders!: Order[];

  constructor(private orderService: OrderService, private salesService: SaleService) { }

  ngOnInit(): void {

  }

  public getAllOrders(): void {
    this.orderService.getAllOrders().subscribe(
      (response: Order[]) => {
        this.orders = response;
      },
      (error: HttpErrorResponse) => {
        console.log(error.message)
      }
    )
  }

  public getOrderById(id: number) {

  }

  /*public addToUser(user: User) {

  }*/

  public addToOrder(sale: Sale) {

  }

}



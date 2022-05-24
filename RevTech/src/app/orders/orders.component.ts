import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { faCartPlus } from '@fortawesome/free-solid-svg-icons';
import { Order } from "../dto/order";
import { Sale } from "../dto/sale";
import { OrderService, Holder } from '../services/order.service';
import { SaleService } from '../services/sale.service';
import { User } from '../dto/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {

  // fa icon
  public faCartPlus = faCartPlus;
  public orders!: Order[];
  order: Order = {
    orderId: null,
    saleList: []
  };
  sale: Sale = {
    saleId: 0,
    quantity: 0,
    dateOfPurchase: null,
    productDescription: "",
    cost: 0,
    product: {
      productId: 0,
      productName: "",
      productDescription: "",
      productCategory: "",
      productImage: "",
      productStatus: "",
      productPrice: 0,
      productQuantity: 0
    },
  };
  user: User = {
    userId: 0,
    username: "",
    email: "",
    password: "",
    firstName: "",
    lastName: "",
    phone: "",
  };

  currentOrder: Order = this.order;

  constructor(private orderService: OrderService, private salesService: SaleService, private router: Router) { }

  ngOnInit(): void {
    if(sessionStorage.getItem("username") == null){
      this.router.navigateByUrl('/login');
    }
  }

  public checkout(order: Order, user: User) {
    let holder = new Holder(order, this.sale, user);
    this.orderService.persistOrder(holder).subscribe((data: any) => { this.currentOrder = this.order });
  }

  public getOrder(selection: String) {
    if (selection == "all") {
      return this.orderService.getAllOrders(1).subscribe((data: any) => { });
    } else if (selection != null && selection != "") {
      return this.orderService.getOrderById(selection).subscribe((data: any) => { });
    }
    return
  }

  public addToOrder(order: Order, sale: Sale) {
    let holder = new Holder(order, sale, this.user);
    this.orderService.addSaleToOrder(holder).subscribe((data: any) => { this.currentOrder = data });
  }

  public removeFromOrder(order: Order, sale: Sale) {
    let holder = new Holder(order, sale, this.user);
    this.orderService.removeSaleFromOrder(holder).subscribe((data: any) => { this.currentOrder = data });

  }

}



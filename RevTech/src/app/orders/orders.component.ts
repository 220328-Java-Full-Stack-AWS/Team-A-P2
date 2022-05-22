import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { faCartPlus } from '@fortawesome/free-solid-svg-icons';
import { Order } from "../dto/order";
import { Sale } from "../dto/sale";
import { OrderService, Holder } from '../services/order.service';
import { SaleService } from '../services/sale.service';
import { User } from '../dto/user';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {

  // fa icon
  public faCartPlus = faCartPlus;
  public orders!: Order[];
  public sales!: Sale[];
  public currentSales!: Sale[];
  public saleToAdd!: Sale;


  order: Order = {
    orderId: null,
    saleList: []
  };
  sale: Sale = {
    saleId: 0,
    quantity: 0,
    dateOfPurchase: null,
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
    address: {
      addressId: 0,
      address: "",
      city: "",
      state: "",
      zip: 0,
      country: "",
    },
    payment: {
      paymentId: 0,
      cardNumber: 0,
      expirationDate: null,
      cvc: 0
    },
  };



  currentOrder: Order = this.order;

  constructor(private orderService: OrderService, private salesService: SaleService) {

  }

  ngOnInit(): void {
    this.salesService.setOrderFunction(this.addToOrder);
    console.log("function registered");
  }





  public checkout(order: Order, user: User) {
    let holder = new Holder(order, this.sale, user);
    this.orderService.persistOrder(holder).subscribe((data: Order) => {
      this.currentOrder = this.order;
      this.currentSales = this.sales;
    });
  }

  public getOrder(selection: String) {
    if (selection == "all") {
      return this.orderService.getAllOrders(1).subscribe((data: Order[]) => { this.orders = data });
    } else if (selection != null && selection != "") {
      return this.orderService.getOrderById(selection).subscribe((data: Order) => {
        this.currentOrder = data;
        this.currentSales = data.saleList;

      });
    }
    return
  }

  public addToOrder(order: Order, sale: Sale) {
    let holder = new Holder(order, sale, this.user);
    this.orderService.addSaleToOrder(holder).subscribe((data: Order) => {
      this.currentOrder = data;
      this.currentSales = data.saleList;
      console.log("Add to Order Finished");
    });
  }

  public removeFromOrder(order: Order, sale: Sale) {
    let holder = new Holder(order, sale, this.user);
    this.orderService.removeSaleFromOrder(holder).subscribe((data: Order) => {
      this.currentOrder = data;
      this.currentSales = data.saleList;
    });

  }

}



import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../dto/product';
import { Sale } from '../dto/sale';
import { Order } from '../dto/order';
import { User } from '../dto/user';
import { OrderService, Holder } from './order.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SaleService {

  private apiServiceUrl = environment.apiBaseUrl;

  orderFunction: Function = this.addToOrder; //() => { console.log("This function is empty!") };
  orderRemoveFunction: Function = this.removeFromOrder;
  public orders!: Order[];
  public sales!: Sale[];
  public currentSales!: Sale[];
  public saleToAdd!: Sale;
  public orderTotal!: number;

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
  setOrderFunction(_function: Function) {
    this.orderFunction = _function;
  }

  invokeOrderFunction(order: any, sale: any, mode: string): Order {
    if (mode == "add") {
      order = this.orderFunction(order, sale);
    } else if (mode == "remove") {
      order = this.orderRemoveFunction(order, sale);
    }
    return order

  }


  getOrderFunction() {
    return this.orderFunction;
  }




  constructor(private http: HttpClient, private orderService: OrderService) { }

  public getSales(): Observable<Sale[]> {
    return this.http.get<any>(`${this.apiServiceUrl}/sale`);
  }

  public addSale(sale: any): Observable<Sale> {
    return this.http.post<Sale>(`${this.apiServiceUrl}/sale/add`, sale);
  }

  public updateSale(sale: Sale): Observable<Sale> {
    return this.http.put<Sale>(`${this.apiServiceUrl}/sale/update`, sale);
  }

  public deleteSale(sale: Sale): Observable<void> {
    return this.http.delete<void>(`${this.apiServiceUrl}/sale/delete`);
  }

  public addToOrder(order: Order, sale: Sale): Order {
    let holder = new Holder(order, sale, this.user);
    this.orderService.addSaleToOrder(holder).subscribe((data: Order) => {
      this.currentOrder = data;
      this.currentSales = data.saleList;
      console.log("Add to Order Finished");
    });
    return this.currentOrder
  }
  public removeFromOrder(order: Order, sale: Sale) {
    let holder = new Holder(order, sale, this.user);
    this.orderService.removeSaleFromOrder(holder).subscribe((data: any) => {
      this.currentOrder = data;
      this.currentSales = data.saleList;
    });

  }

  setCurrentOrder(order: Order) {
    this.currentOrder = order;
  }

  getCurrentOrder() {
    return this.currentOrder
  }
}

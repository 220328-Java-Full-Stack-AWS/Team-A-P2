import { Injectable } from '@angular/core';
import { Sale } from '../dto/sale';
//import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Order } from '../dto/order';
import { User } from '../dto/user';
import { NonNullAssert } from '@angular/compiler';


@Injectable({
  providedIn: 'root'
})
export class OrderService {


  constructor(private http: HttpClient) { }


  public getAllOrders(userId: any) {
    return this.http.get<any>('http://localhost:8080/orders/' + userId, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/json')
        .set('mode', "all")
    });


  }

  public getOrderByUser(userId: any) {
    return this.http.get<Order>('http://localhost:8080/orders/' + userId, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/json')
        .set('mode', "user")
    });

  }

  public getOrderById(id: any) {
    return this.http.get<Order>('http://localhost:8080/orders/' + id);
  }

  public deleteOrder(id: any) {
    return this.http.delete<void>('http://localhost:8080/orders/' + id);
  }


  public persistOrder(holder: any) {
    return this.http.post<Order>('http://localhost:8080/orders', JSON.stringify(holder), {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/json')
    });
  }

  public addSaleToOrder(holder: any) {
    return this.http.put<Order>('http://localhost:8080/orders', JSON.stringify(holder), {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/json')
        .set('mode', "add")
    });
  }

  public removeSaleFromOrder(holder: any) {
    return this.http.put<Order>('http://localhost:8080/orders', JSON.stringify(holder), {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/json')
        .set('mode', "remove")
    });
  }


}
export class Holder {

  order: Order | null;
  sale: Sale;
  user: User;
  constructor(order: Order | null, sale: Sale, user: User) {
    this.order = order;
    this.sale = sale;
    this.user = user;

  }

}

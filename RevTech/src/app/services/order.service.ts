import { Injectable } from '@angular/core';
import { Sale } from '../dto/sale';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private apiServiceUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getAllOrders(): any { //Observable<Order[]> { 

  }

  public getOrderById() {
  }

  public deleteOrder() { }

  public saveOrder() { }
}

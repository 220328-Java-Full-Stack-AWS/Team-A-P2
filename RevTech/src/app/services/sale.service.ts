import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../dto/product';
import { Sale } from '../dto/sale';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SaleService {

  private apiServiceUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getSales(): Observable<Sale[]>{
    return this.http.get<any>(`${this.apiServiceUrl}/sale`);
  }

  public addSale(sale: Sale): Observable<Sale>{
    return this.http.post<Sale>(`${this.apiServiceUrl}/sale/add`, sale);
  }

  public updateSale(sale: Sale): Observable<Sale>{
    return this.http.put<Sale>(`${this.apiServiceUrl}/sale/update`, sale);
  }

  public deleteSale(sale: Sale): Observable<void>{
    return this.http.delete<void>(`${this.apiServiceUrl}/sale/delete`);
  }
}

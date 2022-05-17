import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from './product';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private apiServiceUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public addProduct(product : Product): Observable<Product>{
    return this.http.post<Product>(`${this.apiServiceUrl}/products/add`, product);
  }

}

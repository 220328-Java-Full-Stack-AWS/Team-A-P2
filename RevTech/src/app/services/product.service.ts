import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../dto/product';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiServiceUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }

  public getProducts(): Observable<Product[]>{
    return this.http.get<any>(`${this.apiServiceUrl}/products/all`);
  }

  public getProductsByStatus(status: string): Observable<Product[]>{
    return this.http.get<any>(`${this.apiServiceUrl}/products/status/${status}`);
  }

  public sort(sort: string, order: string): Observable<Product[]>{
    return this.http.get<any>(`${this.apiServiceUrl}/products/sort/${sort}/${order}`);
  }

  public getProductsByCategory(category: string): Observable<Product[]>{
    return this.http.get<any>(`${this.apiServiceUrl}/products/category/${category}`);
  }

  public addProduct(product: Product): Observable<Product>{
    return this.http.post<Product>(`${this.apiServiceUrl}/product/add`, product);
  }

  public updateproduct(product: Product): Observable<Product>{
    return this.http.put<Product>(`${this.apiServiceUrl}/product/update`, product);
  }

  public deleteproduct(id: number): Observable<void>{
    return this.http.delete<void>(`${this.apiServiceUrl}/product/delete${id}`);
  }

}

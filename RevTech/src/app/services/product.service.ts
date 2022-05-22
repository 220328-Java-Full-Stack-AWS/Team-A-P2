import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../dto/product';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class ProductService {

  public cart: Product[] = [];
  public total: number = 0;

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
    return this.http.post<Product>(`${this.apiServiceUrl}/products/add`, product);
  }

  public updateproduct(product: Product): Observable<Product>{
    return this.http.put<Product>(`${this.apiServiceUrl}/products/update`, product);
  }

  public deleteproduct(id: number): Observable<void>{
    return this.http.delete<void>(`${this.apiServiceUrl}/products/delete/id/${id}`);
  }

  public addToCart(product: Product){
    this.cart.push(product);
    this.total += product.productPrice;
    // console.log(this.total);
  }
  public removeFromCart(product: Product){
  }

}

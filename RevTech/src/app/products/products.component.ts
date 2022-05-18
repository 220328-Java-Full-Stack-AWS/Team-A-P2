import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { faCartPlus } from '@fortawesome/free-solid-svg-icons';
import { ProductService } from '../services/product.service';
import { Product } from '../dto/product';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  // fa icon
  public faCartPlus = faCartPlus;

  public products!: Product[];
  constructor(private productService: ProductService){}
  ngOnInit(): void {
    this.getProducts();
  }

  public getProducts():void {
    this.productService.getProducts().subscribe(
      (response: Product[]) => {
        this.products = response;
      },
      (error: HttpErrorResponse) => {
        console.log(error.message)
      }
    )
  }

  public searchProduct(key: string): void {
    const results: Product[] = [];
    for(const product of this.products){
      if(product.productName.toLowerCase().indexOf(key.toLowerCase()) !== -1 ||
        product.productCategory.toLowerCase().indexOf(key.toLowerCase()) !== -1 ||
        product.productStatus.toLowerCase().indexOf(key.toLowerCase()) !== -1
      ){
        results.push(product);
      }
      this.products = results;
      if(results.length === 0 || !key){
        this.getProducts();
      }
    }
  }

  public addToCart(product: Product): void {
    console.log(product)
  }
}

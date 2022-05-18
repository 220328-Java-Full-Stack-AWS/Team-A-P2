import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { faCartPlus } from '@fortawesome/free-solid-svg-icons';
import { ProductService } from '../services/product.service';
import { SaleService } from '../services/sale.service';
import { Product } from '../dto/product';
import { Sale } from '../dto/sale';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  // fa icon
  public faCartPlus = faCartPlus;

  public products!: Product[];

  public sale!: Sale;

  constructor(private productService: ProductService, private salesService: SaleService){}

  ngOnInit(): void {
    this.getProducts();
  }

  @Output() addOneToCart = new EventEmitter<any>();

  public OneToCart(value: any){
    this.addOneToCart.emit(value);
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
    localStorage.setItem("product", JSON.stringify(product));
  }
}

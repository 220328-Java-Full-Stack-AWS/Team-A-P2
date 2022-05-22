import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { faCartPlus, faChevronDown } from '@fortawesome/free-solid-svg-icons';
import { ProductService } from '../services/product.service';
import { SaleService } from '../services/sale.service';
import { Product } from '../dto/product';
import { Sale } from '../dto/sale';
import { Router } from '@angular/router';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  // fa icon
  public faCartPlus = faCartPlus;
  public faChevronDown = faChevronDown;
  public count: number = 0;

  public products!: Product[];

  public sale!: Sale;

  constructor(private productService: ProductService, private salesService: SaleService, private router: Router){}

  ngOnInit(): void {
    if(sessionStorage.getItem("username") == null){
      this.router.navigateByUrl('/login');
    }
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

  public getProductsByStatus(status: string){
    this.productService.getProductsByStatus(status).subscribe(
      (response: Product[]) => {
        this.products = response;
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    )
  }

  public getProductsByCategory(category: string){
    this.productService.getProductsByCategory(category).subscribe(
      (response: Product[]) => {
        this.products = response;
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    )
  }

  public sort(sort: string, order: string){
    this.productService.sort(sort, order).subscribe(
      (response: Product[]) => {
        this.products = response;
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
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

  // dropdown function
  public categoryDropdown(){
    const arrow = document.getElementById('c-icon');
    const sortList = document.getElementById("sort");
    const categoryList = document.getElementById("categories");
    categoryList?.classList.toggle('show');
    sortList?.classList.remove('show');
    arrow?.classList.toggle('flip');
  }
  public sortDropdown(){
    const categoryList = document.getElementById("categories");
    const sortList = document.getElementById("sort");
    sortList?.classList.toggle('show');
    categoryList?.classList.remove('show');
  }

  public addToCart(product: Product){
    this.productService.addToCart(product);
    this.productService.total == product.productPrice;
  }

  public getAmount(){
    let amount = document.getElementById("amount");
    console.log(amount?.textContent)
  }
}

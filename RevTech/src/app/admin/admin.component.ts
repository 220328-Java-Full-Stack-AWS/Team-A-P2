import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Product } from '../dto/product';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {

  public products!: Product[];
  public product!: Product;
  public editProduct!: Product;
  public deleteProduct!: Product;

  constructor(private productService: ProductService,) { }

  ngOnInit(): void {
    this.getProducts();
  }

  public getProducts():void {
    this.productService.getProducts().subscribe(
      (response: Product[]) => {
        this.products = response;
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        console.log(error.message)
      }
    )
  }

  public onOpenModal(product: Product, mode: string): void {
    const screen = document.getElementById('screen');
    screen?.classList.toggle('show');
    const modal = document.getElementById(`${mode}-modal`);
    modal?.classList.toggle('show');
    if(mode === 'edit'){
      this.editProduct = product;
    }
    if(mode === 'delete'){
      this.deleteProduct = product;
    }
  }

  public onCloseModal(mode: string){
    const screen = document.getElementById('screen');
    screen?.classList.remove('show');
    const modal = document.getElementById(`${mode}-modal`);
    modal?.classList.remove('show');
  }
}

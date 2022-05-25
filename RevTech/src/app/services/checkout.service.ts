import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Product } from '../dto/product';
import { Sale } from '../dto/sale';

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

  public cartItemList: any = [];
  public productList = new BehaviorSubject<any>([]);
  public quantityVal!: number;
  public saleList!: Sale[];
  public total = 0;

  constructor() { }

  getProducts() {
    return this.productList.asObservable();
  }

  addToCart(product: Product) {
    this.cartItemList.push(product);
    this.productList.next(this.cartItemList);

    this.total = this.total + product.productPrice * product.productQuantity;
    //The below cLog is only needed for testing.
    // console.log(this.cartItemList)
  }

  removeCartItem(product: any) {
    this.cartItemList.map((a: any, index: any) => {
      if (product.productId === a.productId) {
        this.cartItemList.splice(index, 1)
      }
    })
    //updates number found in the cart
    this.productList.next(this.cartItemList);
    this.total = this.total - product.productPrice * product.productQuantity;
  }

  emptyCart() {
    this.cartItemList = [];
    this.productList.next(this.cartItemList);
  }

  setSales(value: any[]) {
    this.saleList = value;
  }

  getSales() {
    return this.saleList;
  }

  setQuantity(num: number) {
    this.quantityVal = num;
  }

  getQuantity() {
    return this.quantityVal;
  }

}

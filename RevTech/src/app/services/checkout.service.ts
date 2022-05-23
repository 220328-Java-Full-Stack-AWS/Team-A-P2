import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Product } from '../dto/product';

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

  public cartItemList: any = [];
  public productList = new BehaviorSubject<any>([]);

  constructor() { }

  getProducts() {
    return this.productList.asObservable();
  }

  addToCart(product: Product) {
    this.cartItemList.push(product);
    this.productList.next(this.cartItemList);

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
    this.productList.next(this.cartItemList)
  }

  emptyCart() {
    this.cartItemList = [];
    this.productList.next(this.cartItemList);
  }

}

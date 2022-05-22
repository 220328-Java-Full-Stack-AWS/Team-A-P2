import { Component, OnInit } from '@angular/core';
import { CheckoutService } from '../services/checkout.service';
import { ProductsComponent } from '../products/products.component';
import { faRefresh } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-checkout-page',
  templateUrl: './checkout-page.component.html',
  styleUrls: ['./checkout-page.component.scss']
})
export class CheckoutPageComponent implements OnInit {

  public products: any = [];
  constructor(private checkoutService: CheckoutService) { }

  ngOnInit(): void {

    this.checkoutService.getProducts().subscribe(res => {
      this.products = res;
    })

  }
  removeItem(product: any) {
    this.checkoutService.removeCartItem(product);
  }

  emptyCart(){
    this.checkoutService.emptyCart();
  }

}

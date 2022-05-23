import { Component, OnInit } from '@angular/core';
import { CheckoutService } from '../services/checkout.service';
import { ProductsComponent } from '../products/products.component';
import { faRefresh } from '@fortawesome/free-solid-svg-icons';
import { Sale } from '../dto/sale';
import { SaleService } from '../services/sale.service';
import { OrderService, Holder } from '../services/order.service';
import { UserService } from '../services/user.service';
import { User } from '../dto/user';
import { Order } from '../dto/order';

@Component({
  selector: 'app-checkout-page',
  templateUrl: './checkout-page.component.html',
  styleUrls: ['./checkout-page.component.scss']
})
export class CheckoutPageComponent implements OnInit {

  public username = sessionStorage.getItem('username');
  public sale!: Sale;
  public saleList!: Sale[];
  public products: any = [];
  constructor(private checkoutService: CheckoutService, private saleService: SaleService, private orderService: OrderService, private userService: UserService) { }

  ngOnInit(): void {
    this.saleList = this.checkoutService.getSales();
    this.checkoutService.getProducts().subscribe(res => {
      this.products = res;
    })

  }
  removeItem(product: any) {
    this.checkoutService.removeCartItem(product);
    this.saleList.forEach(x => {
      if (x.product.productId == product) {
        this.saleService.invokeOrderFunction(this.saleService.getCurrentOrder(), x, "remove");
      }
    })
  }

  emptyCart() {
    this.checkoutService.emptyCart();
  }

  checkout() {
    let order = this.saleService.getCurrentOrder();
    this.userService.getUserByUsername(this.username).subscribe((data: User) => {
      let daUser = data;
      let holder = new Holder(order, this.sale, daUser);
      if (sessionStorage.getItem('paymentid') == null) {
        alert("A payment method must be linked to your account to checkout. You can add a payment method in profile page");

      } else {
        this.orderService.persistOrder(holder).subscribe((data: Order) => {
          let emptyOrder: Order = {
            orderId: null,
            saleList: []
          };
          this.saleService.setCurrentOrder(emptyOrder);
          this.emptyCart();
          alert("Checkout completed sucessfully! Thank you for your purchase!");

        })

      }

    })
  }

}

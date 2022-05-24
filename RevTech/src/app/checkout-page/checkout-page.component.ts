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
import { ProductService } from '../services/product.service';
import { Product } from '../dto/product';
import { faTrashCan, faFaceAngry } from '@fortawesome/free-solid-svg-icons';

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
  constructor(public checkoutService: CheckoutService, private saleService: SaleService, private orderService: OrderService, private userService: UserService, private productService: ProductService) { }
  public faTrashCan = faTrashCan;
  public faFaceAngry = faFaceAngry;




  ngOnInit(): void {
    this.saleList = this.checkoutService.getSales();
    this.checkoutService.getProducts().subscribe(res => {
      this.products = res;
    })

  }
  removeItem(product: any) {
    this.checkoutService.removeCartItem(product);
    sessionStorage.setItem('currentOrder', JSON.stringify(this.saleService.getCurrentOrder()));
    //for each sale in the current order 
    for (let i = 0; i < this.saleService.getCurrentOrder().saleList.length; i++) {
      //remove it if it contains the given product and update the given products quantity 
      if (this.saleService.getCurrentOrder().saleList[i].product.productId == product.productId) {
        this.saleService.getCurrentOrder().saleList[i].product.productQuantity = this.saleService.getCurrentOrder().saleList[i].quantity;

        this.productService.updateproduct(this.saleService.getCurrentOrder().saleList[i].product).subscribe((data: Product) => {
          this.saleService.getCurrentOrder().saleList[i].product = data;
          this.saleService.invokeOrderFunction(this.saleService.getCurrentOrder(), this.saleService.getCurrentOrder().saleList[i], "remove");
        })

      }
    }


    // animation
    const removeItemNotification = document.getElementById('removeItemNotification');
    removeItemNotification?.classList.add('showAdded');
    setTimeout(() => removeItemNotification?.classList.remove('showAdded'), 3000);
  }

  emptyCart() {
    this.checkoutService.emptyCart();

    // animation
    const clearCartNotification = document.getElementById('clearCartNotification');
    clearCartNotification?.classList.add('showAdded');
    setTimeout(() => clearCartNotification?.classList.remove('showAdded'), 3000);
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

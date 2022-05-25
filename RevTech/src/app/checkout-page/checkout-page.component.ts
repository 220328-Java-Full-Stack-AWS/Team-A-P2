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
import { faTrashCan, faFaceAngry, faFaceGrinTongueSquint } from '@fortawesome/free-solid-svg-icons';
import { AuthenticationService } from '../services/authentication.service';

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
  constructor(public checkoutService: CheckoutService, private saleService: SaleService, private orderService: OrderService, private userService: UserService, private productService: ProductService, public auth: AuthenticationService) { }
  public faTrashCan = faTrashCan;
  public faFaceAngry = faFaceAngry;
  public faFaceGrinTongueSquint = faFaceGrinTongueSquint;
  public cardnumber = sessionStorage.getItem('cardnumber');



  ngOnInit(): void {
    this.saleList = this.checkoutService.getSales();
    this.checkoutService.getProducts().subscribe(res => {
      this.products = res;
    })

  }
  removeItem(product: any) {
    this.checkoutService.removeCartItem(product);
    //for each sale in the current order
    for (let i = 0; i < this.saleService.getCurrentOrder().saleList.length; i++) {
      //remove it if it contains the given product and update the given products quantity
      if (this.saleService.getCurrentOrder().saleList[i].product.productId == product.productId) {
        this.saleService.getCurrentOrder().saleList[i].product.productQuantity += this.saleService.getCurrentOrder().saleList[i].quantity;

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
    this.userService.getUserByUsername(this.auth.username.value).subscribe((data: User) => {
      let daUser = data;
      let holder = new Holder(order, this.sale, daUser);
      console.log(this.cardnumber);
      if (this.cardnumber == 'null') {
        // alert("A payment method must be linked to your account to checkout. You can add a payment method in profile page");
        // animation
        const clearCartNotification = document.getElementById('noPaymentMethod');
        clearCartNotification?.classList.add('showAdded');
      } else {
        this.orderService.persistOrder(holder).subscribe((data: Order) => {
          let emptyOrder: Order = {
            orderId: null,
            saleList: []
          };

          this.saleService.setCurrentOrder(emptyOrder);
          this.emptyCart();
          // alert("Checkout completed sucessfully! Thank you for your purchase!");
          // animation
          const successPurchase = document.getElementById('successPurchase');
          successPurchase?.classList.add('showAdded');
          setTimeout(() =>  successPurchase?.classList.remove('showAdded'), 4000);
        })

      }

    })
  }

}

import { Component, OnInit } from '@angular/core';
import { CheckoutService } from '../services/checkout.service';
import { faTrashCan, faFaceAngry } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-checkout-page',
  templateUrl: './checkout-page.component.html',
  styleUrls: ['./checkout-page.component.scss']
})
export class CheckoutPageComponent implements OnInit {

  public faTrashCan = faTrashCan;
  public faFaceAngry = faFaceAngry;
  public products: any = [];

  public total = 0;
  constructor(private checkoutService: CheckoutService) { }


  ngOnInit(): void {

    this.checkoutService.getProducts().subscribe(res => {
      this.products = res;
    })

  }
  removeItem(product: any) {
    this.checkoutService.removeCartItem(product);

    // animation
    const removeItemNotification = document.getElementById('removeItemNotification');
    removeItemNotification?.classList.add('showAdded');
    setTimeout(() => removeItemNotification?.classList.remove('showAdded'), 3000);
  }

  emptyCart(){
    this.checkoutService.emptyCart();

     // animation
     const clearCartNotification = document.getElementById('clearCartNotification');
     clearCartNotification?.classList.add('showAdded');
     setTimeout(() => clearCartNotification?.classList.remove('showAdded'), 3000);
  }

}

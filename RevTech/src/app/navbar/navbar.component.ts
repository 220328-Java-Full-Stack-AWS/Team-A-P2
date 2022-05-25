import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faUserAstronaut, faShoppingCart, faWindowClose, faSun, faMoon, faUserSecret } from '@fortawesome/free-solid-svg-icons';
import { Observable } from 'rxjs';
import { Order } from '../dto/order';
import { User } from '../dto/user';
import { AuthenticationService } from '../services/authentication.service';
import { CheckoutService } from '../services/checkout.service';
import { ProductService } from '../services/product.service';
import { SaleService } from '../services/sale.service';
import { UserService } from '../services/user.service';
import { OrderService, Holder } from '../services/order.service';
import { Sale } from '../dto/sale';
import { Product } from '../dto/product';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  order: Order = this.saleService.getCurrentOrder();
  sale1!: Sale;
  user!: User;
  public sales!: Sale[];
  isLoggedIn$: Observable<boolean> | undefined;
  orderTotal: number = this.saleService.orderTotal;

  public cartLength!: number;
  constructor(private router: Router, public auth: AuthenticationService, private productService: ProductService, private saleService: SaleService, private userService: UserService, private orderService: OrderService, private checkoutService: CheckoutService) { }

  ngOnInit() {
    this.isLoggedIn$ = this.auth.isLoggedIn;
    this.sales = this.saleService.currentSales;

    this.checkoutService.getProducts().subscribe(res => {
      this.cartLength = res.length
    })
  }

  public username = this.auth.username;
  public username1 = sessionStorage.getItem('userid');


  // public username = "Leonel"

  public logOut() {
    this.auth.logout();
  }

  // Font Awesome Icons
  public faUserAstronaut = faUserAstronaut;
  public faShoppingCart = faShoppingCart;
  public faWindowClose = faWindowClose;
  public faUserSecret = faUserSecret;
  public faSun = faSun;
  public faMoon = faMoon;

  public userMenuDropdown() {
    const userMenu = document.querySelector('.user-menu');
    userMenu?.classList.toggle('magic');
  }
  public closeDropdown() {
    const userMenu = document.querySelector('.user-menu');
    userMenu?.classList.remove('magic');
  }

  public DarkTheme() {
    document.body.classList.add('darkMode');
  }
  public LightTheme() {
    document.body.classList.remove('darkMode');
  }

  public viewCart() {
    const cart = document.querySelector('.cart');
    cart?.classList.toggle('cartMagic');
  }

  public checkout() {
    //let order2 = JSON.parse(sessionStorage.getItem('key'));
    let daUser: User;
    this.userService.getUserByUsername(this.username1).subscribe((data: User) => {
      daUser = data;
      let holder = new Holder(this.order, this.sale1, daUser);
      this.orderService.persistOrder(holder).subscribe((data: Order) => {
        let order1: Order = {
          orderId: null,
          saleList: []
        };
        this.saleService.setCurrentOrder(order1)
        this.saleService.setCount(0);
      });
    })
    this.order = this.saleService.getCurrentOrder();




  }

  public remove(sale: Sale) {
    sale.product.productQuantity += sale.quantity;
    if (sale.product.productStatus == "out of stock") {
      sale.product.productStatus = "available";
    }
    this.productService.updateproduct(sale.product).subscribe((data: Product) => {
      this.order = this.saleService.invokeOrderFunction(this.order, sale, "remove");
    })


  }
}

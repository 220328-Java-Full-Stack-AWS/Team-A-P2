import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faUserAstronaut, faShoppingCart, faWindowClose, faSun, faMoon } from '@fortawesome/free-solid-svg-icons';
import { Observable } from 'rxjs';
import { Order } from '../dto/order';
import { User } from '../dto/user';
import { AuthenticationService } from '../services/authentication.service';
import { ProductService } from '../services/product.service';
import { SaleService } from '../services/sale.service';
import { UserService } from '../services/user.service';
import { OrderService, Holder } from '../services/order.service';
import { Sale } from '../dto/sale';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  order!: Order;
  sale1!: Sale;
  user!: User;
  public sales!: Sale[];
  isLoggedIn$: Observable<boolean> | undefined;
  orderTotal: number = this.saleService.orderTotal;

  constructor(private router: Router, private auth: AuthenticationService, private productService: ProductService, private saleService: SaleService, private userService: UserService, private orderService: OrderService) { }

  ngOnInit() {
    this.isLoggedIn$ = this.auth.isLoggedIn;
    this.sales = this.saleService.currentSales;
  }

  public page = this.router.url;

  public username = sessionStorage.getItem('username');


  public logOut() {
    this.auth.logout();
  }


  // Font Awesome Icons
  public faUserAstronaut = faUserAstronaut;
  public faShoppingCart = faShoppingCart;
  public faWindowClose = faWindowClose;
  public faSun = faSun;
  public faMoon = faMoon;

  public userMenuDropdown() {
    const userMenu = document.querySelector('.user-menu');
    userMenu?.classList.toggle('magic');
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
    let daUser: User;
    this.userService.getUserByUsername(this.username).subscribe((data: User) => {
      daUser = data;
      let holder = new Holder(this.order, this.sale1, daUser);
      this.orderService.persistOrder(holder).subscribe((data: Order) => {
        let order1: Order = {
          orderId: null,
          saleList: []
        };
        this.saleService.setCurrentOrder(order1)
      });
    })
    this.order = this.saleService.getCurrentOrder();




  }

  public remove(sale: Sale) {

  }
}

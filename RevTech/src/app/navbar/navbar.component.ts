import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faUserAstronaut, faShoppingCart, faWindowClose, faSun, faMoon } from '@fortawesome/free-solid-svg-icons';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../services/authentication.service';
import { CheckoutService } from '../services/checkout.service';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  isLoggedIn$!: Observable<boolean>;

  public cartLength!: number;
  constructor(private router: Router, private auth: AuthenticationService, private productService: ProductService, private checkoutService: CheckoutService) { }

  ngOnInit() {
    this.isLoggedIn$ = this.auth.isLoggedIn;

    this.checkoutService.getProducts().subscribe(res => {
      this.cartLength = res.length
    })

  }

  public username = this.auth.username;

  // public username = "Leonel"

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

}

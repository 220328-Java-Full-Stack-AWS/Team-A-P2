import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faUserAstronaut, faShoppingCart, faWindowClose, faSun, faMoon, faTrashCan } from '@fortawesome/free-solid-svg-icons';
import { Observable } from 'rxjs';
import { Product } from '../dto/product';
import { AuthenticationService } from '../services/authentication.service';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  isLoggedIn$: Observable<boolean> | undefined;

  constructor(private auth: AuthenticationService, public productService: ProductService) {
  }

  ngOnInit(){
    this.isLoggedIn$ = this.auth.isLoggedIn;
  }

  cart: Product[] = this.productService.cart;
  itemNumber: number = this.productService.cart.length;
  total: number = this.productService.total;

  public username = sessionStorage.getItem('username');

  public logOut(){
    this.auth.logout();
  }

  // Font Awesome Icons
  public faUserAstronaut = faUserAstronaut;
  public faShoppingCart = faShoppingCart;
  public faTrashCan = faTrashCan;
  public faSun = faSun;
  public faMoon = faMoon;

  public userMenuDropdown(){
    const userMenu = document.querySelector('.user-menu');
    userMenu?.classList.toggle('magic');
  }

  public DarkTheme(){
    document.body.classList.add('darkMode');
  }
  public LightTheme(){
    document.body.classList.remove('darkMode');
  }

  public viewCart(){
    const cartLogo = document.querySelector('.cart');
    cartLogo?.classList.toggle('cartMagic');
  }
}

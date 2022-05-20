import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faUserAstronaut, faShoppingCart, faWindowClose } from '@fortawesome/free-solid-svg-icons';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  ngOnInit(){
  }

  constructor(private router: Router,private auth: AuthenticationService) {
  }

  public counter = 0;

  public loggedIn = true;

  public logOut(){
    this.auth.logout();
  }

  public username = sessionStorage.getItem('username');

  // Font Awesome Icons
  public faUserAstronaut = faUserAstronaut;
  public faShoppingCart = faShoppingCart;
  public faWindowClose = faWindowClose;

  public userMenuDropdown(){
    const userMenu = document.querySelector('.user-menu');
    userMenu?.classList.toggle('magic');
  }

  public viewCart(){
    const cart = document.querySelector('.cart');
    cart?.classList.toggle('cartMagic');
  }
}

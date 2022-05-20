import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faUserAstronaut, faShoppingCart, faWindowClose, faSun, faMoon } from '@fortawesome/free-solid-svg-icons';
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
  public faSun = faSun;
  public faMoon = faMoon;

  public userMenuDropdown(){
    const userMenu = document.querySelector('.user-menu');
    userMenu?.classList.toggle('magic');
  }

  public DarkTheme(){
    const btn = document.getElementById('dark');
    btn?.classList.toggle('mode');
    document.body.classList.add('darkMode');
  }
  public LightTheme(){
    document.body.classList.remove('darkMode');
  }

  public viewCart(){
    const cart = document.querySelector('.cart');
    cart?.classList.toggle('cartMagic');
  }
}

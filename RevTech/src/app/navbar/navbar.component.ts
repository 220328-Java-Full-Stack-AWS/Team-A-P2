import { Component, Input, OnInit } from '@angular/core';
import { faUserAstronaut, faShoppingCart, faWindowClose } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  ngOnInit(): void {
    this.checkLogin();
  }

  public counter = 0;

  public loggedIn = false;

  public username = sessionStorage.getItem('username');

  // uses session Storage to see if user is logged in
  public checkLogin(){
    let cookie = sessionStorage.getItem('username');
    if(cookie !== ""){ // Named cookie becauase that will be the final product
      this.loggedIn = true;
    }else{
      this.loggedIn = false;
    }
  }

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

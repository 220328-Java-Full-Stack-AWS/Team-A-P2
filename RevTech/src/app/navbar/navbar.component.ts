import { Component, OnInit } from '@angular/core';
import { faUserAstronaut, faShoppingCart, faWindowClose } from '@fortawesome/free-solid-svg-icons';
import { AppComponent } from '../app.component';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  // Font Awesome Icons
  public faUserAstronaut = faUserAstronaut;
  public faShoppingCart = faShoppingCart;
  public faWindowClose = faWindowClose;

  public counter = 0;
  ngOnInit(): void {
  }


  public userMenuDropdown(){
    const userMenu = document.querySelector('.user-menu');
    userMenu?.classList.toggle('magic');
  }

  public viewCart(){
    const cart = document.querySelector('.cart');
    cart?.classList.toggle('cartMagic');
  }
}
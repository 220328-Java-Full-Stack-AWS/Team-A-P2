import { Component, Input } from '@angular/core';
import { faUserAstronaut, faShoppingCart, faWindowClose } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {

  @Input() cartCount: 0 | undefined;
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

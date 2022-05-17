import { Component, OnInit } from '@angular/core';
import { faUserAstronaut, faFaceRollingEyes, faCopyright, faShoppingCart, faCartPlus } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'RevTech';
  // Current yyear for nav bar
  public year = new Date().getFullYear();
    // Font Awesome Icons
    public faUserAstronaut = faUserAstronaut;
    public faFaceRollingEyes = faFaceRollingEyes;
    public faCopyright = faCopyright;
    public faShoppingCart = faShoppingCart;
    public faCartPlus = faCartPlus;

    // Front end add to cart
    public counter = 0;
    public AddOneToCart(){
      this.counter++;
    }

  }

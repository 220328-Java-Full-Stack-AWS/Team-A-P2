import { Component } from '@angular/core';
import { faUserAstronaut, faFaceRollingEyes, faCopyright, faShoppingCart } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'RevTech';
  public year = new Date().getFullYear();
    // Font Awesome Icons
    public faUserAstronaut = faUserAstronaut;
    public faFaceRollingEyes = faFaceRollingEyes;
    public faCopyright = faCopyright;
    public faShoppingCart = faShoppingCart;

}

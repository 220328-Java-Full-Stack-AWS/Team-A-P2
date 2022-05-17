import { Component, OnInit } from '@angular/core';
import { faCartPlus } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'RevTech';

  public faCartPlus = faCartPlus;

    // Front end add to cart
    public counter = 0;
    public AddOneToCart(){
      this.counter++;
    }

  }

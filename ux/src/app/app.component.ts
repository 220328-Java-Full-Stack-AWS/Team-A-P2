import { Component, OnInit } from '@angular/core';
import { faUserAstronaut } from '@fortawesome/free-solid-svg-icons';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'ReValue';
  public year = new Date().getFullYear();
  public faUserAstronaut = faUserAstronaut;

  public navAnimation(){
    const header = document.querySelector('.header');
    header?.classList.add('fixed-top');
  }

}

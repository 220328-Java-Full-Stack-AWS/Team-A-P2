import { Component } from '@angular/core';
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
}

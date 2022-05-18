import { Component, OnInit } from '@angular/core';
import { GlobalComponent } from "./global-component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent{
  title = 'RevTech';
  counter = GlobalComponent.counter;
}

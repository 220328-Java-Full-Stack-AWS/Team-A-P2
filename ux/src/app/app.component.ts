import { Component, OnInit } from '@angular/core';
import { faUserAstronaut, faFaceRollingEyes, faCopyright, faShoppingCart } from '@fortawesome/free-solid-svg-icons';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'RevTech';
  // Footer date
  public year = new Date().getFullYear();
  // Font Awesome Icons
  public faUserAstronaut = faUserAstronaut;
  public faFaceRollingEyes = faFaceRollingEyes;
  public faCopyright = faCopyright;
  public faShoppingCart = faShoppingCart;

  // //view password
  // public togglePassword(){
  //   let showPassword = document.getElementById('viewPasswordText');
  //   var x = document.getElementById("password");
  //   if (showPassword?.textContent === "View Password") {
  //       showPassword.textContent = "Hide Password";
  //   } else if (showPassword?.textContent === "Hide Password") {
  //       showPassword.textContent = "View Password";
  //   }
  // }

  // // Modals functions
  // public onOpenModal(mode: String): void {
  //   const container = document.getElementById("main-container");
  //   const button = document.createElement('button');
  //   button.type = 'button';
  //   button.style.display = 'none';
  //   button.setAttribute('data-toggle', 'modal');
  //   if(mode === 'login'){
  //     button.setAttribute('data-target', '#loginModal');
  //   }
  //   if(mode === 'register'){
  //     button.setAttribute('data-target', '#registerModal');
  //   }
  //   container?.appendChild(button);
  //   button.click();
  // }
}

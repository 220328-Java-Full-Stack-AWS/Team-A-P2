import { Component, OnInit, Output, EventEmitter, Input, Injectable, Inject } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { AddressService } from '../services/address.service';
import { PaymentService } from '../services/payment.service';
import { User } from '../dto/user';
import { Address } from '../dto/address';
import { Payment } from '../dto/payment';
import { AuthenticationService } from '../services/authentication.service';
import { ResourceLoader } from '@angular/compiler';
import { GsapService } from '../services/gsap.service';
import { faFaceRollingEyes,faUserAstronaut, faUserSecret,faFaceSmileWink,faFaceAngry } from '@fortawesome/free-solid-svg-icons';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-profiles',
  templateUrl: './profiles.component.html',
  styleUrls: ['./profiles.component.scss'],
})

export class ProfilesComponent implements OnInit {
  nameHTML = sessionStorage.getItem("firstname") + " " + sessionStorage.getItem("lastname");
  userHTML = sessionStorage.getItem("username");
  phoneHTML = sessionStorage.getItem("phone");
  emailHTML = sessionStorage.getItem("email");
  addressHTML = sessionStorage.getItem("address") + " " + sessionStorage.getItem("city") + " " + sessionStorage.getItem("state")
  + " " + sessionStorage.getItem("zip") + " " + sessionStorage.getItem("country");
  paymentHTML = sessionStorage.getItem("cardnumber") + " " + sessionStorage.getItem("expirationdate") + " " + sessionStorage.getItem("cvc");
  passwordHTML = sessionStorage.getItem("password");

  constructor(public auth: AuthenticationService, public userService: UserService, public paymentService: PaymentService,  public addressService: AddressService, private router: Router, public gsap: GsapService) { }


  public updateUser(choice: string){
    if(choice == "address"){
      let address: Address = {
        addressId: parseInt(sessionStorage.getItem("userid")!),
        address: prompt("Address")!,
        city: prompt("City")!,
        state: prompt("State")!,
        zipCode: parseInt(prompt("Zip")!),
        country: prompt("Country")!
      }

      this.addressService.updateAddress(address).subscribe(
        (response: Address) => {
          sessionStorage.setItem("address", response.address);
          sessionStorage.setItem("city", response.city);
          sessionStorage.setItem("state", response.state);
          sessionStorage.setItem("zip", response.zipCode.toString());
          sessionStorage.setItem("country", response.country);
          location.reload();
        }
      );
    }
    else if(choice == "payment"){
      let payment: Payment = {
        paymentId: parseInt(sessionStorage.getItem("userid")!),
        cardNumber: parseInt(prompt("Card Number")!),
        cvc: parseInt(prompt("cvc")!),
        expDate: prompt("Exp Date")
      }

      this.paymentService.updatePayment(payment).subscribe(
        (response: Payment) => {
          sessionStorage.setItem('cardnumber', response.cardNumber.toString());
          sessionStorage.setItem('cvc', response.cvc.toString());
          sessionStorage.setItem('expirationdate', response.expDate);
          location.reload();
        }
      )
    }
    else {
      if(choice == "name"){
        sessionStorage.setItem("firstname", prompt("First name")!);
        sessionStorage.setItem("lastname", prompt("Last name")!);
      }

      if(choice == "username"){
        sessionStorage.setItem("username", prompt("Username")!);
      }

      if(choice == "email"){
        sessionStorage.setItem("email", prompt("Email")!);
      }

      if(choice == "phone"){
        sessionStorage.setItem("phone", prompt("Phone")!);
      }

      if(choice == "password"){
        sessionStorage.setItem("password", prompt("Password")!);
      }

      let user : User = {
        userId: parseInt(sessionStorage.getItem("userid")!),
        username: sessionStorage.getItem("username")!,
        email: sessionStorage.getItem("email")!,
        password: sessionStorage.getItem("password")!,
        firstName: sessionStorage.getItem("firstname")!,
        lastName: sessionStorage.getItem("lastname")!,
        phone: sessionStorage.getItem("phone")!,
      }

      this.userService.updateUser(user).subscribe(
        () => {
          location.reload();
        }
      );
    }
  }

  public deleteUser(){
    this.userService.deleteUser(parseInt(sessionStorage.getItem("userid")!)).subscribe(
      () => {
        this.router.navigateByUrl('/login');
      }
  );}

  public deleteAddress(){
    this.addressService.deleteAddress(parseInt(sessionStorage.getItem("userid")!)).subscribe(
      () => {}
    )
  }

  public deletePayment(){
    this.paymentService.deletePayment(parseInt(sessionStorage.getItem("userid")!)).subscribe(
      () => {}
    )
  }



  // Front end logos
  public faUserAstronaut = faUserAstronaut;
  public faUserSecret = faUserSecret;
  public faFaceRollingEyes = faFaceRollingEyes;
  public faFaceSmileWink = faFaceSmileWink;
  public faFaceAngry = faFaceAngry;

  // show password
  // variable - default false
  show: boolean = false;
  show2: boolean = false;
  // click event function toggle
  showPassword() {
    this.show = !this.show;
  }
  showPassword2() {
    this.show2 = !this.show2;
  }


  public payment: Payment = {} as Payment;
  public address: Address = {} as Address;
  public user: User = {} as User;

  ngOnInit(): void {
    this.getUserData();
    this.openingAnimation();
  }

  public openingAnimation(){
    const anim = this.gsap;
    const profile = '#profile';
    const profileTitle = '#profile-page-title';
    anim.slideIn(profile);
    anim.slideIn(profileTitle);
  }

  public getUserData(){
    this.userService.getUserByUsername(this.auth.username.value).subscribe(
      (response: User) => {
        this.user = response;
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        console.log(error.message)
      }
    )
  }

  // forms
  public onEditUser(editForm: NgForm): void{
  }
  public onAddPayment(addForm: NgForm): void{
    this.paymentService.addNewPayment(addForm.value).subscribe(
      (response: Payment)=> {
        this.getUserData();
        console.log(response);
        const screen = document.getElementById('screen');
        screen?.classList.remove('show');
        const modal = document.getElementById('add-modal');
        modal?.classList.remove('show');

        // animation
        const SuccessNotification = document.getElementById('SuccessNotification');
        SuccessNotification?.classList.add('showAdded3');
        setTimeout(() => SuccessNotification?.classList.remove('showAdded3'), 3000);
      },
      (error: HttpErrorResponse) =>{
        console.log(error.message);
        // animation
        const ErrorNotification = document.getElementById('ErrorNotification');
        ErrorNotification?.classList.add('showAdded3');
        setTimeout(() => ErrorNotification?.classList.remove('showAdded3'), 3000);
      }
    )
  }

  // modals
  public onOpenModal(mode: string, type: string): void {
    const screen = document.getElementById('screen');
    screen?.classList.toggle('show');
    const modal = document.getElementById(`${mode}-${type}-modal`);
    modal?.classList.toggle('show');
  }

  public onCloseModal(mode: string, type: string){
    const screen = document.getElementById('screen');
    screen?.classList.remove('show');
    const modal = document.getElementById(`${mode}-${type}-modal`);
    modal?.classList.remove('show');
  }
}

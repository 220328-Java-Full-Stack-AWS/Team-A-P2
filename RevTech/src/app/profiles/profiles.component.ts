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

  public payment: Payment = {} as Payment;
  public address: Address = {} as Address;
  public user: User = {} as User;
  public data: any = {};

  ngOnInit(): void {
    this.getUserData();
    this.openingAnimation();
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
        this.data = response;
        console.log(this.data);
      },
      (error: HttpErrorResponse) => {
        console.log(error.message)
      }
    )
  }

  // forms
  public editUser(editUserForm: NgForm): void{
    let user : User = {
      userId: parseInt(sessionStorage.getItem("userid")!),
      username: editUserForm.value.username,
      email: editUserForm.value.email,
      password: editUserForm.value.password,
      firstName: editUserForm.value.firstName,
      lastName: editUserForm.value.lastName,
      phone: editUserForm.value.phone,
    }

    this.userService.updateUser(user).subscribe(
      (response: User) => {
        sessionStorage.setItem("username", response.username);
        sessionStorage.setItem("email", response.email);
        sessionStorage.setItem("password", response.password);
        sessionStorage.setItem("firstName", response.firstName);
        sessionStorage.setItem("lastName", response.lastName);
        sessionStorage.setItem("phone", response.phone);
        // location.reload();
        this.getUserData();
        const screen = document.getElementById('screen');
        screen?.classList.remove('show');
        const modal = document.getElementById('edit-account-modal');
        modal?.classList.remove('show');

        // animation
        const SuccessNotification = document.getElementById('SuccessNotification');
        SuccessNotification?.classList.add('showAdded3');
        setTimeout(() => SuccessNotification?.classList.remove('showAdded3'), 3000);
      }
    );
  }

  public editAddress(editAddressForm: NgForm): void{
    let address: Address = {
      addressId: parseInt(sessionStorage.getItem("userid")!),
      address: editAddressForm.value.address,
      city: editAddressForm.value.city,
      state: editAddressForm.value.state,
      zipCode: editAddressForm.value.zip,
      country: editAddressForm.value.country
    }

    this.addressService.updateAddress(address).subscribe(
      (response: Address) => {
        console.log(response);
        sessionStorage.setItem("address", response.address);
        sessionStorage.setItem("city", response.city);
        sessionStorage.setItem("state", response.state);
        sessionStorage.setItem("zip", response.zipCode);
        sessionStorage.setItem("country", response.country);
        // location.reload();
        this.getUserData();
        const screen = document.getElementById('screen');
        screen?.classList.remove('show');
        const modal = document.getElementById('edit-address-modal');
        modal?.classList.remove('show');
        // animation
        const SuccessNotification = document.getElementById('SuccessNotification');
        SuccessNotification?.classList.add('showAdded3');
        setTimeout(() => SuccessNotification?.classList.remove('showAdded3'), 3000);

      }
    );
  }

  public editPayment(editPaymentForm: NgForm): void{
    let payment: Payment = {
      paymentId: parseInt(sessionStorage.getItem("userid")!),
      cardNumber: editPaymentForm.value.cardNumber,
      cvc: editPaymentForm.value.cvc,
      expDate: editPaymentForm.value.expDate
    }
    console.log(editPaymentForm.value);

    this.paymentService.updatePayment(payment).subscribe(
      (response: Payment) => {
        console.log(response);
        sessionStorage.setItem('cardnumber', response.cardNumber);
        sessionStorage.setItem('cvc', response.cvc);
        sessionStorage.setItem('expirationdate', response.expDate);
        // location.reload();
        this.getUserData();
        const screen = document.getElementById('screen');
        screen?.classList.remove('show');
        const modal = document.getElementById('edit-payment-modal');
        modal?.classList.remove('show');
        // animation
        const SuccessNotification = document.getElementById('SuccessNotification');
        SuccessNotification?.classList.add('showAdded3');
        setTimeout(() => SuccessNotification?.classList.remove('showAdded3'), 3000);
      }
    )
  }


  public deleteUser(){
    this.userService.deleteUser(parseInt(sessionStorage.getItem("userid")!)).subscribe(
      () => {
        this.router.navigateByUrl('/login');
      }
  );}

  public deleteAddress(){
    let address: Address = {
      addressId: parseInt(sessionStorage.getItem("userid")!),
      address: null,
      city: null,
      state: null,
      zipCode: null,
      country: null
    }

    this.addressService.updateAddress(address).subscribe(
      () => {
        sessionStorage.removeItem("address");
        sessionStorage.removeItem("city");
        sessionStorage.removeItem("state");
        sessionStorage.removeItem("zip");
        sessionStorage.removeItem("country");
        // location.reload();
        this.getUserData();
        const screen = document.getElementById('screen');
        screen?.classList.remove('show');
        const modal = document.getElementById('delete-address-modal');
        modal?.classList.remove('show');
        // animation
        const SuccessNotification = document.getElementById('SuccessNotification');
        SuccessNotification?.classList.add('showAdded3');
        setTimeout(() => SuccessNotification?.classList.remove('showAdded3'), 3000);
      }
    );}

  public deletePayment(){
    let payment: Payment = {
      paymentId: parseInt(sessionStorage.getItem("userid")!),
      cardNumber: null,
      cvc: null,
      expDate: null
    }

    this.paymentService.updatePayment(payment).subscribe(
      () => {
        sessionStorage.removeItem("cardnumber");
        sessionStorage.removeItem("expirationdate");
        sessionStorage.removeItem("cvc");
        // location.reload();
        this.getUserData();
        const screen = document.getElementById('screen');
        screen?.classList.remove('show');
        const modal = document.getElementById('delete-payment-modal');
        modal?.classList.remove('show');

        // animation
        const SuccessNotification = document.getElementById('SuccessNotification');
        SuccessNotification?.classList.add('showAdded3');
        setTimeout(() => SuccessNotification?.classList.remove('showAdded3'), 3000);
      }
    );}

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

import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { faFaceRollingEyes,faUserAstronaut, faUserSecret,faFaceSmileWink,faFaceAngry } from '@fortawesome/free-solid-svg-icons';
import { AuthenticationService } from '../services/authentication.service';
import { User } from '../dto/user';
import { NgForm } from '@angular/forms';
import { UserService } from '../services/user.service';
import { PaymentService } from '../services/payment.service';
import { AddressService } from '../services/address.service';
import { Address } from '../dto/address';
import { Payment } from '../dto/payment';

@Component({
  selector: 'app-profiles',
  templateUrl: './profiles.component.html',
  styleUrls: ['./profiles.component.scss']
})
export class ProfilesComponent implements OnInit {

  constructor(public auth: AuthenticationService, public userService: UserService, public paymentService: PaymentService,  public addressService: AddressService) { }

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
  }

  public getUserData(){
    this.userService.getUserByUsername(this.auth.username.value).subscribe(
      (response: User) => {
        this.user = response;
        this.address = response.address;
        this.payment = response.payment;
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

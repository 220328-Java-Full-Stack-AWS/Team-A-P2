import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { faFaceRollingEyes } from '@fortawesome/free-solid-svg-icons';
import { NgForm } from '@angular/forms';
import { UserService } from '../services/user.service';
import { User } from '../dto/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})
export class ResetPasswordComponent implements OnInit {

  constructor(public userService: UserService,private router: Router) { }

  public faFaceRollingEyes = faFaceRollingEyes;

  // variable - default false
  show: boolean = false;
  // click event function toggle
  showPassword() {
    this.show = !this.show;
  }

  ngOnInit(): void {
  }

  public data: any = {};
  public getUserByEmail(form: NgForm){
    this.userService.getUserByEmail(form.value.email).subscribe(
      (response: any) => {
        this.data = response;
        console.log(this.data);

        // animation
        const screen = document.getElementById('screen');
        screen?.classList.toggle('show6');
        const modal = document.getElementById('reset-password-modal');
        modal?.classList.toggle('show6');
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
        // animation
        const notFoundNotification = document.getElementById('notFoundNotification');
        notFoundNotification?.classList.add('showAdded5');
        setTimeout(() => notFoundNotification?.classList.remove('showAdded5'), 3000);
      }
    )
  }

  public changePassword(form: NgForm): void{
    let user : User = {
      userId: this.data?.userId,
      username: this.data?.username,
      email: this.data?.email,
      password: form.value.password,
      firstName: this.data?.firstName,
      lastName: this.data?.lastName,
      phone: this.data?.phone,
    }
    this.userService.updateUser(user).subscribe(
      (response: User) => {
        this.router.navigate(['/login']);
      }
    );
  }

  public onCloseModal(){
    const screen = document.getElementById('screen');
    screen?.classList.remove('show6');
    const modal = document.getElementById('reset-password-modal');
    modal?.classList.remove('show6');
  }
}

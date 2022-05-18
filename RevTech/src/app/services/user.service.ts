import { Injectable } from '@angular/core';
import { User } from '../dto/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }

  private user: User;
}

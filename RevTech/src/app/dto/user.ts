import { Address } from "./address";
import { Payment } from "./payment";

export interface User {
    userId: number;
    username: string;
    email: string;
    password: string;
    firstName: string;
    lastName: string;
    phoneNumber: string;
    address: Address;
    payment: Payment;
  }
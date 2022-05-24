import { User } from "./user";

export interface Payment {
    paymentId: number;
    cardNumber: number;
    expDate: any;
    cvc: number;
  }

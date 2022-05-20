import { User } from "./user";

export interface Payment {
    paymentId: number;
    cardNumber: number;
    expirationDate: any;
    cvc: number;
  }

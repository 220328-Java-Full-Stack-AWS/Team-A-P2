import { User } from "./user";

export interface Address {
    addressId: number;
    address: string;
    city: string;
    state: string;
    zip: number;
    country: string;
    userId: number;
  }
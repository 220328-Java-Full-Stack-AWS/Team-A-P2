import { Sale } from "./sale";

export interface Order {
    orderId: any;
    saleList: Sale[];
}
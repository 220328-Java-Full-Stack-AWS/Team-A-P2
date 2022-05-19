import { Sale } from "./sale";

export interface Order {
    id: number;
    saleList: Sale[];
}
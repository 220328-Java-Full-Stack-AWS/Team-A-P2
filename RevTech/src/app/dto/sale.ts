import { Product } from "./product";

export interface Sale {
  saleId: number;
  quantity: number;
  cost: number;
  dateOfPurchase: any;
  productDescription: string;
  product: Product;
}

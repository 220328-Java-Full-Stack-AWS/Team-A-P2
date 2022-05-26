import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, ElementRef, OnInit } from '@angular/core';
import { faCartPlus, faChevronDown, faFaceGrinTongueSquint, faFaceGrinTears } from '@fortawesome/free-solid-svg-icons';
import { ProductService } from '../services/product.service';
import { SaleService } from '../services/sale.service';
import { Product } from '../dto/product';
import { Sale } from '../dto/sale';
import { Router } from '@angular/router';
import { Order } from '../dto/order';
import { OrderService } from '../services/order.service';
import { AddressService } from '../services/address.service';
import { Address } from '../dto/address';
import { PaymentService } from '../services/payment.service';
import { Payment } from '../dto/payment';
import { CheckoutService } from '../services/checkout.service';
import { GsapService } from '../services/gsap.service';


@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  // fa icon
  public faCartPlus = faCartPlus;
  public faChevronDown = faChevronDown;
  public faFaceGrinTongueSquint = faFaceGrinTongueSquint;
  public faFaceGrinTears = faFaceGrinTears;
  public count: number = 0;

  public products!: Product[];



  str: number = 0;
  public sale!: Sale;
  public order: Order = {
    orderId: null,
    saleList: []
  };
  public productList!: Product[];
  public item!: Product;
  public selectedQuantity!: string;

  constructor(private productService: ProductService, private salesService: SaleService, private router: Router, private addressService: AddressService, private paymentService: PaymentService, private checkoutService: CheckoutService, public gsap: GsapService, private orderService: OrderService) { }

  ngOnInit(): void {
    this.getProducts();
    this.openingAnimation();
    this.addressService.getAddressByAddressId(parseInt(sessionStorage.getItem("userid")!)).subscribe(
      (response: Address) => {
        sessionStorage.setItem('address', response.address);
        sessionStorage.setItem('city', response.city);
        sessionStorage.setItem('state', response.state);

        if (response.zipCode == null) { sessionStorage.setItem('zip', "null"); }
        else { sessionStorage.setItem('zip', response.zipCode.toString()); }

        sessionStorage.setItem('country', response.country);
      }
    )

    this.paymentService.getPaymentByPaymentId(parseInt(sessionStorage.getItem("userid")!)).subscribe(
      (response: Payment) => {
        if (response.cardNumber == null) { sessionStorage.setItem('cardnumber', "null"); }
        else { sessionStorage.setItem('cardnumber', response.cardNumber.toString()); }

        if (response.cvc == null) { sessionStorage.setItem('cvc', "null"); }
        else { sessionStorage.setItem('cardnumber', response.cvc.toString()); }

        sessionStorage.setItem('expirationdate', response.expDate);
      }
    )
  }

  quantityUpdate(value: any) {
    this.str = value;
  }

  public getProducts(): void {
    this.productService.getProducts().subscribe(
      (response: Product[]) => {
        this.products = response;
      },
      (error: HttpErrorResponse) => {
        console.log(error.message)
      }
    )
  }

  public openingAnimation() {
    const anim = this.gsap;
    const product = '#products';
    anim.fadeIn(product, 0.6, 0, 0.1);
  }

  public getProductsByStatus(status: string) {
    this.productService.getProductsByStatus(status).subscribe(
      (response: Product[]) => {
        this.products = response;
        this.openingAnimation();
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    )
  }

  public getProductsByCategory(category: string) {
    this.productService.getProductsByCategory(category).subscribe(
      (response: Product[]) => {
        this.products = response;
        this.closeDropdown("categories");
        this.openingAnimation();
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    )
  }

  public sort(sort: string, order: string) {
    this.productService.sort(sort, order).subscribe(
      (response: Product[]) => {
        this.products = response;
        this.closeDropdown("sort");
        this.openingAnimation();
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    )
  }


  public searchProduct(key: string): void {
    const results: Product[] = [];
    for (const product of this.products) {
      if (product.productName.toLowerCase().indexOf(key.toLowerCase().trim()) !== -1 ||
        product.productCategory.toLowerCase().indexOf(key.toLowerCase().trim()) !== -1 ||
        product.productStatus.toLowerCase().indexOf(key.toLowerCase().trim()) !== -1
      ) {
        results.push(product);
      }
      this.products = results;
      if (key.length === 0) {
        this.getProducts();
      }
    }
  }

  // dropdown function
  public categoryDropdown() {
    const arrow = document.getElementById('c-icon');
    const sortList = document.getElementById("sort");
    const categoryList = document.getElementById("categories");
    categoryList?.classList.toggle('show');
    sortList?.classList.remove('show');
    arrow?.classList.toggle('flip');
  }

  public closeDropdown(id: string) {
    const element = document.getElementById(`${id}`);
    element?.classList.remove('show');
  }

  public sortDropdown() {
    const categoryList = document.getElementById("categories");
    const sortList = document.getElementById("sort");
    sortList?.classList.toggle('show');
    categoryList?.classList.remove('show');
  }

  /*
  public addToCart(product: Product, selectedQuantity: string) {
    product.productQuantity = parseInt(selectedQuantity);
    this.checkoutService.addToCart(product);
  }
  */

  public addToCart(product: Product): void {

    if (product.productQuantity <= 0) {
      // alert("This product is currently out of stock");
      // animation
      const OutofStock = document.getElementById('product-out-of-stock');
      OutofStock?.classList.add('showAdded');
      setTimeout(() => OutofStock?.classList.remove('showAdded'), 3000);
    }
    let sale = {
      quantity: this.str,
      dateOfPurchase: null,
      cost: product.productPrice * this.str,
      product: {
        productId: product.productId,
        productName: product.productName,
        productDescription: product.productDescription,
        productCategory: product.productCategory,
        productImage: product.productImage,
        productStatus: product.productStatus,
        productPrice: product.productPrice,
        productQuantity: product.productQuantity - this.str
      },
    };

    console.log(sale.product.productQuantity);
    if (sale.product.productQuantity >= 0) {
      if (sale.product.productQuantity == 0) {
        sale.product.productStatus = "out of stock";
      }
      this.order = this.salesService.currentOrder;
      product = sale.product;
      this.productService.updateproduct(product).subscribe((data: Product) => {
        product.productQuantity = this.str;
        this.checkoutService.addToCart(product);
        product = data;
        this.salesService.addSale(sale).subscribe((data: Sale) => {

          sale = data;
          this.order = this.salesService.invokeOrderFunction(this.order, sale, "add");


        });
      });


      this.salesService.orderTotal += sale.cost;
      // alert("Item successfully added to cart");
      // animation
      const CartNotification = document.getElementById('CartNotification');
      CartNotification?.classList.add('showAdded');
      setTimeout(() => CartNotification?.classList.remove('showAdded'), 3000);


      localStorage.setItem("product", JSON.stringify(product));
    } else if (sale.product.productQuantity <= 0) {
      // alert("Sorry, we don't have that much of this product in stock!");
      // animation
      const LowStock = document.getElementById('product-low-in-stock');
      LowStock?.classList.add('showAdded');
      setTimeout(() => LowStock?.classList.remove('showAdded'), 3000);

    }

  }
}

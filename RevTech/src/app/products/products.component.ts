import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { faCartPlus, faChevronDown, faFaceGrinTongueSquint } from '@fortawesome/free-solid-svg-icons';
import { ProductService } from '../services/product.service';
import { SaleService } from '../services/sale.service';
import { Product } from '../dto/product';
import { Sale } from '../dto/sale';
import { Router } from '@angular/router';
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
  public count: number = 0;

  public products!: Product[];

  public productList!: Product[];
  public sale!: Sale;

  public item!: Product;
  public selectedQuantity!: string;

  constructor(private productService: ProductService, private salesService: SaleService, private router: Router, private addressService: AddressService, private paymentService: PaymentService, private checkoutService: CheckoutService, public gsap: GsapService) { }

  ngOnInit(): void {
    this.getProducts();

    // this.addressService.getAddressByAddressId(parseInt(sessionStorage.getItem("userid")!)).subscribe(
    //   (response: Address) => {
    //    sessionStorage.setItem('address', response.address);
    //    sessionStorage.setItem('city', response.city);
    //    sessionStorage.setItem('state', response.state);

    //    if(response.zipCode == null){sessionStorage.setItem('zip', "null");}
    //    else{sessionStorage.setItem('zip', response.zipCode.toString());}

    //    sessionStorage.setItem('country', response.country);
    //   }
    // )

    // this.paymentService.getPaymentByPaymentId(parseInt(sessionStorage.getItem("userid")!)).subscribe(
    //   (response: Payment) => {
    //     if(response.cardNumber == null){sessionStorage.setItem('cardnumber', "null");}
    //     else{sessionStorage.setItem('cardnumber', response.cardNumber.toString());}

    //     if(response.cvc == null){sessionStorage.setItem('cvc', "null");}
    //     else{sessionStorage.setItem('cardnumber', response.cvc.toString());}

    //     sessionStorage.setItem('expirationdate', response.expirationDate);
    //    }
    // )

    this.openingAnimation();
  }

  public addToCart(product: Product, selectedQuantity: string) {
    product.productQuantity = parseInt(selectedQuantity);
    this.checkoutService.addToCart(product);

    // animation
    const CartNotification = document.getElementById('CartNotification');
    CartNotification?.classList.add('showAdded');
    setTimeout(() => CartNotification?.classList.remove('showAdded'), 3000);
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

  public openingAnimation(){
    const anim = this.gsap;
    const product = '#products';
    anim.fadeIn(product, 0.5, 0, 0.5);
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
      if (product.productName.toLowerCase().indexOf(key.toLowerCase()) !== -1 ||
        product.productCategory.toLowerCase().indexOf(key.toLowerCase()) !== -1 ||
        product.productStatus.toLowerCase().indexOf(key.toLowerCase()) !== -1
      ) {
        results.push(product);
      }
      this.products = results;
      if (results.length === 0 || !key) {
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

  public sortDropdown(){
    const categoryList = document.getElementById("categories");
    const sortList = document.getElementById("sort");
    sortList?.classList.toggle('show');
    categoryList?.classList.remove('show');
  }

}

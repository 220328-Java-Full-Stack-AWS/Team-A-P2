import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm} from '@angular/forms';
import { Product } from '../dto/product';
import { ProductService } from '../services/product.service';
import { faFaceSmileWink,faFaceAngry  } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {

  public products!: Product[];
  public product!: Product;
  public editProduct!: Product;
  public deleteProduct!: Product;
  public enableSave: any;
  public faFaceSmileWink = faFaceSmileWink;
  public faFaceAngry = faFaceAngry;

  constructor(private productService: ProductService,) { }

  ngOnInit(): void {
    this.getProducts();
  }

  public getProducts():void {
    this.productService.getProducts().subscribe(
      (response: Product[]) => {
        this.products = response;
        // console.log(response);
      },
      (error: HttpErrorResponse) => {
        console.log(error.message)
      }
    )
  }

  public onAddProduct(productForm: NgForm): void{
    document.getElementById('add-product')?.click();
    this.productService.addProduct(productForm.value).subscribe(
      (response: Product)=> {
        // console.log(response);
        this.getProducts();
        const screen = document.getElementById('screen');
        screen?.classList.remove('show');
        const modal = document.getElementById('add-modal');
        modal?.classList.remove('show');

        // animation
        const SuccessNotification = document.getElementById('SuccessNotification');
        SuccessNotification?.classList.add('showAdded2');
        setTimeout(() => SuccessNotification?.classList.remove('showAdded2'), 3000);
      },
      (error: HttpErrorResponse) =>{
        console.log(error.message);
        // animation
        const ErrorNotification = document.getElementById('ErrorNotification');
        ErrorNotification?.classList.add('showAdded2');
        setTimeout(() => ErrorNotification?.classList.remove('showAdded2'), 3000);
      }
    );
    productForm.resetForm();
  }

  public onEditProduct(productForm: NgForm): void{
    document.getElementById('edit-product')?.click();
    this.productService.updateproduct(productForm.value).subscribe(
      (response: Product) => {
        // console.log(response);
        this.getProducts();
        const screen = document.getElementById('screen');
        screen?.classList.remove('show');
        const modal = document.getElementById('edit-modal');
        modal?.classList.remove('show');

        // animation
        const SuccessNotification = document.getElementById('SuccessNotification');
        SuccessNotification?.classList.add('showAdded2');
        setTimeout(() => SuccessNotification?.classList.remove('showAdded2'), 3000);
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
        // animation
        const ErrorNotification = document.getElementById('ErrorNotification');
        ErrorNotification?.classList.add('showAdded2');
        setTimeout(() => ErrorNotification?.classList.remove('showAdded2'), 3000);
      }
    );
  }

  public onDeleteProduct(productId: number): void{
    this.productService.deleteproduct(productId).subscribe(
      (response: void) => {
        // console.log(response);
        this.getProducts();
        const screen = document.getElementById('screen');
        screen?.classList.remove('show');
        const modal = document.getElementById('delete-modal');
        modal?.classList.remove('show');

        // animation
        const SuccessNotification = document.getElementById('SuccessNotification');
        SuccessNotification?.classList.add('showAdded2');
        setTimeout(() => SuccessNotification?.classList.remove('showAdded'), 3000);

      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
        // animation
        const ErrorNotification = document.getElementById('ErrorNotification');
        ErrorNotification?.classList.add('showAdded2');
        setTimeout(() => ErrorNotification?.classList.remove('showAdded2'), 3000);
      }
    );
  }

  public onOpenModal(product: Product, mode: string): void {
    const screen = document.getElementById('screen');
    screen?.classList.toggle('show');
    const modal = document.getElementById(`${mode}-modal`);
    modal?.classList.toggle('show');
    if(mode === 'edit'){
      this.editProduct = product;
    }
    if(mode === 'delete'){
      this.deleteProduct = product;
    }
  }

  public onCloseModal(mode: string){
    const screen = document.getElementById('screen');
    screen?.classList.remove('show');
    const modal = document.getElementById(`${mode}-modal`);
    modal?.classList.remove('show');
  }
}

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { ProductsComponent } from './products/products.component';
import { ProfilesComponent } from './profiles/profiles.component';
import { OrdersComponent } from './orders/orders.component';
import { ErrorPageComponent } from './error-page/error-page.component';
import { AdminComponent } from './admin/admin.component';
import { AuthGuard } from './auth.guard';
import { CheckoutPageComponent } from './checkout-page/checkout-page.component';

const routes: Routes = [
  { path: 'login', component: LoginPageComponent },
  { path: 'register', component: RegisterPageComponent },
  { path: 'shop', component: ProductsComponent, /*canActivate:[AuthGuard]*/ },
  { path: 'profile', component: ProfilesComponent, /*canActivate:[AuthGuard]*/ },
  { path: 'orders', component: OrdersComponent, /*canActivate:[AuthGuard]*/ },
  { path: 'admin', component: AdminComponent, /*canActivate:[AuthGuard]*/ },
  { path: 'checkout', component: CheckoutPageComponent, /*canActivate:[AuthGuard]*/ },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', component: ErrorPageComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

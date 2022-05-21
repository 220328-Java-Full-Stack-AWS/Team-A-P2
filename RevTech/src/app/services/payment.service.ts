import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Payment } from '../dto/payment';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private apiServiceUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }

  // get all payments
  public getAllPayments(): Observable<Payment[]> {
    return this.http.get<any>(`${this.apiServiceUrl}/payment/all`);
  }

  // get payment by id
  public getPaymentByPaymentId(paymentId: number): Observable<Payment> {
    return this.http.get<any>(`${this.apiServiceUrl}/payment/${paymentId}`);
  }

  // add payment
  public addNewPayment(payment: Payment): Observable<Payment> {
    return this.http.post<Payment>(`${this.apiServiceUrl}/payment/add`, payment);
  }

  // update payment
  public updatePayment(payment: Payment): Observable<Payment> {
    return this.http.put<Payment>(`${this.apiServiceUrl}/payment/update`, payment);
  }

  // delete payment by id
  public deletePayment(paymentId: number): Observable<void>{
    return this.http.delete<void>(`${this.apiServiceUrl}/payment/delete/${paymentId}`);
  }

    // get addresses by user Id
    public getPaymentsByUserId(userId: number): Observable<Payment[]>{
      return this.http.get<any>(`${this.apiServiceUrl}/payment/user/${userId}`);
    }

}

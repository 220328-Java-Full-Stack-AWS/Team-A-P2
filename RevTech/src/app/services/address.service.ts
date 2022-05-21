import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Address } from '../dto/address';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  private apiServiceUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }

  // get all addresses
  public getAllAddress(): Observable<Address[]> {
    return this.http.get<any>(`${this.apiServiceUrl}/address/all`);
  }

  // get address by id
  public getAddressByAddressId(addressId: number): Observable<Address> {
    return this.http.get<any>(`${this.apiServiceUrl}/address/${addressId}`);
  }

  // add address
  public addNewAddress(address: Address): Observable<Address> {
    return this.http.post<Address>(`${this.apiServiceUrl}/address/add`, address);
  }

  // update address
  public updateAddress(address: Address): Observable<Address> {
    return this.http.put<Address>(`${this.apiServiceUrl}/address/update`, address);
  }

  // delete address
  public deleteAddress(addressId: number): Observable<void>{
    return this.http.delete<void>(`${this.apiServiceUrl}/address/delete/${addressId}`);
  }

  // get addresses by user Id
  public getAddressesByUserId(userId: number): Observable<Address[]>{
    return this.http.get<any>(`${this.apiServiceUrl}/address/user/${userId}`);
  }

}

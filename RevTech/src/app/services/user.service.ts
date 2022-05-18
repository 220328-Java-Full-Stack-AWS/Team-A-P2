import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../dto/user';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiServiceUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getUsers(): Observable<User[]>{
    return this.http.get<any>(`${this.apiServiceUrl}/users`);
  }

  public registerUser(user: User): Observable<User>{
    return this.http.post<User>(`${this.apiServiceUrl}/users`, user);
  }

  public loginUser(user: User): Observable<User>{
    return this.http.post<User>(`${this.apiServiceUrl}/users`, user);
  }

  public updateUser(user: User): Observable<User>{
    return this.http.put<User>(`${this.apiServiceUrl}/users`, user);
  }

  public deleteUser(userId: number): Observable<void>{
    return this.http.delete<void>(`${this.apiServiceUrl}/users`);
  }
}

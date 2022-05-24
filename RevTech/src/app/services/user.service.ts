import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../dto/user';


@Injectable({
    providedIn: 'root'
  })
  export class UserService {
    private apiServiceUrl = environment.apiBaseUrl;
    constructor(private http: HttpClient) { }
  
    public registerUser(user: User): Observable<User>{
      return this.http.post<User>(
        `${this.apiServiceUrl}/users`,// url
        JSON.stringify(user), // object being passed
        {
          headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'mode': 'register'
          })
        }
      );
    }

    public loginUser(user: User): Observable<User>{
      return this.http.post<User>(
        `${this.apiServiceUrl}/users`,// url
        user, // object being passed
        {
          headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'mode': 'login'
          })
        }
      );
    }

    public getUsers(): Observable<User[]>{
      return this.http.get<any>(`${this.apiServiceUrl}/users`);
    }
  
    public updateUser(user: User): Observable<User>{
      return this.http.put<User>(`${this.apiServiceUrl}/users`, user);
    }
  
    public deleteUser(userId: number): Observable<void>{
      return this.http.delete<void>(
        `${this.apiServiceUrl}/users`,
        {
          headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'user_id': userId.toString()
          })
        }
        );
    }
  
    public getUserById(userId: number): Observable<User>{
      return this.http.get<User>(
        `${this.apiServiceUrl}/users/${userId}`, // url
        {
          headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'mode': 'id'
          })
        }
      );
    }
  
    public getUserByUsername(username: string): Observable<User>{
      return this.http.get<User>(
        `${this.apiServiceUrl}/users/${username}`, // url
        {
          headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'mode': 'username'
          })
        }
      );
    }

    public getUserByEmail(email: string): Observable<User>{
      return this.http.get<User>(
        `${this.apiServiceUrl}/users/${email}`, // url
        {
          headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'mode': 'email'
          })
        }
      );
    }

    public getUserByPhone(phone: string): Observable<User>{
      return this.http.get<User>(
        `${this.apiServiceUrl}/users/${phone}`, // url
        {
          headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'mode': 'phone'
          })
        }
      );
    }
  }

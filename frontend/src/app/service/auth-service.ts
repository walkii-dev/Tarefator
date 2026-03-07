import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private http = inject(HttpClient);
  private apiUrl = "http://localhost:8080/auth";

  private readonly TOKEN_KEY = "auth_token";
  private readonly USER_KEY = "user_firstName";

  private currentUserSubject = new BehaviorSubject<string | null>(this.getStoredUser());
  currentUser$ = this.currentUserSubject.asObservable();

  logon(logonCredentials:{email:string; password:string}){
    return this.http.post<{token:string}>(`${this.apiUrl}/login`,logonCredentials)
    .pipe(
      tap(response =>{
        localStorage.setItem(this.TOKEN_KEY,response.token);
        const nameToExibit = logonCredentials.email.split("@")[0];
        localStorage.setItem(this.USER_KEY,nameToExibit);
        this.currentUserSubject.next(nameToExibit);
      })
    );
  }

  registerUser(registerData: { fullname: string; email: string; password: string }) {
  return this.http.post<{ token: string }>(`${this.apiUrl}/register`, registerData)
    .pipe(
      tap(response => {
        if (response.token) {
          localStorage.setItem(this.TOKEN_KEY, response.token);
          localStorage.setItem(this.USER_KEY, registerData.fullname);
          this.currentUserSubject.next(registerData.fullname);
        }
      })
    );
}


  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  setSession(token: string, firstName: string): void {
    localStorage.setItem(this.TOKEN_KEY,token);
    localStorage.setItem(this.USER_KEY,firstName);
    this.currentUserSubject.next(firstName);
  }

  logout(): void {
    localStorage.removeItem(this.TOKEN_KEY);
    localStorage.removeItem(this.USER_KEY,);
    this.currentUserSubject.next(null);
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  private getStoredUser(): string | null {
    return localStorage.getItem(this.USER_KEY);
  }



}

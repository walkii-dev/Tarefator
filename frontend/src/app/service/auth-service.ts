import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly TOKEN_KEY = "auth_token";
  private readonly USER_KEY = "user_firstName";

  private currentUserSubject = new BehaviorSubject<string | null>(this.getStoredUser());

  currentUser$ = this.currentUserSubject.asObservable();

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

import { Component, inject } from '@angular/core';
import { Router, RouterLink } from "@angular/router";
import { AuthService } from '../../service/auth-service';
import { Observable } from 'rxjs';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-page-header',
  imports: [RouterLink, AsyncPipe],
  templateUrl: './page-header.html',
  styleUrl: './page-header.css'
})
export class PageHeader {
  private authService = inject(AuthService);
  private router = inject(Router);

  firstName$: Observable<string | null>  = this.authService.currentUser$;

  logout(): void{
    this.authService.logout();
    this.router.navigate(["/auth/login"]);
  }

  get userIsLogged(): boolean {
    return this.authService.isLoggedIn();
  }



}

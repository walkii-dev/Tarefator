import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../../service/auth-service';

@Component({
  selector: 'app-app-register',
  imports: [FormsModule, CommonModule],
  templateUrl: './app-register.html',
  styleUrl: './app-register.css',
})
export class AppRegister {

  private authService = inject(AuthService);
  private router = inject(Router);

  registerData = {
    userFullName: '',
    userEmail: '',
    userPassword: ''
  };

  errorMessage = "";

  doRegister(): void {
    if (!this.registerData.userFullName ||
        !this.registerData.userEmail ||
        !this.registerData.userPassword) {
          this.errorMessage = "Register data not found! please check your data.";
          return;
        }

        const payload = {
          fullname: this.registerData.userFullName,
          email: this.registerData.userEmail,
          password: this.registerData.userPassword
        };

        this.authService.registerUser(payload).subscribe({})
  }

  goToLogin(): void {
    this.router.navigate(["auth/login"]);
  }

}

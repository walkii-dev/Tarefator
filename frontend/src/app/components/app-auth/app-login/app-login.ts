import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../service/auth-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule],
  standalone: true,
  templateUrl: './app-login.html',
  styleUrl: './app-login.css',
})
export class AppLogin {

  loginCredentials = {
    userEmail: "",
    userPassword: ""
  }

  errorMessage: string = "";


  private authService = inject(AuthService);
  private router = inject(Router);

  doLogin(): void {
    if (!this.loginCredentials.userEmail || !this.loginCredentials.userPassword) {
      this.errorMessage="não há dados de login e senha.";
      return;
    }

    const payload = {
      email: this.loginCredentials.userEmail,
      password: this.loginCredentials.userPassword
    };

      this.authService.logon(payload).subscribe({
        next: () => {
          console.log("Login feito!");
          this.router.navigate(["/listTasks"]);
        },
        error: (error) => {
          console.log("Erro ao logar, verifique credenciais",error);
          this.errorMessage = "Credenciais inválidas.";
        }
      });
  }

  goToRegister(){
    this.router.navigate(["auth/register"]);
  }
}

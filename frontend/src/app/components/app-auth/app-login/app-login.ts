import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule],
  standalone: true,
  templateUrl: './app-login.html',
  styleUrl: './app-login.css',
})
export class AppLogin {
  logonCredentials = {
    userEmail: '',
    userPassword: ''
  };

  doLogin(): void {
    console.log('Dados submetidos:', this.logonCredentials);
  }

  recoverPassword(): void {
    console.log('Ação: Recuperar senha');
  }

}

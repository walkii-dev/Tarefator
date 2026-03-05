import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-app-register',
  imports: [FormsModule,CommonModule],
  templateUrl: './app-register.html',
  styleUrl: './app-register.css',
})
export class AppRegister {
  registerData = {
    userFullName: '',
    userEmail: '',
    userPassword: ''
  };

  doRegister(): void {
    console.log('Dados submetidos:', this.registerData);
  }

  backToLogin(): void {
    console.log('Ação: Retornar para tela de login');
  }

}

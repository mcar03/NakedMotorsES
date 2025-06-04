import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent {

  username: string = '';
  password: string = '';

  onLogin() {
    console.log('Usuario:', this.username);
    console.log('Contraseña:', this.password);
  }
}
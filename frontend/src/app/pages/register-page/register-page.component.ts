import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent {


  firstName = '';
  lastName = '';
  email = '';
  username = '';
  password = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ){

  }

  onRegister(): void {
    console.log('HOLA PACO')
    this.authService.register(
      {
        nombre: this.firstName,
        apellido: this.lastName,
        email: this.email,
        username: this.username,
        password: this.password
      }
    ).subscribe({
      next: (data) => {
        this.router.navigate(["/login"]);
      }
    })
  }
}

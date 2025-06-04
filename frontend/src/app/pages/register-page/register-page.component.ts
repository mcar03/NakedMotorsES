import { Component } from '@angular/core';

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

  onRegister() {
    console.log('Nombre:', this.firstName);
    console.log('Apellido:', this.lastName);
    console.log('Email:', this.email);
    console.log('Usuario:', this.username);
    console.log('Contraseña:', this.password);
    // Aquí podrías enviar los datos a tu servicio de backend o Firebase
  
  }
}

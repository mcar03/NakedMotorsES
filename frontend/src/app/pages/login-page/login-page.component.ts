import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent {

  username: string = '';
  password: string = '';


  constructor(private authService: AuthService,private router: Router){}

  login():void{
    this.authService.login(this.username, this.password).subscribe({
      next:(dataLogin) => {
        localStorage.setItem('token', dataLogin.jwtToken);
        localStorage.setItem('roles', dataLogin.roles);

        this.router.navigate(["/"]);
        window.location.reload();
      },
      error: (err) => console.error("Login failed", err)
    })
  }
}
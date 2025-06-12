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
      next:() => this.router.navigate(["/"]),
      error: (err) => console.error("Login failed", err)
    })
  }
}
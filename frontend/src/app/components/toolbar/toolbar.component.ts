import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';




@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent {

  isLogin: boolean = false;
  roleUser: string = ''

  constructor(private authService: AuthService,private router: Router){
    if(localStorage.getItem('token')){
      this.isLogin = true
    }
    if(localStorage.getItem('roles')){
      this.roleUser = localStorage.getItem('roles')!;
    }
  }

  canLikeProduct():void {
    if(!this.isLogin){
      this.router.navigate(["/login"]);
    }
  }
  
  logout():void{
    localStorage.clear();
    this.isLogin = false
    this.authService.logout()
  }
 
}

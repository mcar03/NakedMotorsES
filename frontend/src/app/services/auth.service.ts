import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
private loginUrl = "http://localhost:8080/auth/login";
private tokenKey = "authToken"
  constructor(private http: HttpClient, private router: Router) { }

  login(username:string, password:string ):Observable<any>{
    return this.http.post<any>(this.loginUrl, {username, password}).pipe(
      tap(response => {
        if(response.token){
          this.setToken(response.token);
          console.log(response.token)
        }
      })
    )
  }

  private setToken(token: string): void{
    localStorage.setItem(this.tokenKey, token);
  }

  private getToken() : string | null{
    return localStorage.getItem(this.tokenKey)
  }

  isAuthenticated(): boolean{
    const token = this.getToken();
    if(!token){
      return false;
    }
    const payload = JSON.parse(atob(token.split(".")[1]));
    const expiracion = payload.expiracion = 1000;
    return Date.now() < expiracion
  }

  logout(): void{
    localStorage.removeItem(this.tokenKey);
    this.router.navigate(["/login"])
  }
}

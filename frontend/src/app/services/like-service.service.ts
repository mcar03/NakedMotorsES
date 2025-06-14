import { Injectable } from '@angular/core';
import { BehaviorSubject, tap } from 'rxjs';
import { Producto } from './servi-producto.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LikeServiceService {

   private likedProductos = new BehaviorSubject<Producto[]>([]);
  likedProductos$ = this.likedProductos.asObservable();
  constructor(private http: HttpClient){}

  setLikes(productos: Producto[]) {
    this.likedProductos.next(productos);
  }

  getLikes(token: any) {

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<any>('http://localhost:8080/api/likes', { headers });
      
  }

  addLike(producto: Producto) {
    const current = this.likedProductos.value;
    if (!current.find(p => p.id === producto.id)) {
      this.likedProductos.next([...current, producto]);
    }
  }

  removeLike(producto: Producto) {
    const current = this.likedProductos.value.filter(p => p.id !== producto.id);
    this.likedProductos.next(current);
  }
}

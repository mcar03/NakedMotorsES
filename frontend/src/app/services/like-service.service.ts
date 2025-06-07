import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Producto } from './servi-producto.service';

@Injectable({
  providedIn: 'root'
})
export class LikeServiceService {

   private likedProductos = new BehaviorSubject<Producto[]>([]);
  likedProductos$ = this.likedProductos.asObservable();

  setLikes(productos: Producto[]) {
    this.likedProductos.next(productos);
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

import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

export interface ProductoCarrito {
  nombre: string;
  precio: number;
  cantidad: number;
  imagenurl: string;
}

@Injectable({
  providedIn: 'root'
})
export class CarritoService {
  private productos: ProductoCarrito[] = [];
  private productosSubject = new BehaviorSubject<ProductoCarrito[]>([]);

  productos$ = this.productosSubject.asObservable();

  añadirProducto(producto: ProductoCarrito) {
    const index = this.productos.findIndex(p => p.nombre === producto.nombre);
    if (index > -1) {
      this.productos[index].cantidad += 1;
    } else {
      this.productos.push({ ...producto, cantidad: 1 });
    }
    this.productosSubject.next([...this.productos]);
  }

  eliminarProducto(index: number) {
    this.productos.splice(index, 1);
    this.productosSubject.next([...this.productos]);
  }

  vaciarCarrito() {
    this.productos = [];
    this.productosSubject.next([]);
  }

  getProductos(): ProductoCarrito[] {
    return this.productos;
  }

  getTotal(): number {
    return this.productos.reduce((acc, p) => acc + p.precio * p.cantidad, 0);
  }
}

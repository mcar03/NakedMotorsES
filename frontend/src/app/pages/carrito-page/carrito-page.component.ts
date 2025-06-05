import { Component } from '@angular/core';

interface ProductoCarrito {
  nombre: string;
  precio: number;
  cantidad: number;
  imagen: string;
}


@Component({
  selector: 'app-carrito-page',
  templateUrl: './carrito-page.component.html',
  styleUrls: ['./carrito-page.component.css']
})
export class CarritoPageComponent {

  productos: ProductoCarrito[] = [
    {
      nombre: 'Casco Integral',
      precio: 120,
      cantidad: 1,
      imagen: 'assets/img/img-prueba-local/casco-shark.jpg'
    },
    {
      nombre: 'Guantes Racing',
      precio: 45,
      cantidad: 2,
      imagen: 'assets/img/img-prueba-local/guantes-carbono.jpg'
    }
  ];

  get total(): number {
    return this.productos.reduce((acc, p) => acc + p.precio * p.cantidad, 0);
  }

  eliminarProducto(index: number) {
    this.productos.splice(index, 1);
  }

  vaciarCarrito() {
    this.productos = [];
  }
}

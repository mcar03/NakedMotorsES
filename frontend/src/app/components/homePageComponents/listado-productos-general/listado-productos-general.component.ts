import { Component } from '@angular/core';

@Component({
  selector: 'app-listado-productos-general',
  templateUrl: './listado-productos-general.component.html',
  styleUrls: ['./listado-productos-general.component.css']
})
export class ListadoProductosGeneralComponent {

   productos = [
   {
    nombre: 'Casco integral Shark',
    precio: 129.99,
    imagen: 'assets/img/img-prueba-local/casco-shark.jpg',
    liked: false
  },
  {
    nombre: 'Guantes racing carbono',
    precio: 59.95,
    imagen: 'assets/img/img-prueba-local/guantes-carbono.jpg',
    liked: false
  },
  {
    nombre: 'Chaqueta touring Pro',
    precio: 199.90,
    imagen: 'assets/img/img-prueba-local/chaqueta-touring-pro.jpg',
    liked: false
  },
  {
    nombre: 'Escape deportivo Akrapovic',
    precio: 349.00,
    imagen: 'assets/img/escape.jpg',
    liked: false
  },
  {
    nombre: 'Botas Racing Evo',
    precio: 149.99,
    imagen: 'assets/img/botas.jpg',
    liked: false
  },
  {
    nombre: 'Mono de cuero completo',
    precio: 499.95,
    imagen: 'assets/img/mono.jpg',
    liked: false
  }
    
  ];

  paginaActual = 1;
  productosPorPagina = 5;

  toggleLike(producto: any): void {
  producto.liked = !producto.liked;
}

  get productosPaginados() {
    const inicio = (this.paginaActual - 1) * this.productosPorPagina;
    return this.productos.slice(inicio, inicio + this.productosPorPagina);
  }

  get totalPaginas() {
    return Math.ceil(this.productos.length / this.productosPorPagina);
  }

  cambiarPagina(pagina: number) {
    this.paginaActual = pagina;
  }
}

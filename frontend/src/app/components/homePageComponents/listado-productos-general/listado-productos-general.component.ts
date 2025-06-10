import { HttpClient } from '@angular/common/http';
import { Component, Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { CarritoService } from 'src/app/services/carrito-service.service';
import { ServiProductoService } from 'src/app/services/servi-producto.service';


export interface Producto {
  id: number;
  nombre: string;
  descripcion: string;
  talla: string;
  precio: number;
  stock: number;
  imagenurl: string;
  liked?: boolean;
  categoriaId: number;
  categoriaNombre: string;
}



@Component({
  selector: 'app-listado-productos-general',
  templateUrl: './listado-productos-general.component.html',
  styleUrls: ['./listado-productos-general.component.css']
})
export class ListadoProductosGeneralComponent {

  productos: Producto[] = [];
  paginaActual = 1;
  productosPorPagina = 5;

 constructor(private productoService: ServiProductoService,private carritoService: CarritoService,private snackBar: MatSnackBar) {}
   
     
 
   annadirAlCarrito(producto: any) {
     this.carritoService.añadirProducto({
       nombre: producto.nombre,
       precio: producto.precio,
       imagenurl: producto.imagenurl,
       cantidad: 1
     });
     this.snackBar.open(`${producto.nombre} añadido al carrito`, 'Cerrar', {
       duration: 2500,
       panelClass: ['snackbar-success']
     }); 
   }
  ngOnInit(): void {
    this.productoService.obtenerProductos().subscribe(data => {
      // Añadimos el campo "liked" por si no viene del backend
      this.productos = data.map(p => ({ ...p, liked: false }));
    });
  }

  get productosPaginados(): Producto[] {
    const inicio = (this.paginaActual - 1) * this.productosPorPagina;
    return this.productos.slice(inicio, inicio + this.productosPorPagina);
  }

  get totalPaginas(): number {
    return Math.ceil(this.productos.length / this.productosPorPagina);
  }

  cambiarPagina(pagina: number): void {
    this.paginaActual = pagina;
  }

  toggleLike(producto: Producto): void {
    producto.liked = !producto.liked;
  }
}

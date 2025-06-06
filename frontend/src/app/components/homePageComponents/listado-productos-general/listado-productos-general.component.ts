import { HttpClient } from '@angular/common/http';
import { Component, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
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


@Injectable({
  providedIn: 'root'
})
@Component({
  selector: 'app-listado-productos-general',
  templateUrl: './listado-productos-general.component.html',
  styleUrls: ['./listado-productos-general.component.css']
})
export class ListadoProductosGeneralComponent {

  productos: Producto[] = [];
  paginaActual = 1;
  productosPorPagina = 5;

  constructor(private productoService: ServiProductoService) {}

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

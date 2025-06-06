import { Component, OnInit } from '@angular/core';
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
  selector: 'app-listado-productos-motos',
  templateUrl: './listado-productos-motos.component.html',
  styleUrls: ['./listado-productos-motos.component.css']
})
export class ListadoProductosMotosComponent implements OnInit {

  productos: Producto[] = [];
  productosPorCategoria: { [categoria: string]: Producto[] } = {};

  constructor(private productoService: ServiProductoService) {}

  ngOnInit(): void {
    this.productoService.obtenerProductos().subscribe(data => {
      this.productos = data
        .filter(p => p.categoriaNombre === 'Motos')
        .map(p => ({ ...p, liked: false }));
      this.organizarPorCategoria();
    });
  }

  organizarPorCategoria(): void {
    this.productosPorCategoria = {};
    for (const producto of this.productos) {
      const categoria = producto.categoriaNombre;
      if (!this.productosPorCategoria[categoria]) {
        this.productosPorCategoria[categoria] = [];
      }
      this.productosPorCategoria[categoria].push(producto);
    }
  }

  categorias(): string[] {
    return Object.keys(this.productosPorCategoria);
  }

  toggleLike(producto: Producto): void {
    producto.liked = !producto.liked;
  }
}

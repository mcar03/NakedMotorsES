import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
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
  selector: 'app-listado-botas',
  templateUrl: './listado-botas.component.html',
  styleUrls: ['./listado-botas.component.css']
})
export class ListadoBotasComponent {

  productos: Producto[] = [];
    productosPorCategoria: { [categoria: string]: Producto[] } = {};
  
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
        this.productos = data
          .filter(p => p.categoriaNombre === 'Botas')
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

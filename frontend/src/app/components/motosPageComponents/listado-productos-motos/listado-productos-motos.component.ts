import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CarritoService } from 'src/app/services/carrito-service.service';
import { LikeServiceService } from 'src/app/services/like-service.service';
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

  constructor(private productoService: ServiProductoService,private carritoService: CarritoService,private snackBar: MatSnackBar, private likeService: LikeServiceService) {}
    
      
  
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

//  ngOnInit(): void {
//   this.productoService.obtenerProductos().subscribe(productos => {
//     this.productos = productos.filter(p => p.categoriaNombre === 'Motos');
//     this.productoService.obtenerLikesUsuario().subscribe(likes => {
//       const likedIds = new Set(likes.map(l => l.producto.id));
//       this.productos = this.productos.map(p => ({ ...p, liked: likedIds.has(p.id) }));
//       this.organizarPorCategoria();
//     });
//   });
// }
ngOnInit(): void {
  this.productoService.obtenerProductos().subscribe(productos => {
    this.productos = productos.filter(p => p.categoriaNombre === 'Motos');
    this.productoService.obtenerLikesUsuario().subscribe(likes => {
      const likedIds = new Set(likes.map(l => l.producto.id));
      this.productos = this.productos.map(p => ({ ...p, liked: likedIds.has(p.id) }));
      this.organizarPorCategoria();

      // Actualizar el LikeService con los productos liked
      const likedProductos = this.productos.filter(p => p.liked);
      this.likeService.setLikes(likedProductos);
    });
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
  this.productoService.toggleLike(producto.id).subscribe({
    next: (nuevoEstado: boolean) => {
      producto.liked = nuevoEstado;
      if (nuevoEstado) {
        this.likeService.addLike(producto);
      } else {
        this.likeService.removeLike(producto);
      }
    },
    error: (err) => {
      console.error('Error toggle like:', err);
    }
  });
}
}

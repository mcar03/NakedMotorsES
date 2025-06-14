import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
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
  selector: 'app-listado-productos-ruedas-component',
  templateUrl: './listado-productos-ruedas-component.component.html',
  styleUrls: ['./listado-productos-ruedas-component.component.css']
})
export class ListadoProductosRuedasComponentComponent {

  productos: Producto[] = [];
  productosPorCategoria: { [categoria: string]: Producto[] } = {};
  isLogin: boolean = false;
  roleUser: string = '';
  productLikes ?: any[];

  constructor(
    private productoService: ServiProductoService, 
    private carritoService: CarritoService, 
    private snackBar: MatSnackBar,
    private router: Router
  ) {
    if (localStorage.getItem('token')) {
      this.isLogin = true
    }
    if (localStorage.getItem('roles')) {
      this.roleUser = localStorage.getItem('roles')!;
    }
  }

  canLikeProduct(): void {
    if (!this.isLogin) {
      this.router.navigate(["/login"]);
      return;
    }
  }

  annadirAlCarrito(producto: any) {
    this.canLikeProduct();
    this.carritoService.añadirProducto({
      nombre: producto.nombre,
      precio: producto.precio,
        id: producto.id,
      imagenurl: producto.imagenurl,
      cantidad: 1
    } ,localStorage.getItem('token'));
    this.snackBar.open(`${producto.nombre} añadido al carrito`, 'Cerrar', {
      duration: 2500,
      panelClass: ['snackbar-success']
    });
  }

  ngOnInit(): void {
    this.productoService.obtenerProductos().subscribe(data => {
      this.productos = data
        .filter(p => p.categoriaNombre === 'Ruedas')
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
    this.canLikeProduct();
    console.log('producto ' ,producto.id)
    console.log(localStorage.getItem('token'))
    this.productoService.toggleLike(producto.id, localStorage.getItem('token')).subscribe((data) => {
      this.productLikes = data;
    })
    producto.liked = !producto.liked;
  }
}

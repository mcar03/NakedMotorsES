import { HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { CarritoService } from 'src/app/services/carrito-service.service';
import { LikeServiceService } from 'src/app/services/like-service.service';
import { Producto, ServiProductoService } from 'src/app/services/servi-producto.service';

@Component({
  selector: 'app-like-page',
  templateUrl: './like-page.component.html',
  styleUrls: ['./like-page.component.css']
})
export class LikePageComponent {

    isLogin: boolean = false;
  roleUser: string = '';
  productLikes ?: any[];
     

constructor(
  private likeService: LikeServiceService,
  private productoService: ServiProductoService,
  private carritoService: CarritoService, 
  private snackBar: MatSnackBar,
  private router: Router
) {}
 productos: Producto[] = [];
  productosLike : any[] =[];

  ngOnInit(): void {
    this.getLikeProduct();
    this.likeService.likedProductos$.subscribe(productos => {
      this.productos = productos;
    });

    // Carga inicial de likes desde backend para sincronizar
    this.productoService.obtenerLikesUsuario().subscribe(likes => {
      const productos = likes.map(l => l.producto);
      this.likeService.setLikes(productos);
    });
  }

    canLikeProduct(): void {
    if (!this.isLogin) {
      this.router.navigate(["/login"]);
      return;
    }
  }

  annadirAlCarrito(producto: any) {
    this.carritoService.añadirProducto({
      nombre: producto.nombre,
        id: producto.id,
      precio: producto.precio,
      imagenurl: producto.imagenurl,
      cantidad: 1
    },localStorage.getItem('token'));
    this.snackBar.open(`${producto.nombre} añadido al carrito`, 'Cerrar', {
      duration: 2500,
      panelClass: ['snackbar-success']
    });

          
  }

  cargarLikes(): void {
    this.productoService.obtenerLikesUsuario().subscribe(likes => {
      this.productos = likes.map(l => l.producto);
    });
  }

  getLikeProduct() {
    this.likeService.getLikes(localStorage.getItem('token')).subscribe({
      next: (data) => {
        this.productosLike = data;
      }
    })
  }

}

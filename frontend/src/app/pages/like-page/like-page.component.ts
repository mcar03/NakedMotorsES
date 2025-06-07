import { Component } from '@angular/core';
import { LikeServiceService } from 'src/app/services/like-service.service';
import { Producto, ServiProductoService } from 'src/app/services/servi-producto.service';

@Component({
  selector: 'app-like-page',
  templateUrl: './like-page.component.html',
  styleUrls: ['./like-page.component.css']
})
export class LikePageComponent {

  constructor(
  private likeService: LikeServiceService,
  private productoService: ServiProductoService
) {}

 productos: Producto[] = [];

ngOnInit(): void {
  this.likeService.likedProductos$.subscribe(productos => {
    this.productos = productos;
  });

  // Carga inicial de likes desde backend para sincronizar
  this.productoService.obtenerLikesUsuario().subscribe(likes => {
    const productos = likes.map(l => l.producto);
    this.likeService.setLikes(productos);
  });
}

cargarLikes(): void {
  this.productoService.obtenerLikesUsuario().subscribe(likes => {
    this.productos = likes.map(l => l.producto);
  });
}


}

import { Component, OnInit } from '@angular/core';
import { CarritoService, ProductoCarrito } from 'src/app/services/carrito-service.service';


@Component({
  selector: 'app-carrito-page',
  templateUrl: './carrito-page.component.html',
  styleUrls: ['./carrito-page.component.css']
})
export class CarritoPageComponent implements OnInit {
  productos: ProductoCarrito[] = [];

  constructor(private carritoService: CarritoService) {}

  ngOnInit() {
    this.carritoService.productos$.subscribe(data => {
      this.productos = data;
    });
  }

  get total(): number {
    return this.carritoService.getTotal();
  }

  eliminarProducto(index: number) {
    this.carritoService.eliminarProducto(index);
  }

  vaciarCarrito() {
    this.carritoService.vaciarCarrito();
  }

  actualizarTotal() {
    // Forzar detección de cambios o simplemente recargar los productos
    this.productos = [...this.productos];
  }
}

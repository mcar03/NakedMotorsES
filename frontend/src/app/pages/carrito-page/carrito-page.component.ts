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
    this.carritoService.getProductosCarrito(localStorage.getItem('token')).subscribe({
      next: (data) => {
        console.log('DATA ' ,data);
        this.productos = data;
      }
    })
    // this.carritoService.productos$.subscribe(data => {
    //   this.productos = data;
    // });
  }

  get total(): number {
    return this.carritoService.getTotal();
  }

  eliminarProducto(index: any) {
    console.log(index)
    this.carritoService.eliminarProducto(index,localStorage.getItem('token'));

  }

  vaciarCarrito() {
    this.carritoService.vaciarCarrito();
  }

  actualizarTotal() {
    // Forzar detección de cambios o simplemente recargar los productos
    this.productos = [...this.productos];
  }
  comprarProducto(){
    const totalUnidades = this.productos.reduce((total, producto) => {
        this.carritoService.eliminarStock(producto.id!,localStorage.getItem('token'),producto.cantidad);
      return total + (producto.cantidad || 0);
    }, 0);

    console.log('Total de unidades compradas:', totalUnidades);
    console.log('Productos:', this.productos);

  }

  
}

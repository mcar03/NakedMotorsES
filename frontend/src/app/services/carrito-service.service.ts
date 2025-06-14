import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';

export interface ProductoCarrito {
  nombre: string;
  id ?: number;
  precio: number;
  cantidad: number;
  productoId?: number;
  imagenurl: string;
}

@Injectable({
  providedIn: 'root'
})
export class CarritoService {
  private productos: ProductoCarrito[] = [];
  private productosSubject = new BehaviorSubject<ProductoCarrito[]>([]);

  productos$ = this.productosSubject.asObservable();
  constructor(private http: HttpClient,private snackService : MatSnackBar, private route: Router){}

  añadirProducto(producto: ProductoCarrito,token:any) {
    const index = this.productos.findIndex(p => p.nombre === producto.nombre);
    if (index > -1) {
      this.productos[index].cantidad += 1;
    } else {
      this.productos.push({ ...producto, cantidad: 1 });
    }
    this.productosSubject.next([...this.productos]);

    console.log('producto ' ,producto);
    this.addProductBack(producto,token).subscribe()
  }

  addProductBack(producto: ProductoCarrito,token:any){
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    console.log('producto ' ,producto);
    return this.http.post<any>(`http://localhost:8080/api/carro/add/${producto.id}`, {...producto, cantidad: 1},{ headers });
  }

  eliminarProducto(index: number,token:any) {
    this.productos.splice(index, 1);
    this.productosSubject.next([...this.productos]);
    this.deleteProductosCarrito(index,token).subscribe(() => {
      this.snackService.open("Su compra se ha realizado con exito", 'Cerrar',{
        duration: 2500,
        panelClass: ['snackbar-success']
      });
      this.route.navigate(["/"]);
    });
  }

  
  eliminarStock(index: number,token:any,cantidad:number) {
    this.deleteProductosStock(index,token,cantidad).subscribe(() => {});
  }


  vaciarCarrito() {
    this.productos = [];
    this.productosSubject.next([]);
  }

  getProductos(): ProductoCarrito[] {
    return this.productos;
  }
  
  getProductosCarrito(token:any) {
  
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<ProductoCarrito[]>(`http://localhost:8080/api/carro`, { headers });
  
  }

  deleteProductosCarrito(productoId: number,token:any) {
  
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.delete<ProductoCarrito[]>(`http://localhost:8080/api/carro/del/${productoId}`, { headers });
  
  }
  
  deleteProductosStock(productoId: number,token:any,cantidad: number) {
  
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.delete<any>(`http://localhost:8080/api/productos/delete-stock/${productoId}`, {body:{cantidad:cantidad}, headers });
  
  }

  getTotal(): number {
    return this.productos.reduce((acc, p) => acc + p.precio * p.cantidad, 0);
  }
}

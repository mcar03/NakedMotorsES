import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Producto {
  id: number;
  nombre: string;
  descripcion: string;
  talla: string;
  precio: number;
  stock: number;
  imagenurl: string;
  // liked?: boolean;
  categoriaId: number;
  categoriaNombre: string;
}

@Injectable({
  providedIn: 'root'
})
export class ServiProductoService {

  private apiUrl = 'http://localhost:8080/api/productos';

  

  constructor(private http: HttpClient) {}

  obtenerProductos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(this.apiUrl);
  }

  eliminarProducto(id: number) {
  return this.http.delete(`http://localhost:8080/api/productos/${id}`);
}

toggleLike(productoId: number,token: any): Observable<any[]> {

  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`
  });
  return this.http.post<any>(`http://localhost:8080/api/likes/${productoId}/toggle`, {},{ headers });

}
obtenerLikesUsuario(): Observable<{producto: Producto}[]> {
  return this.http.get<{producto: Producto}[]>('http://localhost:8080/api/likes');
}



}

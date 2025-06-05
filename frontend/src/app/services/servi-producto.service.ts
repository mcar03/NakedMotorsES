import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Producto {
  nombre: string;
  precio: number;
  imagenurl: string;
  liked?: boolean;
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
}

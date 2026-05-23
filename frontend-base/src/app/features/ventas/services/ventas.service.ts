import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer, Product, SaleRecord, SaleRequest } from '../interfaces/ventas.interface';

@Injectable({
  providedIn: 'root'
})
export class VentasService {
  private readonly apiBaseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(`${this.apiBaseUrl}/customers`);
  }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiBaseUrl}/products`);
  }

  getSales(): Observable<SaleRecord[]> {
    return this.http.get<SaleRecord[]>(`${this.apiBaseUrl}/sales`);
  }

  createSale(request: SaleRequest): Observable<SaleRecord> {
    return this.http.post<SaleRecord>(`${this.apiBaseUrl}/sales`, request);
  }
}
import { Component, OnInit } from '@angular/core';
import { VentasService } from '../../services/ventas.service';
import { SaleRecord } from '../../interfaces/ventas.interface';

@Component({
  selector: 'app-listado-ventas',
  standalone: false,
  templateUrl: './listado-ventas.component.html',
  styleUrls: ['./listado-ventas.component.css']
})
export class ListadoVentasComponent implements OnInit {
  sales: SaleRecord[] = [];
  filteredSales: SaleRecord[] = [];
  searchTerm = '';
  sortBy: 'date' | 'total' = 'date';
  loading = false;
  errorMessage = '';

  constructor(private ventasService: VentasService) {}

  ngOnInit(): void {
    this.loadSales();
  }

  loadSales(): void {
    this.loading = true;
    this.ventasService.getSales().subscribe({
      next: data => {
        this.sales = data;
        this.applyFilters();
        this.loading = false;
      },
      error: () => {
        this.errorMessage = 'No se pudo cargar el listado de ventas.';
        this.loading = false;
      }
    });
  }

  applyFilters(): void {
    const term = this.searchTerm.trim().toLowerCase();

    const sales = this.sales.filter(sale => {
      if (!term) {
        return true;
      }

      return sale.clientName.toLowerCase().includes(term) || sale.saleDate.toLowerCase().includes(term);
    });

    this.filteredSales = [...sales].sort((a, b) => {
      if (this.sortBy === 'total') {
        return Number(b.total) - Number(a.total);
      }

      return new Date(b.saleDate).getTime() - new Date(a.saleDate).getTime();
    });
  }

  getProductsCount(sale: SaleRecord): number {
    return sale.items?.reduce((acc, item) => acc + Number(item.quantity || 0), 0) ?? 0;
  }

  getItemsLabel(sale: SaleRecord): string {
    return sale.items?.map(item => item.productName).join(', ') || 'Sin productos';
  }

  getSalesTotal(): number {
    return this.filteredSales.reduce((acc, sale) => acc + Number(sale.total || 0), 0);
  }

  getSalesCount(): number {
    return this.filteredSales.length;
  }
}
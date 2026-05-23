import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard-ventas',
  standalone: false,
  templateUrl: './dashboard-ventas.component.html',
  styleUrl: './dashboard-ventas.component.css'
})
export class DashboardVentasComponent {
  showRegistro = false;
  showListado = false;

  openRegistro(): void {
    this.showRegistro = true;
    this.showListado = false;
    setTimeout(() => window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' }), 150);
  }

  openListado(): void {
    this.showListado = true;
    this.showRegistro = false;
    setTimeout(() => window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' }), 150);
  }

  closeAll(): void {
    this.showListado = false;
    this.showRegistro = false;
  }
}
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { VentasService } from '../../services/ventas.service';
import { Customer, Product, SaleItem, SaleRequest } from '../../interfaces/ventas.interface';

@Component({
  selector: 'app-registro-venta',
  standalone: false,
  templateUrl: './registro-venta.component.html',
  styleUrls: ['./registro-venta.component.css']
})
export class RegistroVentaComponent implements OnInit {
  ventaForm: FormGroup;
  customers: Customer[] = [];
  products: Product[] = [];
  message = '';
  errorMessage = '';

  constructor(private fb: FormBuilder, private ventasService: VentasService) {
    this.ventaForm = this.fb.group({
      clientId: [null, Validators.required],
      saleDate: [new Date().toISOString().slice(0, 10), Validators.required],
      items: this.fb.array([])
    });
  }

  ngOnInit(): void {
    this.loadCustomers();
    this.loadProducts();
    this.addItem();
  }

  get items(): FormArray {
    return this.ventaForm.get('items') as FormArray;
  }

  get totalGeneral(): number {
    return this.items.controls.reduce((acc, control) => acc + Number(control.get('subtotal')?.value || 0), 0);
  }

  loadCustomers(): void {
    this.ventasService.getCustomers().subscribe({
      next: data => this.customers = data,
      error: () => this.errorMessage = 'No se pudieron cargar los clientes.'
    });
  }

  loadProducts(): void {
    this.ventasService.getProducts().subscribe({
      next: data => this.products = data,
      error: () => this.errorMessage = 'No se pudieron cargar los productos.'
    });
  }

  createItemForm(): FormGroup {
    return this.fb.group({
      productId: [null, Validators.required],
      productName: [''],
      quantity: [1, [Validators.required, Validators.min(1)]],
      unitPrice: [0, [Validators.required, Validators.min(0)]],
      subtotal: [0],
      stock: [0]
    });
  }

  addItem(): void {
    const item = this.createItemForm();
    this.items.push(item);
    this.updateItemValues(this.items.length - 1);
  }

  removeItem(index: number): void {
    if (this.items.length === 1) {
      this.items.at(0).reset({ productId: null, productName: '', quantity: 1, unitPrice: 0, subtotal: 0, stock: 0 });
      return;
    }

    this.items.removeAt(index);
  }

  updateItemValues(index: number): void {
    const itemGroup = this.items.at(index) as FormGroup;
    const productId = Number(itemGroup.get('productId')?.value);
    const quantity = Number(itemGroup.get('quantity')?.value || 0);
    const product = this.products.find(current => current.id === productId);

    if (!product) {
      itemGroup.patchValue({ productName: '', unitPrice: 0, subtotal: 0, stock: 0 }, { emitEvent: false });
      return;
    }

    const subtotal = this.roundMoney(quantity * Number(product.price || 0));
    itemGroup.patchValue(
      {
        productName: product.name,
        unitPrice: product.price,
        stock: product.stock,
        subtotal
      },
      { emitEvent: false }
    );
  }

  onProductChange(index: number): void {
    const itemGroup = this.items.at(index) as FormGroup;
    const productId = Number(itemGroup.get('productId')?.value);
    const product = this.products.find(current => current.id === productId);

    if (product) {
      itemGroup.patchValue({
        productName: product.name,
        unitPrice: product.price,
        stock: product.stock,
        quantity: 1
      });
    }

    this.updateItemValues(index);
  }

  onQuantityChange(index: number): void {
    this.updateItemValues(index);
  }

  getRowError(index: number): string {
    const itemGroup = this.items.at(index) as FormGroup;
    const quantity = Number(itemGroup.get('quantity')?.value || 0);
    const stock = Number(itemGroup.get('stock')?.value || 0);

    if (!itemGroup.get('productId')?.value) {
      return 'Seleccione un producto.';
    }

    if (quantity <= 0) {
      return 'La cantidad debe ser mayor a cero.';
    }

    if (stock > 0 && quantity > stock) {
      return `Stock insuficiente. Disponible: ${stock}.`;
    }

    return '';
  }

  saveSale(): void {
    this.message = '';
    this.errorMessage = '';

    if (this.ventaForm.invalid || this.items.length === 0) {
      this.ventaForm.markAllAsTouched();
      this.errorMessage = 'Complete los campos obligatorios antes de registrar la venta.';
      return;
    }

    const clientId = Number(this.ventaForm.get('clientId')?.value);
    const customer = this.customers.find(current => current.id === clientId);

    if (!customer) {
      this.errorMessage = 'Seleccione un cliente válido.';
      return;
    }

    const invalidRow = this.items.controls.find(control => {
      const productId = Number(control.get('productId')?.value || 0);
      const quantity = Number(control.get('quantity')?.value || 0);
      const stock = Number(control.get('stock')?.value || 0);
      return !productId || quantity <= 0 || quantity > stock;
    });

    if (invalidRow) {
      this.errorMessage = 'Revise los productos agregados y valide el stock.';
      return;
    }

    const items: SaleItem[] = this.items.controls.map(control => ({
      productId: Number(control.get('productId')?.value),
      productName: String(control.get('productName')?.value || ''),
      quantity: Number(control.get('quantity')?.value),
      unitPrice: this.roundMoney(Number(control.get('unitPrice')?.value || 0)),
      subtotal: this.roundMoney(Number(control.get('subtotal')?.value || 0)),
      stock: Number(control.get('stock')?.value || 0)
    }));

    const payload: SaleRequest = {
      clientId: customer.id,
      clientName: customer.name,
      saleDate: this.ventaForm.get('saleDate')?.value,
      items,
      total: this.roundMoney(this.totalGeneral)
    };

    this.ventasService.createSale(payload).subscribe({
      next: () => {
        this.message = 'Venta registrada correctamente.';
        this.resetForm();
      },
      error: error => {
        const backendMessage = error?.error?.message || error?.message || 'No se pudo registrar la venta.';
        this.errorMessage = backendMessage;
      }
    });
  }

  resetForm(): void {
    this.ventaForm.reset({
      clientId: null,
      saleDate: new Date().toISOString().slice(0, 10)
    });
    this.items.clear();
    this.addItem();
  }

  trackByIndex(index: number): number {
    return index;
  }

  private roundMoney(value: number): number {
    return Math.round((value + Number.EPSILON) * 100) / 100;
  }
}
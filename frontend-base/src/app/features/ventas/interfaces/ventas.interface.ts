export interface Customer {
  id: number;
  name: string;
  document?: string;
  email?: string;
  phone?: string;
}

export interface Product {
  id: number;
  name: string;
  sku?: string;
  category?: string;
  price: number;
  stock: number;
  description?: string;
}

export interface SaleItem {
  productId: number | null;
  productName: string;
  quantity: number;
  unitPrice: number;
  subtotal: number;
  stock: number;
}

export interface SaleRequest {
  clientId: number | null;
  clientName: string;
  saleDate: string;
  items: SaleItem[];
  total: number;
}

export interface SaleRecord extends SaleRequest {
  id: number;
  createdAt: string;
}

export interface SaleItemForm {
  productId: number | null;
  quantity: number | null;
}
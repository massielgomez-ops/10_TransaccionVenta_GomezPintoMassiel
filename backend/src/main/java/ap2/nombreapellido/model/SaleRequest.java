package ap2.nombreapellido.model;

import java.util.List;

public class SaleRequest {

  private Long clientId;
  private String clientName;
  private String saleDate;
  private List<SaleItem> items;
  private Double total;

  public SaleRequest() {
  }

  public Long getClientId() {
    return clientId;
  }

  public void setClientId(Long clientId) {
    this.clientId = clientId;
  }

  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }

  public String getSaleDate() {
    return saleDate;
  }

  public void setSaleDate(String saleDate) {
    this.saleDate = saleDate;
  }

  public List<SaleItem> getItems() {
    return items;
  }

  public void setItems(List<SaleItem> items) {
    this.items = items;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }
}
package ap2.nombreapellido.model;

import java.util.List;

public class SaleRecord extends SaleRequest {

  private Long id;
  private String createdAt;

  public SaleRecord() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public List<SaleItem> getItems() {
    return super.getItems();
  }
}
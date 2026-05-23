package ap2.nombreapellido.model;

public class Customer {

  private Long id;
  private String name;
  private String document;
  private String email;
  private String phone;

  public Customer() {
  }

  public Customer(Long id, String name, String document, String email, String phone) {
    this.id = id;
    this.name = name;
    this.document = document;
    this.email = email;
    this.phone = phone;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDocument() {
    return document;
  }

  public void setDocument(String document) {
    this.document = document;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
}
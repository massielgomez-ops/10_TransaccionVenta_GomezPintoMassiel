package ap2.nombreapellido.repository.impl;

import ap2.nombreapellido.model.Product;
import ap2.nombreapellido.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryProductRepository implements ProductRepository {

  private final List<Product> products = new ArrayList<>(List.of(
      new Product(11L, "Laptop Essential 15", "LE-015", "Tecnología", 2890.0, 10, "Equipo portátil para oficina y estudio."),
      new Product(12L, "Tablet Pro 11", "TP-011", "Tecnología", 1650.0, 14, "Tablet con lápiz y pantalla de alta resolución."),
      new Product(13L, "Auriculares ANC", "ANC-013", "Accesorios", 260.0, 28, "Auriculares con cancelación activa de ruido."),
      new Product(14L, "Monitor Curvo 32\"", "MC-032", "Tecnología", 1250.0, 9, "Monitor curvo ideal para productividad."),
      new Product(15L, "Teclado Mecánico RGB", "TK-015", "Accesorios", 290.0, 18, "Teclado gaming retroiluminado RGB."),
      new Product(16L, "Mouse Gamer", "MG-016", "Accesorios", 140.0, 25, "Mouse de alta precisión para juegos."),
      new Product(17L, "Impresora Multifuncional", "IM-017", "Oficina", 920.0, 7, "Impresora, escáner y copiadora en un solo equipo."),
      new Product(18L, "Silla Ergonómica", "SE-018", "Oficina", 780.0, 11, "Silla cómoda para largas jornadas de trabajo.")
  ));

  @Override
  public List<Product> findAll() {
    return new ArrayList<>(products);
  }

  @Override
  public Optional<Product> findById(Long id) {
    return products.stream().filter(product -> product.getId().equals(id)).findFirst();
  }

  @Override
  public Product save(Product product) {
    products.removeIf(existing -> existing.getId().equals(product.getId()));
    products.add(product);
    return product;
  }
}
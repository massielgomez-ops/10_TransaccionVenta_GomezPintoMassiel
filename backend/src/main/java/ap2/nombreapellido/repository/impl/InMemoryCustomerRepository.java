package ap2.nombreapellido.repository.impl;

import ap2.nombreapellido.model.Customer;
import ap2.nombreapellido.repository.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

  private final List<Customer> customers = new ArrayList<>(List.of(
      new Customer(1L, "Elena Torres", "1003004001", "elena.torres@demo.com", "3001112201"),
      new Customer(2L, "Mario Castro", "1003004002", "mario.castro@demo.com", "3001112202"),
      new Customer(3L, "Paula Rojas", "1003004003", "paula.rojas@demo.com", "3001112203"),
      new Customer(4L, "Ricardo Vega", "1003004004", "ricardo.vega@demo.com", "3001112204"),
      new Customer(5L, "Natalia Flores", "1003004005", "natalia.flores@demo.com", "3001112205"),
      new Customer(6L, "Sebastián León", "1003004006", "sebastian.leon@demo.com", "3001112206"),
      new Customer(7L, "Lorena Díaz", "1003004007", "lorena.diaz@demo.com", "3001112207"),
      new Customer(8L, "Pablo Herrera", "1003004008", "pablo.herrera@demo.com", "3001112208")
  ));

  @Override
  public List<Customer> findAll() {
    return new ArrayList<>(customers);
  }

  @Override
  public Optional<Customer> findById(Long id) {
    return customers.stream().filter(customer -> customer.getId().equals(id)).findFirst();
  }
}
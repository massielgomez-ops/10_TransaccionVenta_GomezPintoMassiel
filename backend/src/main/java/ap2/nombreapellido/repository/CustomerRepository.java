package ap2.nombreapellido.repository;

import ap2.nombreapellido.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

  List<Customer> findAll();

  Optional<Customer> findById(Long id);
}
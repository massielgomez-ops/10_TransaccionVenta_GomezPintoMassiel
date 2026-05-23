package ap2.nombreapellido.service.impl;

import ap2.nombreapellido.model.Customer;
import ap2.nombreapellido.repository.CustomerRepository;
import ap2.nombreapellido.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public List<Customer> findAll() {
    return customerRepository.findAll();
  }
}
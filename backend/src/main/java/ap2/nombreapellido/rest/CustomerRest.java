package ap2.nombreapellido.rest;

import ap2.nombreapellido.model.Customer;
import ap2.nombreapellido.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/customers")
public class CustomerRest {

  private final CustomerService customerService;

  public CustomerRest(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping
  public ResponseEntity<List<Customer>> findAll() {
    return ResponseEntity.ok(customerService.findAll());
  }
}
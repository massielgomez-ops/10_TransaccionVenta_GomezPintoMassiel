package ap2.nombreapellido.rest;

import ap2.nombreapellido.model.SaleRecord;
import ap2.nombreapellido.model.SaleRequest;
import ap2.nombreapellido.service.SaleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/sales")
public class SaleRest {

  private final SaleService saleService;

  public SaleRest(SaleService saleService) {
    this.saleService = saleService;
  }

  @GetMapping
  public ResponseEntity<List<SaleRecord>> findAll() {
    return ResponseEntity.ok(saleService.findAll());
  }

  @PostMapping
  public ResponseEntity<SaleRecord> save(@Valid @RequestBody SaleRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(saleService.save(request));
  }
}
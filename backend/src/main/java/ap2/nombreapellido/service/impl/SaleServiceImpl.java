package ap2.nombreapellido.service.impl;

import ap2.nombreapellido.model.Customer;
import ap2.nombreapellido.model.Product;
import ap2.nombreapellido.model.SaleItem;
import ap2.nombreapellido.model.SaleRecord;
import ap2.nombreapellido.model.SaleRequest;
import ap2.nombreapellido.repository.CustomerRepository;
import ap2.nombreapellido.repository.ProductRepository;
import ap2.nombreapellido.repository.SaleRepository;
import ap2.nombreapellido.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

  private final SaleRepository saleRepository;
  private final CustomerRepository customerRepository;
  private final ProductRepository productRepository;

  public SaleServiceImpl(SaleRepository saleRepository,
                         CustomerRepository customerRepository,
                         ProductRepository productRepository) {
    this.saleRepository = saleRepository;
    this.customerRepository = customerRepository;
    this.productRepository = productRepository;
  }

  @Override
  public List<SaleRecord> findAll() {
    return saleRepository.findAll();
  }

  @Override
  public SaleRecord save(SaleRequest request) {
    if (request.getClientId() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El cliente es obligatorio");
    }

    Customer customer = customerRepository.findById(request.getClientId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

    if (request.getItems() == null || request.getItems().isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe agregar al menos un producto");
    }

    for (SaleItem item : request.getItems()) {
      if (item.getProductId() == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe seleccionar un producto");
      }

      if (item.getQuantity() == null || item.getQuantity() <= 0) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cantidad debe ser mayor a cero");
      }

      Product product = productRepository.findById(item.getProductId())
          .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));

      if (item.getQuantity() > product.getStock()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock insuficiente para " + product.getName());
      }

      double subtotal = roundMoney(item.getQuantity() * product.getPrice());
      item.setProductName(product.getName());
      item.setUnitPrice(product.getPrice());
      item.setSubtotal(subtotal);
      item.setStock(product.getStock());
      product.setStock(product.getStock() - item.getQuantity());
      productRepository.save(product);
    }

    SaleRecord saleRecord = new SaleRecord();
    saleRecord.setId(System.currentTimeMillis());
    saleRecord.setClientId(customer.getId());
    saleRecord.setClientName(customer.getName());
    saleRecord.setSaleDate(request.getSaleDate());
    saleRecord.setItems(request.getItems());
    saleRecord.setTotal(roundMoney(request.getItems().stream().mapToDouble(SaleItem::getSubtotal).sum()));
    saleRecord.setCreatedAt(LocalDateTime.now().toString());

    return saleRepository.save(saleRecord);
  }

  private double roundMoney(double value) {
    return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }
}
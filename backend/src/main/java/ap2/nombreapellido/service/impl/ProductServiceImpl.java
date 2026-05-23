package ap2.nombreapellido.service.impl;

import ap2.nombreapellido.model.Product;
import ap2.nombreapellido.repository.ProductRepository;
import ap2.nombreapellido.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public List<Product> findAll() {
    return productRepository.findAll();
  }
}
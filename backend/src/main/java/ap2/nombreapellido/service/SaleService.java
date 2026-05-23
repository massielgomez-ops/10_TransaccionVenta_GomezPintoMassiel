package ap2.nombreapellido.service;

import ap2.nombreapellido.model.SaleRecord;
import ap2.nombreapellido.model.SaleRequest;

import java.util.List;

public interface SaleService {

  List<SaleRecord> findAll();

  SaleRecord save(SaleRequest request);
}
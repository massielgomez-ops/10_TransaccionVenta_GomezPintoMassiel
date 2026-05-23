package ap2.nombreapellido.repository;

import ap2.nombreapellido.model.SaleRecord;

import java.util.List;

public interface SaleRepository {

  List<SaleRecord> findAll();

  SaleRecord save(SaleRecord saleRecord);
}
package ap2.nombreapellido.repository.impl;

import ap2.nombreapellido.model.SaleItem;
import ap2.nombreapellido.model.SaleRecord;
import ap2.nombreapellido.repository.SaleRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemorySaleRepository implements SaleRepository {

  private final List<SaleRecord> sales = new ArrayList<>(List.of(
    sale(2001L, 1L, "Elena Torres", "2026-05-23", 2890.0,
      new SaleItem(11L, "Laptop Essential 15", 1, 2890.0, 2890.0, 10)),
    sale(2002L, 2L, "Mario Castro", "2026-05-23", 1650.0,
      new SaleItem(12L, "Tablet Pro 11", 1, 1650.0, 1650.0, 14)),
    sale(2003L, 3L, "Paula Rojas", "2026-05-23", 260.0,
      new SaleItem(13L, "Auriculares ANC", 1, 260.0, 260.0, 28)),
    sale(2004L, 4L, "Ricardo Vega", "2026-05-23", 2500.0,
      new SaleItem(14L, "Monitor Curvo 32\"", 2, 1250.0, 2500.0, 9)),
    sale(2005L, 5L, "Natalia Flores", "2026-05-23", 420.0,
      new SaleItem(16L, "Mouse Gamer", 3, 140.0, 420.0, 25)),
    sale(2006L, 6L, "Sebastián León", "2026-05-23", 920.0,
      new SaleItem(17L, "Impresora Multifuncional", 1, 920.0, 920.0, 7)),
    sale(2007L, 7L, "Lorena Díaz", "2026-05-23", 780.0,
      new SaleItem(18L, "Silla Ergonómica", 1, 780.0, 780.0, 11)),
    sale(2008L, 8L, "Pablo Herrera", "2026-05-23", 580.0,
      new SaleItem(15L, "Teclado Mecánico RGB", 2, 290.0, 580.0, 18))
  ));

  private static SaleRecord sale(Long id,
                               Long clientId,
                               String clientName,
                               String saleDate,
                               Double total,
                               SaleItem item) {
    SaleRecord saleRecord = new SaleRecord();
    saleRecord.setId(id);
    saleRecord.setClientId(clientId);
    saleRecord.setClientName(clientName);
    saleRecord.setSaleDate(saleDate);
    saleRecord.setTotal(total);
    saleRecord.setCreatedAt("2026-05-23T12:00:00");
    saleRecord.setItems(List.of(item));
    return saleRecord;
  }

  @Override
  public List<SaleRecord> findAll() {
    return new ArrayList<>(sales);
  }

  @Override
  public SaleRecord save(SaleRecord saleRecord) {
    sales.add(0, saleRecord);
    return saleRecord;
  }
}
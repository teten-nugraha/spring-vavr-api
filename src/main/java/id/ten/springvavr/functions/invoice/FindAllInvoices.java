package id.ten.springvavr.functions.invoice;

import id.ten.springvavr.domain.Invoice;
import id.ten.springvavr.persistence.repositories.InvoiceRepository;
import io.vavr.Function0;
import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import id.ten.springvavr.persistence.entities.InvoiceEntity;

@Component
@RequiredArgsConstructor
public class FindAllInvoices implements Function0<List<Invoice>> {

  private final InvoiceRepository invoiceRepository;

  @Override
  public List<Invoice> apply() {
    return this.invoiceRepository.findAll()
        .map(InvoiceEntity::toDomain);
  }
}
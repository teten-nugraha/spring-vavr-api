package id.ten.springvavr.functions.invoice;

import id.ten.springvavr.domain.Invoice;
import id.ten.springvavr.persistence.entities.InvoiceEntity;
import id.ten.springvavr.persistence.repositories.InvoiceRepository;
import io.vavr.Function2;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateInvoice implements Function2<String, Invoice, Try<Invoice>> {

  private final InvoiceRepository invoiceRepository;

  @Override
  public Try<Invoice> apply(final String id, final Invoice payload) {
    return this.invoiceRepository.findById(id)
        .toTry(() -> new RuntimeException("Not found"))
        .flatMap(Function2.of(this::updateInvoice).apply(payload));
  }

  private Try<Invoice> updateInvoice(final Invoice invoice, final InvoiceEntity entity) {
    return Try.of(() -> InvoiceEntity.valueOf(invoice))
        .map(this.invoiceRepository::save)
        .map(InvoiceEntity::toDomain);
  }
}

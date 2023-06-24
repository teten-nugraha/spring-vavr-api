package id.ten.springvavr.functions.invoice;

import id.ten.springvavr.domain.Invoice;
import id.ten.springvavr.functions.issuer.GetIssuer;
import id.ten.springvavr.functions.item.GetItemsByInvoiceId;
import id.ten.springvavr.functions.recipient.GetRecipient;
import id.ten.springvavr.persistence.repositories.InvoiceRepository;
import io.vavr.Function1;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import id.ten.springvavr.persistence.entities.InvoiceEntity;

@Component
@RequiredArgsConstructor
public class GetInvoice implements Function1<String, Either<String, Invoice>> {

  private final InvoiceRepository invoiceRepository;
  private final GetIssuer getIssuer;
  private final GetItemsByInvoiceId getItemsByInvoiceId;
  private final GetRecipient getRecipient;

  @Override
  public Either<String, Invoice> apply(final String id) {
    return getInvoice(id)
        .flatMap(this::assembleIssuer)
        .map(this::assembleItems)
        .flatMap(this::assembleRecipient);
  }

  private Either<String, Invoice> assembleIssuer(final Invoice invoice) {
    return getIssuer.apply(invoice.getIssuerCnpj())
        .map(invoice::issuer)
        .toEither(() -> "Issuer not found");
  }

  private Either<String, Invoice> assembleRecipient(final Invoice invoice) {
    return getRecipient.apply(invoice.getRecipientCpf())
        .map(invoice::recipient)
        .toEither(() -> "Recipient not found");
  }

  private Invoice assembleItems(final Invoice invoice) {
    return invoice.items(getItemsByInvoiceId.apply(invoice.id()));
  }

  private Either<String, Invoice> getInvoice(String id) {
    return this.invoiceRepository.findById(id)
        .map(InvoiceEntity::toDomain)
        .toEither(() -> "Invoice not found");
  }
}

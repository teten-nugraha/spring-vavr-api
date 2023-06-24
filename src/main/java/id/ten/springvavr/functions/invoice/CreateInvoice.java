package id.ten.springvavr.functions.invoice;


import id.ten.springvavr.domain.Invoice;
import id.ten.springvavr.domain.Issuer;
import id.ten.springvavr.domain.Item;
import id.ten.springvavr.domain.Recipient;
import id.ten.springvavr.functions.issuer.CreateIssuer;
import id.ten.springvavr.functions.item.CreateItems;
import id.ten.springvavr.functions.recipient.CreateRecipient;
import id.ten.springvavr.persistence.entities.InvoiceEntity;
import id.ten.springvavr.persistence.repositories.InvoiceRepository;
import io.vavr.Function1;
import io.vavr.collection.List;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateInvoice implements Function1<Invoice, Try<Invoice>> {

  private final CreateIssuer createIssuer;
  private final CreateItems createItems;
  private final CreateRecipient createRecipient;
  private final InvoiceRepository invoiceRepository;

  @Override
  public Try<Invoice> apply(final Invoice body) {
    return createInvoice(body)
        .flatMap(createIssuer(body.issuer()))
        .flatMap(createItems(body.items()))
        .flatMap(createRecipient(body.recipient()));
  }

  private Function1<Invoice, Try<Invoice>> createIssuer(final Issuer body) {
    return invoice -> createIssuer.apply(body)
        .map(invoice::issuer);
  }

  private Function1<Invoice, Try<Invoice>> createItems(final List<Item> body) {
    return invoice -> Try.of(() -> createItems.apply(invoice.id(), body))
        .map(invoice::items);
  }

  private Function1<Invoice, Try<Invoice>> createRecipient(final Recipient body) {
    return invoice -> createRecipient.apply(body)
        .map(invoice::recipient);
  }

  private Try<Invoice> createInvoice(final Invoice invoice) {
    return Try.of(() -> InvoiceEntity.create(invoice))
        .map(this.invoiceRepository::save)
        .map(InvoiceEntity::toDomain);
  }
}
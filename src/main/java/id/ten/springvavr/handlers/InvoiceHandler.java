package id.ten.springvavr.handlers;


import id.ten.springvavr.domain.Invoice;
import id.ten.springvavr.errors.ApiError;
import id.ten.springvavr.functions.invoice.CreateInvoice;
import id.ten.springvavr.functions.invoice.DeleteInvoice;
import id.ten.springvavr.functions.invoice.FindAllInvoices;
import id.ten.springvavr.functions.invoice.GetInvoice;
import id.ten.springvavr.functions.invoice.UpdateInvoice;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;
import static org.springframework.web.servlet.function.ServerResponse.ok;
import static org.springframework.web.servlet.function.ServerResponse.status;

@Component
@RequiredArgsConstructor
public class InvoiceHandler {

  private final CreateInvoice createInvoice;
  private final DeleteInvoice deleteInvoice;
  private final FindAllInvoices findAllInvoices;
  private final GetInvoice getInvoice;
  private final UpdateInvoice updateInvoice;

  public ServerResponse create(final ServerRequest request) {
    return Try.of(() -> request.body(Invoice.class))
        .flatMap(this.createInvoice)
        .fold(ApiError.fromThrowable.andThen(status(500)::body), ok()::body);
  }

  public final ServerResponse findAll(final ServerRequest request) {
    return this.findAllInvoices
        .andThen(ok()::body)
        .get();
  }

  public ServerResponse get(final ServerRequest request) {
    return this.getInvoice.apply(request.pathVariable("id"))
        .fold(ApiError.fromString.andThen(status(404)::body), ok()::body);
  }

  public final ServerResponse update(final ServerRequest request) {
    return Try.of(() -> request.body(Invoice.class))
        .flatMap(payload -> this.updateInvoice.apply(request.pathVariable("id"), payload))
        .fold(ApiError.fromThrowable.andThen(status(500)::body), ok()::body);
  }

  public final ServerResponse delete(final ServerRequest request) {
    return this.deleteInvoice.apply(request.pathVariable("id"))
        .fold(ApiError.fromThrowable.andThen(status(500)::body), ok()::body);
  }
}

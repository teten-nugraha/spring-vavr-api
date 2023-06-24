package id.ten.springvavr.persistence.entities;

import id.ten.springvavr.domain.Invoice;
import id.ten.springvavr.domain.Issuer;
import id.ten.springvavr.domain.Recipient;
import io.vavr.collection.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@Entity
@NoArgsConstructor
@Table(name = "invoices")
public class InvoiceEntity {

  @Id
  private String id;
  private Integer number;
  private Integer serie;
  private String issuer;
  private String recipient;

  public Invoice toDomain() {
    return new Invoice(
        getId(),
        getNumber(),
        getSerie(),
        Issuer.cnpj(getIssuer()),
        Recipient.cpf(getRecipient()),
        List.empty()
    );
  }

  public static InvoiceEntity create(final Invoice invoice) {
    return valueOf(invoice).toBuilder()
        .id(UUID.randomUUID().toString())
        .issuer(invoice.issuer().cnpj())
        .recipient(invoice.recipient().cpf())
        .build();
  }

  public static InvoiceEntity valueOf(final Invoice invoice) {
    return InvoiceEntity.builder()
        .number(invoice.number())
        .serie(invoice.serie())
        .issuer(invoice.issuer().cnpj())
        .recipient(invoice.recipient().cpf())
        .build();
  }
}

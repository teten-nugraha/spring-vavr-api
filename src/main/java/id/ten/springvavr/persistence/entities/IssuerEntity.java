package id.ten.springvavr.persistence.entities;

import id.ten.springvavr.domain.Issuer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@Entity
@NoArgsConstructor
@Table(name = "issuers")
public class IssuerEntity {

  @Id
  private String cnpj;
  private String corporateName;

  public static IssuerEntity valueOf(final Issuer issuer) {
    return IssuerEntity.builder()
        .cnpj(issuer.cnpj())
        .corporateName(issuer.corporateName())
        .build();
  }

  public Issuer toDomain() {
    return new Issuer(getCnpj(), getCorporateName());
  }
}

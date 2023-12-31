package id.ten.springvavr.functions.issuer;

import id.ten.springvavr.domain.Issuer;
import id.ten.springvavr.persistence.entities.IssuerEntity;
import id.ten.springvavr.persistence.repositories.IssuerRepository;
import io.vavr.Function1;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateIssuer implements Function1<Issuer, Try<Issuer>> {

  private final IssuerRepository issuerRepository;

  @Override
  public Try<Issuer> apply(final Issuer issuer) {
    return Try.of(() -> IssuerEntity.valueOf(issuer))
        .map(this.issuerRepository::save)
        .map(IssuerEntity::toDomain);
  }
}

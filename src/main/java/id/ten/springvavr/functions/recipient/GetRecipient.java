package id.ten.springvavr.functions.recipient;

import id.ten.springvavr.domain.Recipient;
import id.ten.springvavr.persistence.repositories.RecipientRepository;
import io.vavr.Function1;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import id.ten.springvavr.persistence.entities.RecipientEntity;

@Component
@RequiredArgsConstructor
public class GetRecipient implements Function1<String, Option<Recipient>> {

  private final RecipientRepository recipientRepository;

  @Override
  public Option<Recipient> apply(final String cnpj) {
    return this.recipientRepository.findById(cnpj)
        .map(RecipientEntity::toDomain);
  }
}

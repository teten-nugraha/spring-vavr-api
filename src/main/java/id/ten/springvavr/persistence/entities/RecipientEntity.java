package id.ten.springvavr.persistence.entities;

import id.ten.springvavr.domain.Recipient;
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
@Table(name = "recipients")
public class RecipientEntity {

  @Id
  private String cpf;
  private String name;

  public static RecipientEntity valueOf(final Recipient recipient) {
    return RecipientEntity.builder()
        .cpf(recipient.cpf())
        .name(recipient.name())
        .build();
  }

  public Recipient toDomain() {
    return new Recipient(getCpf(), getName());
  }
}

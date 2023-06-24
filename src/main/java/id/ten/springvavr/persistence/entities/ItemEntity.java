package id.ten.springvavr.persistence.entities;

import id.ten.springvavr.domain.Item;
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
@Table(name = "items")
public class ItemEntity {

  @Id
  private String id;
  private String name;
  private String itemId;
  private String invoiceId;

  public static ItemEntity create(final String invoiceId, final Item item) {
    return ItemEntity.builder()
        .id(UUID.randomUUID().toString())
        .itemId(item.id())
        .invoiceId(invoiceId)
        .name(item.name())
        .build();
  }

  public Item toDomain() {
    return new Item(getItemId(), getName());
  }
}

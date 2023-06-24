package id.ten.springvavr.functions.item;

import id.ten.springvavr.domain.Item;
import id.ten.springvavr.persistence.repositories.ItemRepository;
import io.vavr.Function1;
import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import id.ten.springvavr.persistence.entities.ItemEntity;

@Component
@RequiredArgsConstructor
public class GetItemsByInvoiceId implements Function1<String, List<Item>> {

  private final ItemRepository itemRepository;

  @Override
  public List<Item> apply(final String invoiceId) {
    return this.itemRepository.findAllByInvoiceId(invoiceId)
        .map(ItemEntity::toDomain);
  }
}
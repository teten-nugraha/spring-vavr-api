package id.ten.springvavr.persistence.repositories;

import id.ten.springvavr.persistence.entities.ItemEntity;
import io.vavr.collection.List;
import io.vavr.control.Option;
import org.springframework.data.repository.Repository;

public interface ItemRepository extends Repository<ItemEntity, String> {

  ItemEntity save(ItemEntity entity);

  List<ItemEntity> findAll();

  Option<ItemEntity> findById(final String id);

  void deleteById(String id);

  List<ItemEntity> findAllByInvoiceId(final String invoiceId);
}

package id.ten.springvavr.persistence.repositories;

import id.ten.springvavr.persistence.entities.InvoiceEntity;
import io.vavr.collection.List;
import io.vavr.control.Option;
import org.springframework.data.repository.Repository;

public interface InvoiceRepository extends Repository<InvoiceEntity, String> {

  InvoiceEntity save(InvoiceEntity entity);

  List<InvoiceEntity> findAll();

  Option<InvoiceEntity> findById(final String id);

  void deleteById(String id);
}

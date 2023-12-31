package id.ten.springvavr.persistence.repositories;

import id.ten.springvavr.persistence.entities.RecipientEntity;
import io.vavr.collection.List;
import io.vavr.control.Option;
import org.springframework.data.repository.Repository;

public interface RecipientRepository extends Repository<RecipientEntity, String> {

  RecipientEntity save(RecipientEntity entity);

  List<RecipientEntity> findAll();

  Option<RecipientEntity> findById(final String id);

  void deleteById(String id);
}

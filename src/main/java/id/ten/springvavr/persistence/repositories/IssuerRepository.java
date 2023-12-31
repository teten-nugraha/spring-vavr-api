package id.ten.springvavr.persistence.repositories;

import id.ten.springvavr.persistence.entities.IssuerEntity;
import io.vavr.collection.List;
import io.vavr.control.Option;
import org.springframework.data.repository.Repository;

public interface IssuerRepository extends Repository<IssuerEntity, String> {

  IssuerEntity save(IssuerEntity entity);

  List<IssuerEntity> findAll();

  Option<IssuerEntity> findById(final String id);

  void deleteById(String id);
}

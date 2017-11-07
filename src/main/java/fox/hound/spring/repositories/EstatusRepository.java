package fox.hound.spring.repositories;

import org.springframework.data.repository.CrudRepository;

import fox.hound.spring.models.Estatus;

//https://docs.spring.io/spring-data/jpa/docs/1.6.0.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods
public interface EstatusRepository extends CrudRepository<Estatus, Long> {

}

package fox.hound.spring.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fox.hound.spring.models.Trabajador;

//https://docs.spring.io/spring-data/jpa/docs/1.6.0.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods
public interface TrabajadorRepository extends CrudRepository<Trabajador, Long> {

	List<Trabajador> findByCargoId(Long cargo);
	
}

package fox.hound.spring.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fox.hound.spring.models.combo.Municipio;

//https://docs.spring.io/spring-data/jpa/docs/1.6.0.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods
public interface MunicipioRepository extends CrudRepository<Municipio, Long> {

	List<Municipio> findByCiudadId(Long valueOf);
}

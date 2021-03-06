package fox.hound.spring.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fox.hound.spring.models.OrdenServicio;
import fox.hound.spring.models.puente.DetalleOrdenServicio;

//https://docs.spring.io/spring-data/jpa/docs/1.6.0.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods
public interface DetalleOrdenServicioRepository extends CrudRepository<DetalleOrdenServicio, Long> {
	List<OrdenServicio> findByTrabajadorId(Long valueOf);
}

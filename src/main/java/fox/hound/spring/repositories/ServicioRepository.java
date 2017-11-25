package fox.hound.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fox.hound.spring.models.Servicio;

//https://docs.spring.io/spring-data/jpa/docs/1.6.0.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods
public interface ServicioRepository extends CrudRepository<Servicio, Long> {
	
	List<Servicio> findByEstatus(int estatus);

	List<Servicio> findByTipoServicioId(Long tipoServicio);
	 
	@Query("SELECT s FROM Servicio s, TipoServicio t WHERE s.tipoServicio.id = t.id AND t.categoria.id = :id")
	List<Servicio> findByCategoriaId(@Param("id") Long categoria);
	
	@Query("SELECT s FROM Servicio s JOIN s.detalleServicioInmuebles dsi, TipoCaracteristicaInmueble tci "
			+ "WHERE dsi.tipoCaracteristicaInmueble.id = tci.id AND tci.tipoInmueble.id = :id")
	List<Servicio> findByTipoInmuebleId(@Param("id") Long tioInmueble);
}

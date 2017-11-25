package fox.hound.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fox.hound.spring.models.puente.Promocion;
import fox.hound.spring.models.puente.PromocionServicio;

//https://docs.spring.io/spring-data/jpa/docs/1.6.0.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods
public interface PromocionServicioRepository extends CrudRepository<PromocionServicio, Long> {
	//SELECT * FROM PROMOCION P, SERVICIO S, PROMOCION_SERVICIO PS WHERE PS.SERVICIO_ID = S.ID AND PS.ESTATUS = 0 AND PS.PROMOCION_ID = 1
	@Query("SELECT ps FROM PromocionServicio ps, Promocion p, Servicio s WHERE ps.servicio.id = s.id AND ps.estatus = 0 AND ps.promocion.id = :id")
	List<Promocion> findByServicioId(@Param("id") Long servicio);
	//List<Promocion> findByServicioId(Long valueOf);
}

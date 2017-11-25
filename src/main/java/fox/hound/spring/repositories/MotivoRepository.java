package fox.hound.spring.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import fox.hound.spring.models.combo.Motivo;

//https://docs.spring.io/spring-data/jpa/docs/1.6.0.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods
public interface MotivoRepository extends CrudRepository<Motivo, Long> {
	List<Motivo> findByTipoMotivoId(Long valueOf);
}

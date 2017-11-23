package fox.hound.spring.repositories;

import org.springframework.data.repository.CrudRepository;

import fox.hound.spring.models.DiagnosticoVisita;

//https://docs.spring.io/spring-data/jpa/docs/1.6.0.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods
public interface DiagnosticoVisitaRepository extends CrudRepository<DiagnosticoVisita, Long> {

}

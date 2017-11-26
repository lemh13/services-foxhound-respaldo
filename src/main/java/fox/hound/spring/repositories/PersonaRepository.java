package fox.hound.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import fox.hound.spring.models.Persona;

//https://docs.spring.io/spring-data/jpa/docs/1.6.0.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods
public interface PersonaRepository extends CrudRepository<Persona, Long> {

	Persona findByEmailAndPassword(String email, String password);
		
	@Query("select p from Persona p where type = ?1")
	List<Persona> findByType(String typeValue);

}
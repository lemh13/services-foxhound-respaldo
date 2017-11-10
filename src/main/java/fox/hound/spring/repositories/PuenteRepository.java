//package fox.hound.spring.repositories;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//
//import fox.hound.spring.models.puente.Puente;
//
////https://docs.spring.io/spring-data/jpa/docs/1.6.0.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods
//public interface PuenteRepository extends CrudRepository<Puente, Long> {
//
//	@Query("select p from Puente p where type = ?1")
//	List<Puente> findByType(String typeValue);
//}

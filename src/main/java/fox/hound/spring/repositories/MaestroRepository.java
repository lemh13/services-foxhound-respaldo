//package fox.hound.spring.repositories;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//
//import fox.hound.spring.models.maestros.Maestro;
//
////https://docs.spring.io/spring-data/jpa/docs/1.6.0.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods
//public interface MaestroRepository extends CrudRepository<Maestro, Long> {
//
//	@Query("select m from Maestro m where type = ?1")
//	List<Maestro> findByType(String typeValue);
//}

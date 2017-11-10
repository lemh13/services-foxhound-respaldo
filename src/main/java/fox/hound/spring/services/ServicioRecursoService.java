package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.ServicioRecurso;
import fox.hound.spring.repositories.ServicioRecursoRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class ServicioRecursoService implements ServiceGeneral<ServicioRecurso> {

	 @Autowired
	 private ServicioRecursoRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<ServicioRecurso> getAll() {
		 List<ServicioRecurso> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public ServicioRecurso getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public ServicioRecurso saveOrUpdate(ServicioRecurso clase) {
		 if (clase.getId() != null) {
			 ServicioRecurso claseAux = getOne( clase.getId() );
			 clase.setFecha_creacion( claseAux.getFecha_creacion() );
		 }
		 clase.setFecha_modificacion( DateUtil.getCurrentDate() );
		 clase.setEstatus( estatusService.getOne(clase.getEstatus().getId() ) );
		 return repository.save(clase);
	 }

	 @Override
	 public void delete(Long id) {
		 repository.delete(id);
	 }
}

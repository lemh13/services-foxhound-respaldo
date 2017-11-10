package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.combo.Recurso;
import fox.hound.spring.repositories.RecursoRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class RecursoService implements ServiceGeneral<Recurso> {

	 @Autowired
	 private RecursoRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<Recurso> getAll() {
		 List<Recurso> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Recurso getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Recurso saveOrUpdate(Recurso clase) {
		 if (clase.getId() != null) {
			 Recurso claseAux = getOne( clase.getId() );
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

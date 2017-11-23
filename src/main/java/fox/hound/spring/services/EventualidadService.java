package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.combo.Eventualidad;
import fox.hound.spring.repositories.EventualidadRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class EventualidadService implements ServiceGeneral<Eventualidad> {

	 @Autowired
	 private EventualidadRepository repository;

	 

	 @Override
	 public List<Eventualidad> getAll() {
		 List<Eventualidad> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Eventualidad getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Eventualidad saveOrUpdate(Eventualidad clase) {
		 if (clase.getId() != null) {
			 Eventualidad claseAux = getOne( clase.getId() );
			 clase.setFecha_creacion( claseAux.getFecha_creacion() );
		 }
		 clase.setFecha_modificacion( DateUtil.getCurrentDate() );
		 
		 return repository.save(clase);
	 }

	 @Override
	 public void delete(Long id) {
		 repository.delete(id);
	 }
	 
	 @Override
		public void deleteLogic(String id) {
		 	Eventualidad clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}
}

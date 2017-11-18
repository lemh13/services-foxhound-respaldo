package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.Tarea;
import fox.hound.spring.repositories.TareaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class TareaService implements ServiceGeneral<Tarea> {

	 @Autowired
	 private TareaRepository repository;

	 

	 @Override
	 public List<Tarea> getAll() {
		 List<Tarea> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Tarea getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Tarea saveOrUpdate(Tarea clase) {
		 if (clase.getId() != null) {
			 Tarea claseAux = getOne( clase.getId() );
			 clase.setFecha_creacion( claseAux.getFecha_creacion() );
		 }
		 clase.setFecha_modificacion( DateUtil.getCurrentDate() );
		 
		 return repository.save(clase);
	 }

	 @Override
	 public void delete(Long id) {
		 repository.delete(id);
	 }
}

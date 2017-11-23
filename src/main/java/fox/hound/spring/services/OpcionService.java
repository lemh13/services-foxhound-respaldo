package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.Opcion;
import fox.hound.spring.repositories.OpcionRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class OpcionService implements ServiceGeneral<Opcion> {

	 @Autowired
	 private OpcionRepository repository;

	 

	 @Override
	 public List<Opcion> getAll() {
		 List<Opcion> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Opcion getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Opcion saveOrUpdate(Opcion clase) {
		 if (clase.getId() != null) {
			 Opcion claseAux = getOne( clase.getId() );
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
		 	Opcion clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}
}

package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.Condicion;
import fox.hound.spring.repositories.CondicionRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class CondicionService implements ServiceGeneral<Condicion> {

	 @Autowired
	 private CondicionRepository repository;

	 

	 @Override
	 public List<Condicion> getAll() {
		 List<Condicion> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Condicion getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Condicion saveOrUpdate(Condicion clase) {
		 if (clase.getId() != null) {
			 Condicion claseAux = getOne( clase.getId() );
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
		 	Condicion clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}
}

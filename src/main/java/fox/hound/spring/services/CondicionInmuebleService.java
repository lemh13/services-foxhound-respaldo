package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.combo.CondicionInmueble;
import fox.hound.spring.repositories.CondicionInmuebleRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class CondicionInmuebleService implements ServiceGeneral<CondicionInmueble> {

	 @Autowired
	 private CondicionInmuebleRepository repository;

	 

	 @Override
	 public List<CondicionInmueble> getAll() {
		 List<CondicionInmueble> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public CondicionInmueble getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public CondicionInmueble saveOrUpdate(CondicionInmueble clase) {
		 if (clase.getId() != null) {
			 CondicionInmueble claseAux = getOne( clase.getId() );
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

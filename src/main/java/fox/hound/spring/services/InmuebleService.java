package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.Inmueble;
import fox.hound.spring.repositories.InmuebleRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class InmuebleService implements ServiceGeneral<Inmueble> {

	 @Autowired
	 private InmuebleRepository repository;

	 

	 @Override
	 public List<Inmueble> getAll() {
		 List<Inmueble> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Inmueble getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Inmueble saveOrUpdate(Inmueble clase) {
		 if (clase.getId() != null) {
			 Inmueble claseAux = getOne( clase.getId() );
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

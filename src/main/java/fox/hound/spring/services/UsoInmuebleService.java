package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.UsoInmueble;
import fox.hound.spring.repositories.UsoInmuebleRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class UsoInmuebleService implements ServiceGeneral<UsoInmueble> {

	 @Autowired
	 private UsoInmuebleRepository repository;

	 @Override
	 public List<UsoInmueble> getAll() {
		 List<UsoInmueble> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public UsoInmueble getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public UsoInmueble saveOrUpdate(UsoInmueble clase) {
		 if (clase.getId() != null) {
			 UsoInmueble claseAux = getOne( clase.getId() );
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
		 	UsoInmueble clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}
}

package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.CategoriaInmueble;
import fox.hound.spring.repositories.CategoriaInmuebleRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class CategoriaInmuebleService implements ServiceGeneral<CategoriaInmueble> {

	 @Autowired
	 private CategoriaInmuebleRepository repository;

	 

	 @Override
	 public List<CategoriaInmueble> getAll() {
		 List<CategoriaInmueble> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public CategoriaInmueble getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public CategoriaInmueble saveOrUpdate(CategoriaInmueble clase) {
		 if (clase.getId() != null) {
			 CategoriaInmueble claseAux = getOne( clase.getId() );
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
		 	CategoriaInmueble clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}
}

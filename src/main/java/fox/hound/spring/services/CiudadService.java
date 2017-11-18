package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.combo.Ciudad;
import fox.hound.spring.repositories.CiudadRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class CiudadService implements ServiceGeneral<Ciudad> {

	 @Autowired
	 private CiudadRepository repository;

	 public List<Ciudad> getCiudadPorEstado(String id) {
		 return repository.findByEstadoId(Long.valueOf(id));
	 }

	 @Override
	 public List<Ciudad> getAll() {
		 List<Ciudad> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Ciudad getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Ciudad saveOrUpdate(Ciudad clase) {
		 if (clase.getId() != null) {
			 Ciudad claseAux = getOne( clase.getId() );
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

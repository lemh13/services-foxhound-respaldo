package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fox.hound.spring.models.Respuesta;
import fox.hound.spring.repositories.RespuestaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class RespuestaService implements ServiceGeneral<Respuesta> {

	 @Autowired
	 private RespuestaRepository repository;

	 

	 @Override
	 public List<Respuesta> getAll() {
		 List<Respuesta> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Respuesta getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Respuesta saveOrUpdate(Respuesta clase) {
		 if (clase.getId() != null) {
			 Respuesta claseAux = getOne( clase.getId() );
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
		 	Respuesta clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}
}

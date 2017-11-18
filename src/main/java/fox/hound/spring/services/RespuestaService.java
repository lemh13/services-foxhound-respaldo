package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.combo.Respuesta;
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
}

package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.Pregunta;
import fox.hound.spring.repositories.PreguntaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class PreguntaService implements ServiceGeneral<Pregunta> {

	 @Autowired
	 private PreguntaRepository repository;

	 

	 @Override
	 public List<Pregunta> getAll() {
		 List<Pregunta> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Pregunta getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Pregunta saveOrUpdate(Pregunta clase) {
		 if (clase.getId() != null) {
			 Pregunta claseAux = getOne( clase.getId() );
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

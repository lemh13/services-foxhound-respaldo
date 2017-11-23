package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.combo.OpcionPregunta;
import fox.hound.spring.repositories.OpcionPreguntaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class OpcionPreguntaService implements ServiceGeneral<OpcionPregunta> {

	 @Autowired
	 private OpcionPreguntaRepository repository;

	 

	 @Override
	 public List<OpcionPregunta> getAll() {
		 List<OpcionPregunta> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public OpcionPregunta getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public OpcionPregunta saveOrUpdate(OpcionPregunta clase) {
		 if (clase.getId() != null) {
			 OpcionPregunta claseAux = getOne( clase.getId() );
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
		 	OpcionPregunta clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}
}

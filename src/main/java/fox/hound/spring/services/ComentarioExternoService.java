package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.combo.ComentarioExterno;
import fox.hound.spring.repositories.ComentarioExternoRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class ComentarioExternoService implements ServiceGeneral<ComentarioExterno> {

	 @Autowired
	 private ComentarioExternoRepository repository;

	 

	 @Override
	 public List<ComentarioExterno> getAll() {
		 List<ComentarioExterno> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public ComentarioExterno getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public ComentarioExterno saveOrUpdate(ComentarioExterno clase) {
		 if (clase.getId() != null) {
			 ComentarioExterno claseAux = getOne( clase.getId() );
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

package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.Profesion;
import fox.hound.spring.repositories.ProfesionRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class ProfesionService implements ServiceGeneral<Profesion> {

	 @Autowired
	 private ProfesionRepository repository;

	 

	 @Override
	 public List<Profesion> getAll() {
		 List<Profesion> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Profesion getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Profesion saveOrUpdate(Profesion clase) {
		 if (clase.getId() != null) {
			 Profesion claseAux = getOne( clase.getId() );
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

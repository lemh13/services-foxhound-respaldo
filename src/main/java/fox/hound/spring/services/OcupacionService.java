package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.Ocupacion;
import fox.hound.spring.repositories.OcupacionRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class OcupacionService implements ServiceGeneral<Ocupacion> {

	 @Autowired
	 private OcupacionRepository repository;

	 

	 @Override
	 public List<Ocupacion> getAll() {
		 List<Ocupacion> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Ocupacion getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Ocupacion saveOrUpdate(Ocupacion clase) {
		 if (clase.getId() != null) {
			 Ocupacion claseAux = getOne( clase.getId() );
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

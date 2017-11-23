package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.OrdenServicioEventualidad;
import fox.hound.spring.repositories.OrdenServicioEventualidadRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class OrdenServicioEventualidadService implements ServiceGeneral<OrdenServicioEventualidad> {

	 @Autowired
	 private OrdenServicioEventualidadRepository repository;

	 

	 @Override
	 public List<OrdenServicioEventualidad> getAll() {
		 List<OrdenServicioEventualidad> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public OrdenServicioEventualidad getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public OrdenServicioEventualidad saveOrUpdate(OrdenServicioEventualidad clase) {
		 if (clase.getId() != null) {
			 OrdenServicioEventualidad claseAux = getOne( clase.getId() );
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
		 	OrdenServicioEventualidad clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}
}

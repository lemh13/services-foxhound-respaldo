package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.DetalleOrdenServicio;
import fox.hound.spring.repositories.DetalleOrdenServicioRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class DetalleOrdenServicioService implements ServiceGeneral<DetalleOrdenServicio> {

	 @Autowired
	 private DetalleOrdenServicioRepository repository;

	 

	 @Override
	 public List<DetalleOrdenServicio> getAll() {
		 List<DetalleOrdenServicio> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public DetalleOrdenServicio getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public DetalleOrdenServicio saveOrUpdate(DetalleOrdenServicio clase) {
		 if (clase.getId() != null) {
			 DetalleOrdenServicio claseAux = getOne( clase.getId() );
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

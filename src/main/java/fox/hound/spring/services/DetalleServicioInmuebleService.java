package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.DetalleServicioInmueble;
import fox.hound.spring.repositories.DetalleServicioInmuebleRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class DetalleServicioInmuebleService implements ServiceGeneral<DetalleServicioInmueble> {

	 @Autowired
	 private DetalleServicioInmuebleRepository repository;

	 @Override
	 public List<DetalleServicioInmueble> getAll() {
		 List<DetalleServicioInmueble> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public DetalleServicioInmueble getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public DetalleServicioInmueble saveOrUpdate(DetalleServicioInmueble clase) {
		 if (clase.getId() != null) {
			 DetalleServicioInmueble claseAux = getOne( clase.getId() );
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

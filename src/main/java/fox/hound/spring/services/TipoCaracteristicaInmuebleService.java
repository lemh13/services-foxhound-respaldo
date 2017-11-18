package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.TipoCaracteristicaInmueble;
import fox.hound.spring.repositories.TipoCaracteristicaInmuebleRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class TipoCaracteristicaInmuebleService implements ServiceGeneral<TipoCaracteristicaInmueble> {

	 @Autowired
	 private TipoCaracteristicaInmuebleRepository repository;

	 

	 @Override
	 public List<TipoCaracteristicaInmueble> getAll() {
		 List<TipoCaracteristicaInmueble> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public TipoCaracteristicaInmueble getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public TipoCaracteristicaInmueble saveOrUpdate(TipoCaracteristicaInmueble clase) {
		 if (clase.getId() != null) {
			 TipoCaracteristicaInmueble claseAux = getOne( clase.getId() );
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

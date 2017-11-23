package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.TipoCaracteristicaServicio;
import fox.hound.spring.repositories.TipoCaracteristicaServicioRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class TipoCaracteristicaServicioService implements ServiceGeneral<TipoCaracteristicaServicio> {

	 @Autowired
	 private TipoCaracteristicaServicioRepository repository;

	 

	 @Override
	 public List<TipoCaracteristicaServicio> getAll() {
		 List<TipoCaracteristicaServicio> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public TipoCaracteristicaServicio getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public TipoCaracteristicaServicio saveOrUpdate(TipoCaracteristicaServicio clase) {
		 if (clase.getId() != null) {
			 TipoCaracteristicaServicio claseAux = getOne( clase.getId() );
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
		 	TipoCaracteristicaServicio clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}
}

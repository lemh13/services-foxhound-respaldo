package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.TipoOrdenServicio;
import fox.hound.spring.repositories.TipoOrdenServicioRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class TipoOrdenServicioService implements ServiceGeneral<TipoOrdenServicio> {

	 @Autowired
	 private TipoOrdenServicioRepository repository;

	 @Override
	 public List<TipoOrdenServicio> getAll() {
		 List<TipoOrdenServicio> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public TipoOrdenServicio getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public TipoOrdenServicio saveOrUpdate(TipoOrdenServicio clase) {
		 if (clase.getId() != null) {
			 TipoOrdenServicio claseAux = getOne( clase.getId() );
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
		 	TipoOrdenServicio clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}
}

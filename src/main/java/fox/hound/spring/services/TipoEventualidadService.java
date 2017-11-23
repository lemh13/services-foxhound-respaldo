package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.TipoEventualidad;
import fox.hound.spring.repositories.TipoEventualidadRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class TipoEventualidadService implements ServiceGeneral<TipoEventualidad> {

	 @Autowired
	 private TipoEventualidadRepository repository;

	 @Override
	 public List<TipoEventualidad> getAll() {
		 List<TipoEventualidad> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public TipoEventualidad getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public TipoEventualidad saveOrUpdate(TipoEventualidad clase) {
		 if (clase.getId() != null) {
			 TipoEventualidad claseAux = getOne( clase.getId() );
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
		 	TipoEventualidad clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}
}

package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.CalificarServicio;
import fox.hound.spring.repositories.CalificarServicioRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class CalificarServicioService implements ServiceGeneral<CalificarServicio> {

	 @Autowired
	 private CalificarServicioRepository repository;

	 

	 @Override
	 public List<CalificarServicio> getAll() {
		 List<CalificarServicio> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public CalificarServicio getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public CalificarServicio saveOrUpdate(CalificarServicio clase) {
		 if (clase.getId() != null) {
			 CalificarServicio claseAux = getOne( clase.getId() );
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
		 	 CalificarServicio clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public CalificarServicio activeDesactiveEstatus(String id) {
		CalificarServicio clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

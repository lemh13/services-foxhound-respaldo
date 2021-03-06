package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.OrdenServicio;
import fox.hound.spring.repositories.OrdenServicioRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class OrdenServicioService implements ServiceGeneral<OrdenServicio> {

	 @Autowired
	 private OrdenServicioRepository repository;

	 @Override
	 public List<OrdenServicio> getAll() {
		 List<OrdenServicio> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public OrdenServicio getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public OrdenServicio saveOrUpdate(OrdenServicio clase) {
		 if (clase.getId() != null) {
			 OrdenServicio claseAux = getOne( clase.getId() );
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
		 	OrdenServicio clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public OrdenServicio activeDesactiveEstatus(String id) {
		OrdenServicio clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

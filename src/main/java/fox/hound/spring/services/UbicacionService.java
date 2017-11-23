package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.Ubicacion;
import fox.hound.spring.repositories.UbicacionRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class UbicacionService implements ServiceGeneral<Ubicacion> {

	 @Autowired
	 private UbicacionRepository repository;

	 @Override
	 public List<Ubicacion> getAll() {
		 List<Ubicacion> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Ubicacion getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Ubicacion saveOrUpdate(Ubicacion clase) {
		 if (clase.getId() != null) {
			 Ubicacion claseAux = getOne( clase.getId() );
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
		 	Ubicacion clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public Ubicacion activeDesactiveEstatus(String id) {
		Ubicacion clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

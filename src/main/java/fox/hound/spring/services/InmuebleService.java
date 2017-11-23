package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.Inmueble;
import fox.hound.spring.repositories.InmuebleRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class InmuebleService implements ServiceGeneral<Inmueble> {

	 @Autowired
	 private InmuebleRepository repository;
	 
	 public List<Inmueble> getAllByClienteId(Long id) {
		 List<Inmueble> lista = new ArrayList<>();
		 repository.findByClienteId(id).forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public List<Inmueble> getAll() {
		 List<Inmueble> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Inmueble getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Inmueble saveOrUpdate(Inmueble clase) {
		 if (clase.getId() != null) {
			 Inmueble claseAux = getOne( clase.getId() );
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
		 	Inmueble clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public Inmueble activeDesactiveEstatus(String id) {
		Inmueble clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

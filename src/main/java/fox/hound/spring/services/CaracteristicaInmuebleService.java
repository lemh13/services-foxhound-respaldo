package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.CaracteristicaInmueble;
import fox.hound.spring.repositories.CaracteristicaInmuebleRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class CaracteristicaInmuebleService implements ServiceGeneral<CaracteristicaInmueble> {

	 @Autowired
	 private CaracteristicaInmuebleRepository repository;

	 

	 @Override
	 public List<CaracteristicaInmueble> getAll() {
		 List<CaracteristicaInmueble> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public CaracteristicaInmueble getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public CaracteristicaInmueble saveOrUpdate(CaracteristicaInmueble clase) {
		 if (clase.getId() != null) {
			 CaracteristicaInmueble claseAux = getOne( clase.getId() );
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
		 	CaracteristicaInmueble clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public CaracteristicaInmueble activeDesactiveEstatus(String id) {
		CaracteristicaInmueble clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

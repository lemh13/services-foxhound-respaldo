package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.CargoTipoServicio;
import fox.hound.spring.repositories.CargoTipoServicioRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class CargoTipoServicioService implements ServiceGeneral<CargoTipoServicio> {

	 @Autowired
	 private CargoTipoServicioRepository repository;

	 

	 @Override
	 public List<CargoTipoServicio> getAll() {
		 List<CargoTipoServicio> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public CargoTipoServicio getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public CargoTipoServicio saveOrUpdate(CargoTipoServicio clase) {
		 if (clase.getId() != null) {
			 CargoTipoServicio claseAux = getOne( clase.getId() );
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
		 	CargoTipoServicio clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}
}

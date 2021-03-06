package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.Cargo;
import fox.hound.spring.repositories.CargoRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class CargoService implements ServiceGeneral<Cargo> {

	 @Autowired
	 private CargoRepository repository;

	 

	 @Override
	 public List<Cargo> getAll() {
		 List<Cargo> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Cargo getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Cargo saveOrUpdate(Cargo clase) {
		 if (clase.getId() != null) {
			 Cargo claseAux = getOne( clase.getId() );
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
		 	Cargo clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public Cargo activeDesactiveEstatus(String id) {
		Cargo clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

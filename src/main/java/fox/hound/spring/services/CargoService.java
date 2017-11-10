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

	 @Autowired
	 private EstatusService estatusService;

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
		 clase.setEstatus( estatusService.getOne(clase.getEstatus().getId() ) );
		 return repository.save(clase);
	 }

	 @Override
	 public void delete(Long id) {
		 repository.delete(id);
	 }
}

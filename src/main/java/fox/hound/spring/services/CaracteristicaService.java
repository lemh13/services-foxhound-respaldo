package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.combo.Caracteristica;
import fox.hound.spring.repositories.CaracteristicaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class CaracteristicaService implements ServiceGeneral<Caracteristica> {

	 @Autowired
	 private CaracteristicaRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<Caracteristica> getAll() {
		 List<Caracteristica> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Caracteristica getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Caracteristica saveOrUpdate(Caracteristica clase) {
		 if (clase.getId() != null) {
			 Caracteristica claseAux = getOne( clase.getId() );
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

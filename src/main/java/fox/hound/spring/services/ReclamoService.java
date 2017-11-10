package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.combo.Reclamo;
import fox.hound.spring.repositories.ReclamoRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class ReclamoService implements ServiceGeneral<Reclamo> {

	 @Autowired
	 private ReclamoRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<Reclamo> getAll() {
		 List<Reclamo> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Reclamo getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Reclamo saveOrUpdate(Reclamo clase) {
		 if (clase.getId() != null) {
			 Reclamo claseAux = getOne( clase.getId() );
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

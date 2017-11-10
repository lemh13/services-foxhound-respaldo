package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.combo.Parroquia;
import fox.hound.spring.repositories.ParroquiaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class ParroquiaService implements ServiceGeneral<Parroquia> {

	 @Autowired
	 private ParroquiaRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<Parroquia> getAll() {
		 List<Parroquia> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Parroquia getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Parroquia saveOrUpdate(Parroquia clase) {
		 if (clase.getId() != null) {
			 Parroquia claseAux = getOne( clase.getId() );
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

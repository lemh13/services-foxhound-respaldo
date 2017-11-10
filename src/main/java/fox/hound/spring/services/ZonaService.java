package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.combo.Zona;
import fox.hound.spring.repositories.ZonaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class ZonaService implements ServiceGeneral<Zona> {

	 @Autowired
	 private ZonaRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<Zona> getAll() {
		 List<Zona> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Zona getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Zona saveOrUpdate(Zona clase) {
		 if (clase.getId() != null) {
			 Zona claseAux = getOne( clase.getId() );
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

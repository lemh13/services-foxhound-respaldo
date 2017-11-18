package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.SolicitudServicio;
import fox.hound.spring.repositories.SolicitudServicioRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class SolicitudServicioService implements ServiceGeneral<SolicitudServicio> {

	 @Autowired
	 private SolicitudServicioRepository repository;

	 

	 @Override
	 public List<SolicitudServicio> getAll() {
		 List<SolicitudServicio> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public SolicitudServicio getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public SolicitudServicio saveOrUpdate(SolicitudServicio clase) {
		 if (clase.getId() != null) {
			 SolicitudServicio claseAux = getOne( clase.getId() );
			 clase.setFecha_creacion( claseAux.getFecha_creacion() );
		 }
		 clase.setFecha_modificacion( DateUtil.getCurrentDate() );
		 
		 return repository.save(clase);
	 }

	 @Override
	 public void delete(Long id) {
		 repository.delete(id);
	 }
}

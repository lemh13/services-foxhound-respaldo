package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.Solicitud;
import fox.hound.spring.repositories.SolicitudRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class SolicitudService implements ServiceGeneral<Solicitud> {

	 @Autowired
	 private SolicitudRepository repository;

	 @Override
	 public List<Solicitud> getAll() {
		 List<Solicitud> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Solicitud getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Solicitud saveOrUpdate(Solicitud clase) {
		 if (clase.getId() != null) {
			 Solicitud claseAux = getOne( clase.getId() );
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
		Solicitud clase = getOne(Long.valueOf(id));
		clase.setEstatus(2);
		repository.save(clase);
	}
	
}

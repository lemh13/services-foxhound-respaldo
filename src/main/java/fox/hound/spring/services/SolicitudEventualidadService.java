package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.SolicitudEventualidad;
import fox.hound.spring.repositories.SolicitudEventualidadRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class SolicitudEventualidadService implements ServiceGeneral<SolicitudEventualidad> {

	 @Autowired
	 private SolicitudEventualidadRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<SolicitudEventualidad> getAll() {
		 List<SolicitudEventualidad> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public SolicitudEventualidad getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public SolicitudEventualidad saveOrUpdate(SolicitudEventualidad clase) {
		 if (clase.getId() != null) {
			 SolicitudEventualidad claseAux = getOne( clase.getId() );
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

package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.ReclamoSolicitudServicio;
import fox.hound.spring.repositories.ReclamoSolicitudServicioRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class ReclamoSolicitudServicioService implements ServiceGeneral<ReclamoSolicitudServicio> {

	 @Autowired
	 private ReclamoSolicitudServicioRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<ReclamoSolicitudServicio> getAll() {
		 List<ReclamoSolicitudServicio> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public ReclamoSolicitudServicio getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public ReclamoSolicitudServicio saveOrUpdate(ReclamoSolicitudServicio clase) {
		 if (clase.getId() != null) {
			 ReclamoSolicitudServicio claseAux = getOne( clase.getId() );
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

package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.SolicitudServicioMotivo;
import fox.hound.spring.repositories.SolicitudServicioMotivoRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class SolicitudServicioMotivoService implements ServiceGeneral<SolicitudServicioMotivo> {

	 @Autowired
	 private SolicitudServicioMotivoRepository repository;

	 

	 @Override
	 public List<SolicitudServicioMotivo> getAll() {
		 List<SolicitudServicioMotivo> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public SolicitudServicioMotivo getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public SolicitudServicioMotivo saveOrUpdate(SolicitudServicioMotivo clase) {
		 if (clase.getId() != null) {
			 SolicitudServicioMotivo claseAux = getOne( clase.getId() );
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
		 	SolicitudServicioMotivo clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}
}

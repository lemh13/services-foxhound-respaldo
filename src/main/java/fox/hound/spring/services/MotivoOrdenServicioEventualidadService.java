package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.MotivoOrdenServicioEventualidad;
import fox.hound.spring.repositories.MotivoOrdenServicioEventualidadRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class MotivoOrdenServicioEventualidadService implements ServiceGeneral<MotivoOrdenServicioEventualidad> {

	 @Autowired
	 private MotivoOrdenServicioEventualidadRepository repository;

	 

	 @Override
	 public List<MotivoOrdenServicioEventualidad> getAll() {
		 List<MotivoOrdenServicioEventualidad> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public MotivoOrdenServicioEventualidad getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public MotivoOrdenServicioEventualidad saveOrUpdate(MotivoOrdenServicioEventualidad clase) {
		 if (clase.getId() != null) {
			 MotivoOrdenServicioEventualidad claseAux = getOne( clase.getId() );
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

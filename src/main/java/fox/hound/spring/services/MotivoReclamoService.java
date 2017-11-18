package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.MotivoReclamo;
import fox.hound.spring.repositories.MotivoReclamoRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class MotivoReclamoService implements ServiceGeneral<MotivoReclamo> {

	 @Autowired
	 private MotivoReclamoRepository repository;

	 

	 @Override
	 public List<MotivoReclamo> getAll() {
		 List<MotivoReclamo> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public MotivoReclamo getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public MotivoReclamo saveOrUpdate(MotivoReclamo clase) {
		 if (clase.getId() != null) {
			 MotivoReclamo claseAux = getOne( clase.getId() );
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

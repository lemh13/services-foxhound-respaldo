package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.combo.Motivo;
import fox.hound.spring.repositories.MotivoRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class MotivoService implements ServiceGeneral<Motivo> {

	 @Autowired
	 private MotivoRepository repository;

	 

	 @Override
	 public List<Motivo> getAll() {
		 List<Motivo> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Motivo getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Motivo saveOrUpdate(Motivo clase) {
		 if (clase.getId() != null) {
			 Motivo claseAux = getOne( clase.getId() );
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

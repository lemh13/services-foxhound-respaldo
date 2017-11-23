package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.Estado;
import fox.hound.spring.repositories.EstadoRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class EstadoService implements ServiceGeneral<Estado> {

	 @Autowired
	 private EstadoRepository repository;

	 

	 @Override
	 public List<Estado> getAll() {
		 List<Estado> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Estado getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Estado saveOrUpdate(Estado clase) {
		 if (clase.getId() != null) {
			 Estado claseAux = getOne( clase.getId() );
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
		 	Estado clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}
}

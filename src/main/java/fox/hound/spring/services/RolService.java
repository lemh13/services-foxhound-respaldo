package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.combo.Rol;
import fox.hound.spring.repositories.RolRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class RolService implements ServiceGeneral<Rol> {

	 @Autowired
	 private RolRepository repository;

	 

	 @Override
	 public List<Rol> getAll() {
		 List<Rol> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Rol getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Rol saveOrUpdate(Rol clase) {
		 if (clase.getId() != null) {
			 Rol claseAux = getOne( clase.getId() );
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
		 	Rol clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}
}

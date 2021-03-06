package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.RolFuncion;
import fox.hound.spring.repositories.RolFuncionRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class RolFuncionService implements ServiceGeneral<RolFuncion> {

	 @Autowired
	 private RolFuncionRepository repository;

	 

	 @Override
	 public List<RolFuncion> getAll() {
		 List<RolFuncion> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public RolFuncion getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public RolFuncion saveOrUpdate(RolFuncion clase) {
		 if (clase.getId() != null) {
			 RolFuncion claseAux = getOne( clase.getId() );
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
		 	RolFuncion clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public RolFuncion activeDesactiveEstatus(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}

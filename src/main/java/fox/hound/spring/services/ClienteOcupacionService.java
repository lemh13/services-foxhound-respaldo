package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.ClienteOcupacion;
import fox.hound.spring.repositories.ClienteOcupacionRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class ClienteOcupacionService implements ServiceGeneral<ClienteOcupacion> {

	 @Autowired
	 private ClienteOcupacionRepository repository;

	 

	 @Override
	 public List<ClienteOcupacion> getAll() {
		 List<ClienteOcupacion> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public ClienteOcupacion getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public ClienteOcupacion saveOrUpdate(ClienteOcupacion clase) {
		 if (clase.getId() != null) {
			 ClienteOcupacion claseAux = getOne( clase.getId() );
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
		 	ClienteOcupacion clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public ClienteOcupacion activeDesactiveEstatus(String id) {
		ClienteOcupacion clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

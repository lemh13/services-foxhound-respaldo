package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.Cliente;
import fox.hound.spring.repositories.ClienteRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class ClienteService implements ServiceGeneral<Cliente> {

	 @Autowired
	 private ClienteRepository repository;

	 

	 @Override
	 public List<Cliente> getAll() {
		 List<Cliente> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Cliente getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Cliente saveOrUpdate(Cliente clase) {
		 if (clase.getId() != null) {
			 Cliente claseAux = getOne( clase.getId() );
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

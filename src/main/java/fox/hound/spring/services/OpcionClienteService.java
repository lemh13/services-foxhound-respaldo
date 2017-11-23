package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.OpcionCliente;
import fox.hound.spring.repositories.OpcionClienteRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class OpcionClienteService implements ServiceGeneral<OpcionCliente> {

	 @Autowired
	 private OpcionClienteRepository repository;

	 

	 @Override
	 public List<OpcionCliente> getAll() {
		 List<OpcionCliente> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public OpcionCliente getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public OpcionCliente saveOrUpdate(OpcionCliente clase) {
		 if (clase.getId() != null) {
			 OpcionCliente claseAux = getOne( clase.getId() );
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
		 	OpcionCliente clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}
}

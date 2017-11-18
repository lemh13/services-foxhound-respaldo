package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.PreferenciaCliente;
import fox.hound.spring.repositories.PreferenciaClienteRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class PreferenciaClienteService implements ServiceGeneral<PreferenciaCliente> {

	 @Autowired
	 private PreferenciaClienteRepository repository;

	 

	 @Override
	 public List<PreferenciaCliente> getAll() {
		 List<PreferenciaCliente> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public PreferenciaCliente getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public PreferenciaCliente saveOrUpdate(PreferenciaCliente clase) {
		 if (clase.getId() != null) {
			 PreferenciaCliente claseAux = getOne( clase.getId() );
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

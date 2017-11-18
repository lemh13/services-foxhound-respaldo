package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.ClienteProfesion;
import fox.hound.spring.repositories.ClienteProfesionRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class ClienteProfesionService implements ServiceGeneral<ClienteProfesion> {

	 @Autowired
	 private ClienteProfesionRepository repository;

	 

	 @Override
	 public List<ClienteProfesion> getAll() {
		 List<ClienteProfesion> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public ClienteProfesion getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public ClienteProfesion saveOrUpdate(ClienteProfesion clase) {
		 if (clase.getId() != null) {
			 ClienteProfesion claseAux = getOne( clase.getId() );
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

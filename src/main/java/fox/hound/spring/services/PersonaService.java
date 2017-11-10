package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.Persona;
import fox.hound.spring.repositories.PersonaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class PersonaService implements ServiceGeneral<Persona> {

	 @Autowired
	 private PersonaRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<Persona> getAll() {
		 List<Persona> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Persona getOne(Long id) {
		 return repository.findOne(id);
	 }
	 
	 public Persona getByEmailAndPassword(String email, String password) {
		 return repository.findByEmailAndPassword(email, password);
	 }

	 @Override
	 public Persona saveOrUpdate(Persona clase) {
		 if (clase.getId() != null) {
			 Persona claseAux = getOne( clase.getId() );
			 clase.setFecha_creacion( claseAux.getFecha_creacion() );
		 }
		 clase.setFecha_modificacion( DateUtil.getCurrentDate() );
		 clase.setEstatus( estatusService.getOne(clase.getEstatus().getId() ) );
		 return repository.save(clase);
	 }

	 @Override
	 public void delete(Long id) {
		 repository.delete(id);
	 }
}

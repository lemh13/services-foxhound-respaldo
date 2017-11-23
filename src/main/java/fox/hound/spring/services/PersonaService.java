package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.Persona;
import fox.hound.spring.repositories.PersonaRepository;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.EncryptionUtil;

@Service
public class PersonaService implements ServiceGeneral<Persona> {

	 @Autowired
	 private PersonaRepository repository;
	 
	 @Autowired
	 private EncryptionUtil encript;
	 
	 public List<Persona> getAll(String type) {
		 List<Persona> lista = new ArrayList<>();
		 repository.findByType(type).forEach(lista::add);
		 return lista;
	 }

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
			 
			 if (!claseAux.getPassword().equals( encript.md5( clase.getPassword() ) )) {
				 clase.setPassword( encript.md5( clase.getPassword() ) );
			 }
		 } else {
			 clase.setPassword( encript.md5( clase.getPassword() ) );
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
		 	Persona clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}
}

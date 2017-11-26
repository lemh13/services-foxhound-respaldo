package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fox.hound.spring.models.Persona;
import fox.hound.spring.models.Trabajador;
import fox.hound.spring.repositories.TrabajadorRepository;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.EncryptionUtil;

@Service
public class TrabajadorService implements ServiceGeneral<Trabajador> {

	 @Autowired
	 private TrabajadorRepository repository;
	 
	 @Autowired
	 private EncryptionUtil encript;

	 @Override
	 public List<Trabajador> getAll() {
		 List<Trabajador> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }
	 
	 public List<Trabajador> getTrabajadorPorCargo(String id) { 
		 return repository.findByCargoId(Long.valueOf(id));
	 }
	
	 @Override
	 public Trabajador getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Trabajador saveOrUpdate(Trabajador clase) {
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
		 	Trabajador clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public Trabajador activeDesactiveEstatus(String id) {
		Trabajador clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

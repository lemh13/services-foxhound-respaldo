package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fox.hound.spring.models.Persona;
import fox.hound.spring.models.Usuario;
import fox.hound.spring.repositories.UsuarioRepository;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.EncryptionUtil;

@Service
public class UsuarioService implements ServiceGeneral<Usuario> {

	 @Autowired
	 private UsuarioRepository repository;

	 @Autowired
	 private EncryptionUtil encript;
	 
	 
	 @Override
	 public List<Usuario> getAll() {
		 List<Usuario> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Usuario getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Usuario saveOrUpdate(Usuario clase) {
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
		 	Usuario clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public Usuario activeDesactiveEstatus(String id) {
		Usuario clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

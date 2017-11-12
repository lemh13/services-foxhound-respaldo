package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.TipoPersona;
import fox.hound.spring.repositories.TipoPersonaRepository;

@Service
public class TipoPersonaService implements ServiceGeneral<TipoPersona> {

	 @Autowired
	 private TipoPersonaRepository repository;

	 @Override
	 public List<TipoPersona> getAll() {
		 List<TipoPersona> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public TipoPersona getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public TipoPersona saveOrUpdate(TipoPersona clase) {
		 return repository.save(clase);
	 }

	 @Override
	 public void delete(Long id) {
		 repository.delete(id);
	 }
}

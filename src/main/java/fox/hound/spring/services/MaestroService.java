package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fox.hound.spring.models.Maestro;
import fox.hound.spring.repositories.MaestroRepository;

@Service
public class MaestroService implements ServiceGeneral<Maestro> {

	@Autowired
	private MaestroRepository repository;
	
	@Override
	public List<Maestro> getAll() {
		List<Maestro> lista = new ArrayList<>();
		repository.findAll().forEach(lista::add);
		return lista;
	}

	@Override
	public Maestro getOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Maestro saveOrUpdate(Maestro clase) {
		return repository.save(clase);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

}

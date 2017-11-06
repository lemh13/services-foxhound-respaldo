package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fox.hound.spring.models.Maestro;
import fox.hound.spring.repositories.MaestroRepository;

@Service
public class MaestroService {

	@Autowired
	private MaestroRepository repository;
	
	public List<Maestro> getAll(String type) {
		List<Maestro> lista = new ArrayList<>();
		repository.findByType(type).forEach(lista::add);
		return lista;
	}

	public Maestro getOne(Long id) {
		return repository.findOne(id);
	}

	public Maestro saveOrUpdate(Maestro clase) {
		return repository.save(clase);
	}

	public void delete(Long id) {
		repository.delete(id);
	}

}

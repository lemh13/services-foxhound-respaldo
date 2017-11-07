package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fox.hound.spring.models.maestros.Maestro;
import fox.hound.spring.repositories.MaestroRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class MaestroService {

	@Autowired
	private MaestroRepository repository;
	
	@Autowired
	private EstatusService estatusService;
	
	public List<Maestro> getAll(String type) {
		List<Maestro> lista = new ArrayList<>();
		repository.findByType(type).forEach(lista::add);
		return lista;
	}

	public Maestro getOne(Long id) {
		return repository.findOne(id);
	}

	public Maestro saveOrUpdate(Maestro clase) {
		if (clase.getId() != null) {
			Maestro claseAux = getOne( clase.getId() );

			clase.setFecha_creacion( claseAux.getFecha_creacion() );
		}
		clase.setFecha_modificacion( DateUtil.getCurrentDate() );
		
		clase.setEstatus( estatusService.getOne(clase.getEstatus().getId() ) );
		return repository.save(clase);
	}

	public void delete(Long id) {
		repository.delete(id);
	}

}

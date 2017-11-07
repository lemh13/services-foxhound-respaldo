package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fox.hound.spring.models.Estatus;
import fox.hound.spring.repositories.EstatusRepository;

@Service
public class EstatusService implements ServiceGeneral<Estatus> {
	
	@Autowired
	private EstatusRepository estatusService;

	@Override
	public List<Estatus> getAll() {
		List<Estatus> lista = new ArrayList<>();
		estatusService.findAll().forEach(lista::add);
		return lista;
	}

	@Override
	public Estatus getOne(Long id) {
		return estatusService.findOne(id);
	}

	@Override
	public Estatus saveOrUpdate(Estatus clase) {
		return estatusService.save(clase);
	}

	@Override
	public void delete(Long id) {
		estatusService.delete(id);
	}

}

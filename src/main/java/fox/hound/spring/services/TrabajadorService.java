package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.Trabajador;
import fox.hound.spring.repositories.TrabajadorRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class TrabajadorService implements ServiceGeneral<Trabajador> {

	 @Autowired
	 private TrabajadorRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<Trabajador> getAll() {
		 List<Trabajador> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Trabajador getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Trabajador saveOrUpdate(Trabajador clase) {
		 if (clase.getId() != null) {
			 Trabajador claseAux = getOne( clase.getId() );
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

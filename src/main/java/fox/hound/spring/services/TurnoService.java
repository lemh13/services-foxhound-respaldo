package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.Turno;
import fox.hound.spring.repositories.TurnoRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class TurnoService implements ServiceGeneral<Turno> {

	 @Autowired
	 private TurnoRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<Turno> getAll() {
		 List<Turno> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Turno getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Turno saveOrUpdate(Turno clase) {
		 if (clase.getId() != null) {
			 Turno claseAux = getOne( clase.getId() );
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

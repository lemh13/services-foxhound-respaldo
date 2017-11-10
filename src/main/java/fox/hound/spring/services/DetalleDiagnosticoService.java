package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.DetalleDiagnostico;
import fox.hound.spring.repositories.DetalleDiagnosticoRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class DetalleDiagnosticoService implements ServiceGeneral<DetalleDiagnostico> {

	 @Autowired
	 private DetalleDiagnosticoRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<DetalleDiagnostico> getAll() {
		 List<DetalleDiagnostico> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public DetalleDiagnostico getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public DetalleDiagnostico saveOrUpdate(DetalleDiagnostico clase) {
		 if (clase.getId() != null) {
			 DetalleDiagnostico claseAux = getOne( clase.getId() );
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

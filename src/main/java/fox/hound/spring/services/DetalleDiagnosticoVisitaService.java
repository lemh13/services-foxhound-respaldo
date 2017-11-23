package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.*;
import fox.hound.spring.repositories.*;
import fox.hound.spring.utils.DateUtil;

@Service
public class DetalleDiagnosticoVisitaService implements ServiceGeneral<DetalleDiagnosticoVisita> {

	 @Autowired
	 private DetalleDiagnosticoVisitaRepository repository;

	 @Override
	 public List<DetalleDiagnosticoVisita> getAll() {
		 List<DetalleDiagnosticoVisita> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public DetalleDiagnosticoVisita getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public DetalleDiagnosticoVisita saveOrUpdate(DetalleDiagnosticoVisita clase) {
		 if (clase.getId() != null) {
			 DetalleDiagnosticoVisita claseAux = getOne( clase.getId() );
			 clase.setFecha_creacion( claseAux.getFecha_creacion() );
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
		 	DetalleDiagnosticoVisita clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}
}

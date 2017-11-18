package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.CondicionDiagnostico;
import fox.hound.spring.repositories.DetalleDiagnosticoRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class DetalleDiagnosticoService implements ServiceGeneral<CondicionDiagnostico> {

	 @Autowired
	 private DetalleDiagnosticoRepository repository;

	 

	 @Override
	 public List<CondicionDiagnostico> getAll() {
		 List<CondicionDiagnostico> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public CondicionDiagnostico getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public CondicionDiagnostico saveOrUpdate(CondicionDiagnostico clase) {
		 if (clase.getId() != null) {
			 CondicionDiagnostico claseAux = getOne( clase.getId() );
			 clase.setFecha_creacion( claseAux.getFecha_creacion() );
		 }
		 clase.setFecha_modificacion( DateUtil.getCurrentDate() );
		 
		 return repository.save(clase);
	 }

	 @Override
	 public void delete(Long id) {
		 repository.delete(id);
	 }
}

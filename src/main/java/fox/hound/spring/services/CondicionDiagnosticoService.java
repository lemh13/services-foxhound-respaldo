package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.CondicionDiagnostico;
import fox.hound.spring.repositories.CondicionDiagnosticoRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class CondicionDiagnosticoService implements ServiceGeneral<CondicionDiagnostico> {

	 @Autowired
	 private CondicionDiagnosticoRepository repository;

	 

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
	 
	 @Override
		public void deleteLogic(String id) {
		 	CondicionDiagnostico clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public CondicionDiagnostico activeDesactiveEstatus(String id) {
		CondicionDiagnostico clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

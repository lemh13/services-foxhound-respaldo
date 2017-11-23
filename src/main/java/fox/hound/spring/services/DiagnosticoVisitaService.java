package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fox.hound.spring.models.DiagnosticoVisita;
import fox.hound.spring.repositories.DiagnosticoVisitaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class DiagnosticoVisitaService implements ServiceGeneral<DiagnosticoVisita> {

	 @Autowired
	 private DiagnosticoVisitaRepository repository;

	 

	 @Override
	 public List<DiagnosticoVisita> getAll() {
		 List<DiagnosticoVisita> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public DiagnosticoVisita getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public DiagnosticoVisita saveOrUpdate(DiagnosticoVisita clase) {
		 if (clase.getId() != null) {
			 DiagnosticoVisita claseAux = getOne( clase.getId() );
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
		 	DiagnosticoVisita clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public DiagnosticoVisita activeDesactiveEstatus(String id) {
		DiagnosticoVisita clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

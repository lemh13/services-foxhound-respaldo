package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.TipoDiagnosticoVisita;
import fox.hound.spring.repositories.TipoDiagnosticoVisitaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class TipoDiagnosticoVisitaService implements ServiceGeneral<TipoDiagnosticoVisita> {

	 @Autowired
	 private TipoDiagnosticoVisitaRepository repository;

	 

	 @Override
	 public List<TipoDiagnosticoVisita> getAll() {
		 List<TipoDiagnosticoVisita> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public TipoDiagnosticoVisita getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public TipoDiagnosticoVisita saveOrUpdate(TipoDiagnosticoVisita clase) {
		 if (clase.getId() != null) {
			 TipoDiagnosticoVisita claseAux = getOne( clase.getId() );
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

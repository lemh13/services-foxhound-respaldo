package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.Visita;
import fox.hound.spring.repositories.VisitaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class VisitaService implements ServiceGeneral<Visita> {

	 @Autowired
	 private VisitaRepository repository;

	 @Override
	 public List<Visita> getAll() {
		 List<Visita> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Visita getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Visita saveOrUpdate(Visita clase) {
		 if (clase.getId() != null) {
			 Visita claseAux = getOne( clase.getId() );
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

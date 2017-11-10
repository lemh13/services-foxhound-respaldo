package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.TipoRecurso;
import fox.hound.spring.repositories.TipoRecursoRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class TipoRecursoService implements ServiceGeneral<TipoRecurso> {

	 @Autowired
	 private TipoRecursoRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<TipoRecurso> getAll() {
		 List<TipoRecurso> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public TipoRecurso getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public TipoRecurso saveOrUpdate(TipoRecurso clase) {
		 if (clase.getId() != null) {
			 TipoRecurso claseAux = getOne( clase.getId() );
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

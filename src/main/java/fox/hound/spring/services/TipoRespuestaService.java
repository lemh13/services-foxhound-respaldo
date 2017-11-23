package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.TipoRespuesta;
import fox.hound.spring.repositories.TipoRespuestaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class TipoRespuestaService implements ServiceGeneral<TipoRespuesta> {

	 @Autowired
	 private TipoRespuestaRepository repository;

	 @Override
	 public List<TipoRespuesta> getAll() {
		 List<TipoRespuesta> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public TipoRespuesta getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public TipoRespuesta saveOrUpdate(TipoRespuesta clase) {
		 if (clase.getId() != null) {
			 TipoRespuesta claseAux = getOne( clase.getId() );
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
		 	TipoRespuesta clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public TipoRespuesta activeDesactiveEstatus(String id) {
		TipoRespuesta clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

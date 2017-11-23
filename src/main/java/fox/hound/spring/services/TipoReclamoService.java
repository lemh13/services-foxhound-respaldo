package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.TipoReclamo;
import fox.hound.spring.repositories.TipoReclamoRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class TipoReclamoService implements ServiceGeneral<TipoReclamo> {

	 @Autowired
	 private TipoReclamoRepository repository;

	 @Override
	 public List<TipoReclamo> getAll() {
		 List<TipoReclamo> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public TipoReclamo getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public TipoReclamo saveOrUpdate(TipoReclamo clase) {
		 if (clase.getId() != null) {
			 TipoReclamo claseAux = getOne( clase.getId() );
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
		 	TipoReclamo clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public TipoReclamo activeDesactiveEstatus(String id) {
		TipoReclamo clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

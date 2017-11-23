package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fox.hound.spring.models.maestros.TipoInmueble;
import fox.hound.spring.repositories.TipoInmuebleRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class TipoInmuebleService implements ServiceGeneral<TipoInmueble> {

	 @Autowired
	 private TipoInmuebleRepository repository;
	 	
	 public List<TipoInmueble> getTipoInmueblePorCategoria(String id) { 
		 return repository.findByCategoriaInmuebleId(Long.valueOf(id));
	 }	
	 
	 @Override
	 public List<TipoInmueble> getAll() {
		 List<TipoInmueble> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public TipoInmueble getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public TipoInmueble saveOrUpdate(TipoInmueble clase) {
		 if (clase.getId() != null) {
			 TipoInmueble claseAux = getOne( clase.getId() );
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
		 	TipoInmueble clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public TipoInmueble activeDesactiveEstatus(String id) {
		TipoInmueble clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

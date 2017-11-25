package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.Servicio;
import fox.hound.spring.repositories.ServicioRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class ServicioService implements ServiceGeneral<Servicio> {

	 @Autowired
	 private ServicioRepository repository;

	 @Override
	 public List<Servicio> getAll() {
		 List<Servicio> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Servicio getOne(Long id) {
		 return repository.findOne(id);
	 }
	 
	 public  List<Servicio> getAllActive() {
		 List<Servicio> lista = new ArrayList<>();
		 repository.findByEstatus(0).forEach(lista::add);
		 return lista;
	 }
	 
	 public List<Servicio> getAllUltimas() {
		 List<Servicio> lista = new ArrayList<>();
		 repository.findByEstatus(0).forEach(lista::add);
		 return lista.subList(Math.max(lista.size() - 6, 0), lista.size());
	 }

	 @Override
	 public Servicio saveOrUpdate(Servicio clase) {
		 if (clase.getId() != null) {
			 Servicio claseAux = getOne( clase.getId() );
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
		 	Servicio clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public Servicio activeDesactiveEstatus(String id) {
		Servicio clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
	
	public List<Servicio> getAllTipoServicio(Long id) {
		 return repository.findByTipoServicioId(id);
	}
	
	public List<Servicio> getAllCategoria(Long id) {
		 return repository.findByCategoriaId(id);
	}
	
	public List<Servicio> getAllTipoInmueble(Long id) {
		 return repository.findByTipoInmuebleId(id);
	}
	
}

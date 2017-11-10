package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.EstadoServicio;
import fox.hound.spring.repositories.EstadoServicioRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class EstadoServicioService implements ServiceGeneral<EstadoServicio> {

	 @Autowired
	 private EstadoServicioRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<EstadoServicio> getAll() {
		 List<EstadoServicio> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public EstadoServicio getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public EstadoServicio saveOrUpdate(EstadoServicio clase) {
		 if (clase.getId() != null) {
			 EstadoServicio claseAux = getOne( clase.getId() );
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

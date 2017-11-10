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

	 @Autowired
	 private EstatusService estatusService;

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

	 @Override
	 public Servicio saveOrUpdate(Servicio clase) {
		 if (clase.getId() != null) {
			 Servicio claseAux = getOne( clase.getId() );
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

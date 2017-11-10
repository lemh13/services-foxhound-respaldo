package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.TipoServicio;
import fox.hound.spring.repositories.TipoServicioRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class TipoServicioService implements ServiceGeneral<TipoServicio> {

	 @Autowired
	 private TipoServicioRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<TipoServicio> getAll() {
		 List<TipoServicio> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public TipoServicio getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public TipoServicio saveOrUpdate(TipoServicio clase) {
		 if (clase.getId() != null) {
			 TipoServicio claseAux = getOne( clase.getId() );
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

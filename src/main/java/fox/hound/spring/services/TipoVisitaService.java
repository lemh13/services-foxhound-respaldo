package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.TipoVisita;
import fox.hound.spring.repositories.TipoVisitaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class TipoVisitaService implements ServiceGeneral<TipoVisita> {

	 @Autowired
	 private TipoVisitaRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<TipoVisita> getAll() {
		 List<TipoVisita> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public TipoVisita getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public TipoVisita saveOrUpdate(TipoVisita clase) {
		 if (clase.getId() != null) {
			 TipoVisita claseAux = getOne( clase.getId() );
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

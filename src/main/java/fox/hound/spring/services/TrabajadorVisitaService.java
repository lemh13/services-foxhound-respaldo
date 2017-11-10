package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.TrabajadorVisita;
import fox.hound.spring.repositories.TrabajadorVisitaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class TrabajadorVisitaService implements ServiceGeneral<TrabajadorVisita> {

	 @Autowired
	 private TrabajadorVisitaRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<TrabajadorVisita> getAll() {
		 List<TrabajadorVisita> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public TrabajadorVisita getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public TrabajadorVisita saveOrUpdate(TrabajadorVisita clase) {
		 if (clase.getId() != null) {
			 TrabajadorVisita claseAux = getOne( clase.getId() );
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

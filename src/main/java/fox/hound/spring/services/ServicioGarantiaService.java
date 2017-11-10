package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.ServicioGarantia;
import fox.hound.spring.repositories.ServicioGarantiaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class ServicioGarantiaService implements ServiceGeneral<ServicioGarantia> {

	 @Autowired
	 private ServicioGarantiaRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<ServicioGarantia> getAll() {
		 List<ServicioGarantia> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public ServicioGarantia getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public ServicioGarantia saveOrUpdate(ServicioGarantia clase) {
		 if (clase.getId() != null) {
			 ServicioGarantia claseAux = getOne( clase.getId() );
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

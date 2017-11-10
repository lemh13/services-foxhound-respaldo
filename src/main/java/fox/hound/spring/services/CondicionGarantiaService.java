package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.CondicionGarantia;
import fox.hound.spring.repositories.CondicionGarantiaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class CondicionGarantiaService implements ServiceGeneral<CondicionGarantia> {

	 @Autowired
	 private CondicionGarantiaRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<CondicionGarantia> getAll() {
		 List<CondicionGarantia> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public CondicionGarantia getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public CondicionGarantia saveOrUpdate(CondicionGarantia clase) {
		 if (clase.getId() != null) {
			 CondicionGarantia claseAux = getOne( clase.getId() );
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

package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.TipoGarantia;
import fox.hound.spring.repositories.TipoGarantiaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class TipoGarantiaService implements ServiceGeneral<TipoGarantia> {

	 @Autowired
	 private TipoGarantiaRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<TipoGarantia> getAll() {
		 List<TipoGarantia> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public TipoGarantia getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public TipoGarantia saveOrUpdate(TipoGarantia clase) {
		 if (clase.getId() != null) {
			 TipoGarantia claseAux = getOne( clase.getId() );
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

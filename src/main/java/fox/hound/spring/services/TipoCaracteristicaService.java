package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.TipoCaracteristica;
import fox.hound.spring.repositories.TipoCaracteristicaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class TipoCaracteristicaService implements ServiceGeneral<TipoCaracteristica> {

	 @Autowired
	 private TipoCaracteristicaRepository repository;

	 

	 @Override
	 public List<TipoCaracteristica> getAll() {
		 List<TipoCaracteristica> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public TipoCaracteristica getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public TipoCaracteristica saveOrUpdate(TipoCaracteristica clase) {
		 if (clase.getId() != null) {
			 TipoCaracteristica claseAux = getOne( clase.getId() );
			 clase.setFecha_creacion( claseAux.getFecha_creacion() );
		 }
		 clase.setFecha_modificacion( DateUtil.getCurrentDate() );
		 
		 return repository.save(clase);
	 }

	 @Override
	 public void delete(Long id) {
		 repository.delete(id);
	 }
}

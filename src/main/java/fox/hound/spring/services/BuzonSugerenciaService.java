package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fox.hound.spring.models.combo.BuzonSugerencia;
import fox.hound.spring.repositories.BuzonSugerenciaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class BuzonSugerenciaService implements ServiceGeneral<BuzonSugerencia> {

	 @Autowired
	 private BuzonSugerenciaRepository repository;

	 

	 @Override
	 public List<BuzonSugerencia> getAll() {
		 List<BuzonSugerencia> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public BuzonSugerencia getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public BuzonSugerencia saveOrUpdate(BuzonSugerencia clase) {
		 if (clase.getId() != null) {
			 BuzonSugerencia claseAux = getOne( clase.getId() );
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
	 	BuzonSugerencia clase = getOne(Long.valueOf(id));
		clase.setEstatus(2);
		repository.save(clase);
	}
}

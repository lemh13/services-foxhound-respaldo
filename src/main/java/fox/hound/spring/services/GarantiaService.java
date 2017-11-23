package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fox.hound.spring.models.Garantia;
import fox.hound.spring.repositories.GarantiaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class GarantiaService implements ServiceGeneral<Garantia> {

	 @Autowired
	 private GarantiaRepository repository;

	 

	 @Override
	 public List<Garantia> getAll() {
		 List<Garantia> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Garantia getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Garantia saveOrUpdate(Garantia clase) {
		 if (clase.getId() != null) {
			 Garantia claseAux = getOne( clase.getId() );
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

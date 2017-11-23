package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fox.hound.spring.models.combo.Municipio;
import fox.hound.spring.models.combo.Parroquia;
import fox.hound.spring.repositories.ParroquiaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class ParroquiaService implements ServiceGeneral<Parroquia> {

	 @Autowired
	 private ParroquiaRepository repository;

	 public List<Parroquia> getParroquiaPorMunicipio(String id) { 
		 return repository.findByMunicipioId(Long.valueOf(id));
	 }

	 @Override
	 public List<Parroquia> getAll() {
		 List<Parroquia> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Parroquia getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Parroquia saveOrUpdate(Parroquia clase) {
		 if (clase.getId() != null) {
			 Parroquia claseAux = getOne( clase.getId() );
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
		 	Parroquia clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}
}

package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fox.hound.spring.models.combo.Ciudad;
import fox.hound.spring.models.combo.Municipio;
import fox.hound.spring.repositories.MunicipioRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class MunicipioService implements ServiceGeneral<Municipio> {

	 @Autowired
	 private MunicipioRepository repository;

	 public List<Municipio> getMunicipioPorCiudad(String id) { 
		 return repository.findByCiudadId(Long.valueOf(id));
	 }

	 @Override
	 public List<Municipio> getAll() {
		 List<Municipio> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Municipio getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Municipio saveOrUpdate(Municipio clase) {
		 if (clase.getId() != null) {
			 Municipio claseAux = getOne( clase.getId() );
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
		 	Municipio clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}
}

package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.combo.Municipio;
import fox.hound.spring.repositories.MunicipioRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class MunicipioService implements ServiceGeneral<Municipio> {

	 @Autowired
	 private MunicipioRepository repository;

	 @Autowired
	 private EstatusService estatusService;

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
		 clase.setEstatus( estatusService.getOne(clase.getEstatus().getId() ) );
		 return repository.save(clase);
	 }

	 @Override
	 public void delete(Long id) {
		 repository.delete(id);
	 }
}
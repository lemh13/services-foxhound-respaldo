package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.ValoracionOrdenServicio;
import fox.hound.spring.repositories.ValoracionOrdenServicioRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class ValoracionOrdenServicioService implements ServiceGeneral<ValoracionOrdenServicio> {

	 @Autowired
	 private ValoracionOrdenServicioRepository repository;

	 @Override
	 public List<ValoracionOrdenServicio> getAll() {
		 List<ValoracionOrdenServicio> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public ValoracionOrdenServicio getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public ValoracionOrdenServicio saveOrUpdate(ValoracionOrdenServicio clase) {
		 if (clase.getId() != null) {
			 ValoracionOrdenServicio claseAux = getOne( clase.getId() );
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
		 	ValoracionOrdenServicio clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}
	 
	 @Override
	 public ValoracionOrdenServicio activeDesactiveEstatus(String id) {
	  	ValoracionOrdenServicio clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	 }
}

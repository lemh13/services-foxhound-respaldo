package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.combo.ServicioTarea;
import fox.hound.spring.repositories.ServicioTareaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class ServicioTareaService implements ServiceGeneral<ServicioTarea> {

	 @Autowired
	 private ServicioTareaRepository repository;

	 

	 @Override
	 public List<ServicioTarea> getAll() {
		 List<ServicioTarea> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public ServicioTarea getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public ServicioTarea saveOrUpdate(ServicioTarea clase) {
		 if (clase.getId() != null) {
			 ServicioTarea claseAux = getOne( clase.getId() );
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
		 	ServicioTarea clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}
}

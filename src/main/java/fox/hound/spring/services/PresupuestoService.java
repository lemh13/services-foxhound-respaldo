package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.combo.Presupuesto;
import fox.hound.spring.repositories.PresupuestoRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class PresupuestoService implements ServiceGeneral<Presupuesto> {

	 @Autowired
	 private PresupuestoRepository repository;

	 

	 @Override
	 public List<Presupuesto> getAll() {
		 List<Presupuesto> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Presupuesto getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Presupuesto saveOrUpdate(Presupuesto clase) {
		 if (clase.getId() != null) {
			 Presupuesto claseAux = getOne( clase.getId() );
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
		 	Presupuesto clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}
}

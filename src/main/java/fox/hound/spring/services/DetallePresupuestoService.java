package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.DetallePresupuesto;
import fox.hound.spring.repositories.DetallePresupuestoRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class DetallePresupuestoService implements ServiceGeneral<DetallePresupuesto> {

	 @Autowired
	 private DetallePresupuestoRepository repository;

	 

	 @Override
	 public List<DetallePresupuesto> getAll() {
		 List<DetallePresupuesto> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public DetallePresupuesto getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public DetallePresupuesto saveOrUpdate(DetallePresupuesto clase) {
		 if (clase.getId() != null) {
			 DetallePresupuesto claseAux = getOne( clase.getId() );
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
		 	DetallePresupuesto clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public DetallePresupuesto activeDesactiveEstatus(String id) {
		DetallePresupuesto clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

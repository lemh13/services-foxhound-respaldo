package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.MotivoPresupuesto;
import fox.hound.spring.repositories.MotivoPresupuestoRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class MotivoPresupuestoService implements ServiceGeneral<MotivoPresupuesto> {

	 @Autowired
	 private MotivoPresupuestoRepository repository;

	 

	 @Override
	 public List<MotivoPresupuesto> getAll() {
		 List<MotivoPresupuesto> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public MotivoPresupuesto getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public MotivoPresupuesto saveOrUpdate(MotivoPresupuesto clase) {
		 if (clase.getId() != null) {
			 MotivoPresupuesto claseAux = getOne( clase.getId() );
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
		 	MotivoPresupuesto clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public MotivoPresupuesto activeDesactiveEstatus(String id) {
		MotivoPresupuesto clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

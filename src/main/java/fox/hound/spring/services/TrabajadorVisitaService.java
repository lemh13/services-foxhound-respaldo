package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.TrabajadorVisita;
import fox.hound.spring.repositories.TrabajadorVisitaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class TrabajadorVisitaService implements ServiceGeneral<TrabajadorVisita> {

	 @Autowired
	 private TrabajadorVisitaRepository repository;

	 @Override
	 public List<TrabajadorVisita> getAll() {
		 List<TrabajadorVisita> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public TrabajadorVisita getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public TrabajadorVisita saveOrUpdate(TrabajadorVisita clase) {
		 if (clase.getId() != null) {
			 TrabajadorVisita claseAux = getOne( clase.getId() );
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
		 	TrabajadorVisita clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public TrabajadorVisita activeDesactiveEstatus(String id) {
		TrabajadorVisita clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

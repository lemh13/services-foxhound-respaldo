package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.ReclamoOrdenEntrega;
import fox.hound.spring.repositories.ReclamoOrdenEntregaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class ReclamoOrdenEntregaService implements ServiceGeneral<ReclamoOrdenEntrega> {

	 @Autowired
	 private ReclamoOrdenEntregaRepository repository;

	 

	 @Override
	 public List<ReclamoOrdenEntrega> getAll() {
		 List<ReclamoOrdenEntrega> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public ReclamoOrdenEntrega getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public ReclamoOrdenEntrega saveOrUpdate(ReclamoOrdenEntrega clase) {
		 if (clase.getId() != null) {
			 ReclamoOrdenEntrega claseAux = getOne( clase.getId() );
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
		 	ReclamoOrdenEntrega clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public ReclamoOrdenEntrega activeDesactiveEstatus(String id) {
		ReclamoOrdenEntrega clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

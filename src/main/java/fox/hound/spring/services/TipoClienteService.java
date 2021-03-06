package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.TipoCliente;
import fox.hound.spring.repositories.TipoClienteRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class TipoClienteService implements ServiceGeneral<TipoCliente> {

	 @Autowired
	 private TipoClienteRepository repository;

	 

	 @Override
	 public List<TipoCliente> getAll() {
		 List<TipoCliente> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public TipoCliente getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public TipoCliente saveOrUpdate(TipoCliente clase) {
		 if (clase.getId() != null) {
			 TipoCliente claseAux = getOne( clase.getId() );
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
		 	TipoCliente clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public TipoCliente activeDesactiveEstatus(String id) {
		TipoCliente clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

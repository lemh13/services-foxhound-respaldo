package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.UnidadMedida;
import fox.hound.spring.repositories.UnidadMedidaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class UnidadMedidaService implements ServiceGeneral<UnidadMedida> {

	 @Autowired
	 private UnidadMedidaRepository repository;

	 @Override
	 public List<UnidadMedida> getAll() {
		 List<UnidadMedida> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public UnidadMedida getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public UnidadMedida saveOrUpdate(UnidadMedida clase) {
		 if (clase.getId() != null) {
			 UnidadMedida claseAux = getOne( clase.getId() );
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
		 	UnidadMedida clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public UnidadMedida activeDesactiveEstatus(String id) {
		UnidadMedida clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

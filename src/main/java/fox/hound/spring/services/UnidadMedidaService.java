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

	 @Autowired
	 private EstatusService estatusService;

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
		 clase.setEstatus( estatusService.getOne(clase.getEstatus().getId() ) );
		 return repository.save(clase);
	 }

	 @Override
	 public void delete(Long id) {
		 repository.delete(id);
	 }
}

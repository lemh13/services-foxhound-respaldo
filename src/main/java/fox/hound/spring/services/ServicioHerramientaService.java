package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.combo.ServicioHerramienta;
import fox.hound.spring.repositories.ServicioHerramientaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class ServicioHerramientaService implements ServiceGeneral<ServicioHerramienta> {

	 @Autowired
	 private ServicioHerramientaRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<ServicioHerramienta> getAll() {
		 List<ServicioHerramienta> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public ServicioHerramienta getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public ServicioHerramienta saveOrUpdate(ServicioHerramienta clase) {
		 if (clase.getId() != null) {
			 ServicioHerramienta claseAux = getOne( clase.getId() );
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

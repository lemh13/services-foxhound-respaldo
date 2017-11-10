package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.ClienteOcupacion;
import fox.hound.spring.repositories.ClienteOcupacionRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class ClienteOcupacionService implements ServiceGeneral<ClienteOcupacion> {

	 @Autowired
	 private ClienteOcupacionRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<ClienteOcupacion> getAll() {
		 List<ClienteOcupacion> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public ClienteOcupacion getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public ClienteOcupacion saveOrUpdate(ClienteOcupacion clase) {
		 if (clase.getId() != null) {
			 ClienteOcupacion claseAux = getOne( clase.getId() );
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

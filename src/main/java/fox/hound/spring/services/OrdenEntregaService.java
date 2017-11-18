package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.combo.OrdenEntrega;
import fox.hound.spring.repositories.OrdenEntregaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class OrdenEntregaService implements ServiceGeneral<OrdenEntrega> {

	 @Autowired
	 private OrdenEntregaRepository repository;

	 

	 @Override
	 public List<OrdenEntrega> getAll() {
		 List<OrdenEntrega> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public OrdenEntrega getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public OrdenEntrega saveOrUpdate(OrdenEntrega clase) {
		 if (clase.getId() != null) {
			 OrdenEntrega claseAux = getOne( clase.getId() );
			 clase.setFecha_creacion( claseAux.getFecha_creacion() );
		 }
		 clase.setFecha_modificacion( DateUtil.getCurrentDate() );
		 
		 return repository.save(clase);
	 }

	 @Override
	 public void delete(Long id) {
		 repository.delete(id);
	 }
}

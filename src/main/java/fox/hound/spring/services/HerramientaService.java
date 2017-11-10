package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.Herramienta;
import fox.hound.spring.repositories.HerramientaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class HerramientaService implements ServiceGeneral<Herramienta> {

	 @Autowired
	 private HerramientaRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<Herramienta> getAll() {
		 List<Herramienta> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Herramienta getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Herramienta saveOrUpdate(Herramienta clase) {
		 if (clase.getId() != null) {
			 Herramienta claseAux = getOne( clase.getId() );
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

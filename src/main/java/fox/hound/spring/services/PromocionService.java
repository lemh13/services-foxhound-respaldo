package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.Promocion;
import fox.hound.spring.repositories.PromocionRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class PromocionService implements ServiceGeneral<Promocion> {

	 @Autowired
	 private PromocionRepository repository;

	 @Override
	 public List<Promocion> getAll() {
		 List<Promocion> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }
	 
	 public List<Promocion> getAllActive() {
		 List<Promocion> lista = new ArrayList<>();
		 repository.findByEstatus(0).forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Promocion getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Promocion saveOrUpdate(Promocion clase) {
		 if (clase.getId() != null) {
			 Promocion claseAux = getOne( clase.getId() );
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

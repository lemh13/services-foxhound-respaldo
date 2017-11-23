package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.PromocionServicio;
import fox.hound.spring.repositories.PromocionServicioRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class PromocionServicioService implements ServiceGeneral<PromocionServicio> {

	 @Autowired
	 private PromocionServicioRepository repository;

	 @Override
	 public List<PromocionServicio> getAll() {
		 List<PromocionServicio> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public PromocionServicio getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public PromocionServicio saveOrUpdate(PromocionServicio clase) {
		 if (clase.getId() != null) {
			 PromocionServicio claseAux = getOne( clase.getId() );
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
		 	PromocionServicio clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}
}

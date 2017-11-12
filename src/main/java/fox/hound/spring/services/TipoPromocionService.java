package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.TipoPromocion;
import fox.hound.spring.repositories.TipoPromocionRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class TipoPromocionService implements ServiceGeneral<TipoPromocion> {

	 @Autowired
	 private TipoPromocionRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<TipoPromocion> getAll() {
		 List<TipoPromocion> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public TipoPromocion getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public TipoPromocion saveOrUpdate(TipoPromocion clase) {
		 if (clase.getId() != null) {
			 TipoPromocion claseAux = getOne( clase.getId() );
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
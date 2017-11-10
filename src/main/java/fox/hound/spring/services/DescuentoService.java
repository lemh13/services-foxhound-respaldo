package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.combo.Descuento;
import fox.hound.spring.repositories.DescuentoRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class DescuentoService implements ServiceGeneral<Descuento> {

	 @Autowired
	 private DescuentoRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<Descuento> getAll() {
		 List<Descuento> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Descuento getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Descuento saveOrUpdate(Descuento clase) {
		 if (clase.getId() != null) {
			 Descuento claseAux = getOne( clase.getId() );
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

package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.TipoMotivo;
import fox.hound.spring.repositories.TipoMotivoRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class TipoMotivoService implements ServiceGeneral<TipoMotivo> {

	 @Autowired
	 private TipoMotivoRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<TipoMotivo> getAll() {
		 List<TipoMotivo> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public TipoMotivo getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public TipoMotivo saveOrUpdate(TipoMotivo clase) {
		 if (clase.getId() != null) {
			 TipoMotivo claseAux = getOne( clase.getId() );
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

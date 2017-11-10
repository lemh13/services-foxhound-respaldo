package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.combo.AsuntoComentario;
import fox.hound.spring.repositories.AsuntoComentarioRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class AsuntoComentarioService implements ServiceGeneral<AsuntoComentario> {

	 @Autowired
	 private AsuntoComentarioRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<AsuntoComentario> getAll() {
		 List<AsuntoComentario> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public AsuntoComentario getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public AsuntoComentario saveOrUpdate(AsuntoComentario clase) {
		 if (clase.getId() != null) {
			 AsuntoComentario claseAux = getOne( clase.getId() );
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

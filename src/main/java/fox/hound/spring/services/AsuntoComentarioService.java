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

	 @Override
	 public List<AsuntoComentario> getAll() {
		 List<AsuntoComentario> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }
	 
	 public List<AsuntoComentario> getAllActive() {
		 List<AsuntoComentario> lista = new ArrayList<>();
		 repository.findByEstatus(0).forEach(lista::add);
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
		 
		 return repository.save(clase);
	 }

	 @Override
	 public void delete(Long id) {
		 repository.delete(id);
	 }

	@Override
	public void deleteLogic(String id) {
	 	AsuntoComentario clase = getOne(Long.valueOf(id));
		clase.setEstatus(2);
		repository.save(clase);
	}

	@Override
	public AsuntoComentario activeDesactiveEstatus(String id) {
		AsuntoComentario clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

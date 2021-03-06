package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.Categoria;
import fox.hound.spring.repositories.CategoriaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class CategoriaService implements ServiceGeneral<Categoria> {

	 @Autowired
	 private CategoriaRepository repository;

	 

	 @Override
	 public List<Categoria> getAll() {
		 List<Categoria> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Categoria getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Categoria saveOrUpdate(Categoria clase) {
		 if (clase.getId() != null) {
			 Categoria claseAux = getOne( clase.getId() );
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
		 	Categoria clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public Categoria activeDesactiveEstatus(String id) {
		Categoria clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

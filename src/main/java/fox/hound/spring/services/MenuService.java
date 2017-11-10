package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.Menu;
import fox.hound.spring.repositories.MenuRepository;

@Service
public class MenuService implements ServiceGeneral<Menu> {

	 @Autowired
	 private MenuRepository repository;

	 @Override
	 public List<Menu> getAll() {
		 List<Menu> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Menu getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Menu saveOrUpdate(Menu clase) {
		 return repository.save(clase);
	 }

	 @Override
	 public void delete(Long id) {
		 repository.delete(id);
	 }
}

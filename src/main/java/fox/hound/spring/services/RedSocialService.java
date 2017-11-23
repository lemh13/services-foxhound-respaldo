package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.maestros.RedSocial;
import fox.hound.spring.repositories.RedSocialRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class RedSocialService implements ServiceGeneral<RedSocial> {

	 @Autowired
	 private RedSocialRepository repository;

	 

	 @Override
	 public List<RedSocial> getAll() {
		 List<RedSocial> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public RedSocial getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public RedSocial saveOrUpdate(RedSocial clase) {
		 if (clase.getId() != null) {
			 RedSocial claseAux = getOne( clase.getId() );
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
		 	RedSocial clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public RedSocial activeDesactiveEstatus(String id) {
		RedSocial clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

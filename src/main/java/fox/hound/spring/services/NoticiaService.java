package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.Noticia;
import fox.hound.spring.repositories.NoticiaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class NoticiaService implements ServiceGeneral<Noticia> {

	 @Autowired
	 private NoticiaRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<Noticia> getAll() {
		 List<Noticia> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Noticia getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Noticia saveOrUpdate(Noticia clase) {
		 if (clase.getId() != null) {
			 Noticia claseAux = getOne( clase.getId() );
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

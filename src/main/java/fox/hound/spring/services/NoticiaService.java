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

	 @Override
	 public List<Noticia> getAll() {
		 List<Noticia> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }
	 
	 public List<Noticia> getAllActive() {
		 List<Noticia> lista = new ArrayList<>();
		 repository.findByEstatus(0).forEach(lista::add);
		 return lista;
	 }
	 
	 public List<Noticia> getAllUltimas() {
		 List<Noticia> lista = new ArrayList<>();
		 repository.findByEstatus(0).forEach(lista::add);
		 return lista.subList(Math.max(lista.size() - 3, 0), lista.size());
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
			 
			 if (clase.getFecha_caducidad() == null) {
				 clase.setFecha_caducidad( claseAux.getFecha_caducidad() );
			 }
			 if (clase.getImgNoticia() == null) {
				 clase.setImgNoticia( claseAux.getImgNoticia() );
			 }
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
		 	Noticia clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public Noticia activeDesactiveEstatus(String id) {
		Noticia clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

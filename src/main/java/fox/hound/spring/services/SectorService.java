package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fox.hound.spring.models.combo.Sector;
import fox.hound.spring.repositories.SectorRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class SectorService implements ServiceGeneral<Sector> {

	 @Autowired
	 private SectorRepository repository;
	 
	 public List<Sector> getSectorPorParroquia(String id) { 
		 return repository.findByParroquiaId(Long.valueOf(id));
	 }
	 

	 @Override
	 public List<Sector> getAll() {
		 List<Sector> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Sector getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Sector saveOrUpdate(Sector clase) {
		 if (clase.getId() != null) {
			 Sector claseAux = getOne( clase.getId() );
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
		 	Sector clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}


	@Override
	public Sector activeDesactiveEstatus(String id) {
		Sector clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

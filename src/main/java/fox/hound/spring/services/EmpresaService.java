package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.Empresa;
import fox.hound.spring.repositories.EmpresaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class EmpresaService implements ServiceGeneral<Empresa> {

	 @Autowired
	 private EmpresaRepository repository;

	 

	 @Override
	 public List<Empresa> getAll() {
		 List<Empresa> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public Empresa getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public Empresa saveOrUpdate(Empresa clase) {
		 if (clase.getId() != null) {
			 Empresa claseAux = getOne( clase.getId() );
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
		 	Empresa clase = getOne(Long.valueOf(id));
			clase.setEstatus(2);
			repository.save(clase);
		}

	@Override
	public Empresa activeDesactiveEstatus(String id) {
		Empresa clase = getOne(Long.valueOf(id));
		clase.setEstatus( clase.getEstatus() == 0 ? 1 : 0 );
		return repository.save(clase);
	}
}

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

	 @Autowired
	 private EstatusService estatusService;

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
		 clase.setEstatus( estatusService.getOne(clase.getEstatus().getId() ) );
		 return repository.save(clase);
	 }

	 @Override
	 public void delete(Long id) {
		 repository.delete(id);
	 }
}

package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fox.hound.spring.models.puente.RedSocialEmpresa;
import fox.hound.spring.repositories.RedSocialEmpresaRepository;
import fox.hound.spring.utils.DateUtil;

@Service
public class RedSocialEmpresaService implements ServiceGeneral<RedSocialEmpresa> {

	 @Autowired
	 private RedSocialEmpresaRepository repository;

	 @Autowired
	 private EstatusService estatusService;

	 @Override
	 public List<RedSocialEmpresa> getAll() {
		 List<RedSocialEmpresa> lista = new ArrayList<>();
		 repository.findAll().forEach(lista::add);
		 return lista;
	 }

	 @Override
	 public RedSocialEmpresa getOne(Long id) {
		 return repository.findOne(id);
	 }

	 @Override
	 public RedSocialEmpresa saveOrUpdate(RedSocialEmpresa clase) {
		 if (clase.getId() != null) {
			 RedSocialEmpresa claseAux = getOne( clase.getId() );
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

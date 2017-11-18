//package fox.hound.spring.services;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import fox.hound.spring.models.puente.Puente;
//import fox.hound.spring.repositories.PuenteRepository;
//import fox.hound.spring.utils.DateUtil;
//
//@Service
//public class PuenteService {
//
//	@Autowired
//	private PuenteRepository repository;
//	
//	@Autowired
//	private EstatusService estatusService;
//	
//	public List<Puente> getAll(String type) {
//		List<Puente> lista = new ArrayList<>();
//		repository.findByType(type).forEach(lista::add);
//		return lista;
//	}
//
//	public Puente getOne(Long id) {
//		return repository.findOne(id);
//	}
//
//	public Puente saveOrUpdate(Puente clase) {
//		if (clase.getId() != null) {
//			Puente claseAux = getOne( clase.getId() );
//
//			clase.setFecha_creacion( claseAux.getFecha_creacion() );
//		}
//		clase.setFecha_modificacion( DateUtil.getCurrentDate() );
//		
//		
//		return repository.save(clase);
//	}
//
//	public void delete(Long id) {
//		repository.delete(id);
//	}
//
//}

package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fox.hound.spring.models.Inmueble;
import fox.hound.spring.repositories.InmuebleRepository;

@Service
public class InmuebleService {
	
	@Autowired
	private InmuebleRepository preferenciaRepository;

	public List<Inmueble> getAllInmuebles(String usuarioId) {
		List<Inmueble> lista = new ArrayList<>();
		preferenciaRepository.findByClienteId(Long.valueOf(usuarioId))
		.forEach(lista::add);
		
		return lista.isEmpty() ? null : lista;
	}
	
	public Inmueble getInmueble(Long id) {
		return preferenciaRepository.findOne(id);
	}
	
	public Inmueble saveOrUpdateInmueble(Inmueble inmueble) {
		Inmueble p = preferenciaRepository.save(inmueble);
		return p;
	}

	public void deleteInmueble(Long id) {
		preferenciaRepository.delete(id);
	}

}

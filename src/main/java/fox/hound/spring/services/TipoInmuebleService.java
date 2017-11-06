package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fox.hound.spring.models.TipoInmueble;
import fox.hound.spring.repositories.TipoInmuebleRepository;
import fox.hound.spring.utils.EncryptionUtil;

@Service
public class TipoInmuebleService {
	
	@Autowired
	private TipoInmuebleRepository tipoinmuebleRepository;
	
	@Autowired
	private EncryptionUtil encript;

	public List<TipoInmueble> getAllTipoInmuebles() {
		List<TipoInmueble> lista = new ArrayList<>();
		tipoinmuebleRepository.findAll().forEach(lista::add);
		return lista.isEmpty() ? null : lista;
	}
	
	public TipoInmueble getTipoInmueble(Long id) {
		return tipoinmuebleRepository.findOne(id);
	}

	public TipoInmueble getTipoInmuebleByDescripcion(String descripcion) {
		return tipoinmuebleRepository.findByDescripcion(descripcion);
	}
	
	
	public TipoInmueble saveOrUpdateUser(TipoInmueble tipoinmueble) {
		tipoinmueble.setDescripcion(tipoinmueble.getDescripcion());
		TipoInmueble ti = tipoinmuebleRepository.save(tipoinmueble);
		return ti;
	}

	public void deleteTipoInmueble(Long id) {
		tipoinmuebleRepository.delete(id);
	}

}

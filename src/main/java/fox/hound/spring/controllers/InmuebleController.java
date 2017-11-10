package fox.hound.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fox.hound.spring.models.Cliente;
import fox.hound.spring.models.Inmueble;
import fox.hound.spring.services.InmuebleService;
import fox.hound.spring.services.PersonaService;
import fox.hound.spring.utils.ResponseDefault;

@RestController
public class InmuebleController {
	
	@Autowired
	private InmuebleService preferenciaService;
	@Autowired	
	private PersonaService personaService;
	
	private Class<?> CLASE = Inmueble.class;
	
	@RequestMapping("/cliente/{clienteId}/inmueble/buscarTodos")
	public ResponseEntity<?> getAllInmuebles(@PathVariable String clienteId) {
		return ResponseDefault.ok(preferenciaService.getAllInmuebles(clienteId), CLASE, ResponseDefault.PLURAL);
	}
	
	@RequestMapping("/inmueble/{id}")
	public ResponseEntity<?> getInmueble(@PathVariable String id) {
		return ResponseDefault.ok(preferenciaService.getInmueble(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	}
	
	@RequestMapping(value="/usuario/{personaId}/inmueble", method=RequestMethod.POST)
	public ResponseEntity<?> addInmueble(@RequestBody Inmueble inmueble, @PathVariable String personaId) {
		Cliente c = (Cliente) personaService.getOne(Long.valueOf(personaId));
		
		if (c == null) {
			return ResponseDefault.ok(new Inmueble(), CLASE, ResponseDefault.SINGULAR);
		} else {
			inmueble.setCliente(c);
			return ResponseDefault.ok(preferenciaService.saveOrUpdateInmueble(inmueble), CLASE, ResponseDefault.SINGULAR);
		}
	}
	
	@RequestMapping(value="/usuario/{usuarioId}/inmueble", method=RequestMethod.PUT)
	public void updateInmueble(@RequestBody Inmueble inmueble, @PathVariable String usuarioId, @PathVariable String id) {
//		Usuario u = usuarioService.getUser(Long.valueOf(usuarioId));
//
//		if (u != null)
			preferenciaService.saveOrUpdateInmueble(inmueble);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/inmueble/{id}")
	public void deleteInmueble(@PathVariable String id) {
		preferenciaService.deleteInmueble(Long.valueOf(id));
	}

}

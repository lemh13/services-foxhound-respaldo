package fox.hound.spring.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fox.hound.spring.models.TipoServicio;
import fox.hound.spring.services.MaestroService;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("tipo_servicio")
public class TipoServicioController {

	@Autowired
	private MaestroService service;
	
	private Class<?> CLASE = TipoServicio.class;

	@RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getAll(HttpServletRequest request) {
		return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	}

	@RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	}

	@RequestMapping(value="/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> agregar(@RequestBody TipoServicio clase, HttpServletRequest request) {
		return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	}

	@RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void modificar(@RequestBody TipoServicio clase, HttpServletRequest request) {
		ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	}

	@RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void borrar(@PathVariable String id, HttpServletRequest request) {
		service.delete(Long.valueOf(id));
	}
	
}


package fox.hound.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fox.hound.spring.models.Estatus;
import fox.hound.spring.services.EstatusService;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("estatus")
public class EstatusController {
	
	@Autowired
	private EstatusService estatusService;
	
	private Class<?> CLASE = Estatus.class;

	@RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getAll(HttpServletRequest request) {
		
		List<Estatus> estatus = new ArrayList<>();
		estatus.add(new Estatus(Long.valueOf(1), "Activo"));
		estatus.add(new Estatus(Long.valueOf(2), "Suspendido"));
		estatus.add(new Estatus(Long.valueOf(3), "Eliminado"));
		
		return ResponseDefault.ok(estatus, CLASE, ResponseDefault.PLURAL);
	}

	@RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		return ResponseDefault.ok(estatusService.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	}

	@RequestMapping(value="/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> agregar(@RequestBody Estatus clase, HttpServletRequest request) {
		return ResponseDefault.ok(estatusService.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	}

	@RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> modificar(@RequestBody Estatus clase, HttpServletRequest request) {
		return ResponseDefault.ok(estatusService.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	}

	@RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void borrar(@PathVariable String id, HttpServletRequest request) {
		estatusService.delete(Long.valueOf(id));
	}

}

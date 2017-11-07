package fox.hound.spring.controllers.combo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fox.hound.spring.models.combo.CondicionInmueble;
import fox.hound.spring.models.maestros.Condicion;
import fox.hound.spring.services.MaestroService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("condicionInmueble")
public class CondicionInmuebleController {

	@Autowired
	private MaestroService service;
	
	private Class<?> CLASE = CondicionInmueble.class;

	@RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getAll(HttpServletRequest request) {
		return ResponseDefault.ok(service.getAll("CondicionInmueble"), CLASE, ResponseDefault.PLURAL);
	}

	@RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	}

	@RequestMapping(value="condicion/{id}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> agregar(@RequestBody CondicionInmueble clase, @PathVariable String id, HttpServletRequest request) {
		clase.setFecha_creacion( DateUtil.getCurrentDate() );
		Condicion padre = (Condicion) service.getOne(Long.valueOf(id));
		
		if (padre != null) {
			clase.setCondicion(padre);
			return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
		} else {
			return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Condicion");
		}
	}

	@RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> modificar(@RequestBody CondicionInmueble clase, HttpServletRequest request) {
		return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	}

	@RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		service.delete(Long.valueOf(id));
		return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "CondicionInmueble");
	}
	
}

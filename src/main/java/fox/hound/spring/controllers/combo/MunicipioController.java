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

import fox.hound.spring.models.combo.Ciudad;
import fox.hound.spring.models.combo.Municipio;
import fox.hound.spring.services.MaestroService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("municipio")
public class MunicipioController {

	@Autowired
	private MaestroService service;
	
	private Class<?> CLASE = Municipio.class;

	@RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getAll(HttpServletRequest request) {
		return ResponseDefault.ok(service.getAll("Municipio"), CLASE, ResponseDefault.PLURAL);
	}

	@RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	}

	@RequestMapping(value="ciudad/{id}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> agregar(@RequestBody Municipio clase, @PathVariable String id, HttpServletRequest request) {
		clase.setFecha_creacion( DateUtil.getCurrentDate() );
		Ciudad ciudad = (Ciudad) service.getOne(Long.valueOf(id));
		
		if (ciudad != null) {
			clase.setCiudad(ciudad);
			return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
		} else {
			return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Ciudad");
		}
	}

	@RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> modificar(@RequestBody Municipio clase, HttpServletRequest request) {
		return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	}

	@RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		service.delete(Long.valueOf(id));
		return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "Municipio");
	}
	
}

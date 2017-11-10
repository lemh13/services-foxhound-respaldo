package fox.hound.spring.controllers.maestros;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fox.hound.spring.models.maestros.CategoriaInmueble;
import fox.hound.spring.models.maestros.TipoInmueble;
import fox.hound.spring.services.CategoriaInmuebleService;
import fox.hound.spring.services.TipoInmuebleService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("tipoInmueble")
public class TipoInmuebleController {

	@Autowired
	private TipoInmuebleService service;
	
	@Autowired
	private CategoriaInmuebleService categoriaInmuebleService;
	
	private Class<?> CLASE = TipoInmueble.class;

	@RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getAll(HttpServletRequest request) {
		return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	}

	@RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	}

	@RequestMapping(value="categoriaInmueble/{id}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> agregar(@RequestBody TipoInmueble clase, @PathVariable String id, HttpServletRequest request) {
		clase.setFecha_creacion( DateUtil.getCurrentDate() );
		
		CategoriaInmueble padre = categoriaInmuebleService.getOne(Long.valueOf(id));
		
		if (padre != null) {
			clase.setCategoriaInmueble(padre);
			return ResponseDefault.messageAndObject(MessageUtil.GUARDAR_REGISTRO, "Tipo de Inmueble", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
		} else {
			return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Categoria del Inmueble");
		}
	}

	@RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> modificar(@RequestBody TipoInmueble clase, HttpServletRequest request) {
		return ResponseDefault.messageAndObject(MessageUtil.ACTUALIZAR_REGISTRO, "Tipo de Inmueble", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	}

	@RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		service.delete(Long.valueOf(id));
		return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "Tipo de Inmueble");
	}
	
	
}

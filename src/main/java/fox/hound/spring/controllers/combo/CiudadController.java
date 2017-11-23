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
import fox.hound.spring.models.maestros.Estado;
import fox.hound.spring.services.CiudadService;
import fox.hound.spring.services.EstadoService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("ciudad")
public class CiudadController {

	@Autowired
	private CiudadService service;
	
	@Autowired
	private EstadoService estadoService;
	
	private Class<?> CLASE = Ciudad.class;

	@RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getAll(HttpServletRequest request) {
		return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	}

	@RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	}

	@RequestMapping(value="estado/{id}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> agregar(@RequestBody Ciudad clase, @PathVariable String id, HttpServletRequest request) {
		clase.setFecha_creacion( DateUtil.getCurrentDate() );
		Estado estado = estadoService.getOne(Long.valueOf(id));
		
		if (estado != null) {
			clase.setEstado(estado);
			return ResponseDefault.messageAndObject(MessageUtil.GUARDAR_REGISTRO, "Ciudad", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
		} else {
			return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Estado");
		}
	}

	@RequestMapping(value="estado/{id}/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> modificar(@RequestBody Ciudad clase, @PathVariable String id,HttpServletRequest request) {
		Estado estado = estadoService.getOne(Long.valueOf(id));
		
		if (estado != null) {
			clase.setEstado(estado);
			return ResponseDefault.messageAndObject(MessageUtil.ACTUALIZAR_REGISTRO, "Ciudad", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
		} else {
			return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Estado");
		}
	}

	@RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		service.delete(Long.valueOf(id));
		return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "Ciudad");
	}
	
	@RequestMapping(value="/estado/{id}/buscarPorEstado", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> buscarPorEstado(@PathVariable String id, HttpServletRequest request) {
		return ResponseDefault.ok(service.getCiudadPorEstado(id), CLASE, ResponseDefault.SINGULAR); 
	}
	
}

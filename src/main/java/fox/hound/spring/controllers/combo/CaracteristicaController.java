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

import fox.hound.spring.models.combo.Caracteristica;
import fox.hound.spring.models.maestros.TipoCaracteristica;
import fox.hound.spring.services.CaracteristicaService;
import fox.hound.spring.services.TipoCaracteristicaService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("caracteristica")
public class CaracteristicaController {

	@Autowired
	private CaracteristicaService service;
	
	@Autowired
	private TipoCaracteristicaService tipoCaracteristicaService;
	
	private Class<?> CLASE = Caracteristica.class;

	@RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getAll(HttpServletRequest request) {
		return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	}

	@RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	}

	@RequestMapping(value="tipoCaracteristica/{id}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> agregar(@RequestBody Caracteristica clase, @PathVariable String id, HttpServletRequest request) {
		clase.setFecha_creacion( DateUtil.getCurrentDate() );
		TipoCaracteristica padre = tipoCaracteristicaService.getOne(Long.valueOf(id));
		
		if (padre != null) {
			clase.setTipoCaracteristica(padre);
			return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
		} else {
			return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Tipo de Caracteristica");
		}
	}

	@RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> modificar(@RequestBody Caracteristica clase, HttpServletRequest request) {
		return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	}

	@RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		service.delete(Long.valueOf(id));
		return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "Caracteristica");
	}
	
}

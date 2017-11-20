package fox.hound.spring.controllers.puente;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fox.hound.spring.models.maestros.TipoCaracteristica;
import fox.hound.spring.models.maestros.TipoInmueble;
import fox.hound.spring.models.puente.TipoCaracteristicaInmueble;
import fox.hound.spring.services.TipoCaracteristicaInmuebleService;
import fox.hound.spring.services.TipoCaracteristicaService;
import fox.hound.spring.services.TipoInmuebleService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("tipocaracteristicainmueble")
public class TipoCaracteristicaInmuebleController {

	 @Autowired
	 private TipoCaracteristicaInmuebleService service;
	 
	 @Autowired
	 private TipoCaracteristicaService tipoCaracteristicaService;
	 
	 @Autowired
	 private TipoInmuebleService tipoInmuebleService;

	 private Class<?> CLASE = TipoCaracteristicaInmueble.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/tipoCaracteristica/{tipoCaracteristicaid}/tipoInmueble/{tipoInmuebleid}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@PathVariable String tipoCaracteristicaid, @PathVariable String tipoInmuebleid, HttpServletRequest request) {
		 TipoCaracteristicaInmueble clase = new TipoCaracteristicaInmueble();
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 TipoCaracteristica tipoCaracteristica = tipoCaracteristicaService.getOne(Long.valueOf(tipoCaracteristicaid));
		 TipoInmueble tipoInmueble = tipoInmuebleService.getOne(Long.valueOf(tipoInmuebleid));
		 
		 if (tipoCaracteristica != null && tipoInmueble != null) {
				clase.setTipoCaracteristica(tipoCaracteristica);
				clase.setTipoInmueble(tipoInmueble);
				return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
		 } else if (tipoCaracteristica == null) {
			 return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Tipo de Caracteristica");
		} else {
			return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Tipo de Inmueble");
		}
	 }

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody TipoCaracteristicaInmueble clase, HttpServletRequest request) {
		 return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "TipoCaracteristicaInmueble");
	 }

}

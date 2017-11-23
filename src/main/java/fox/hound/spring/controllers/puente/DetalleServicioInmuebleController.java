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

import fox.hound.spring.models.Servicio;
import fox.hound.spring.models.puente.DetalleServicioInmueble;
import fox.hound.spring.models.puente.TipoCaracteristicaInmueble;
import fox.hound.spring.services.DetalleServicioInmuebleService;
import fox.hound.spring.services.ServicioService;
import fox.hound.spring.services.TipoCaracteristicaInmuebleService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("detalleServicioInmueble")
public class DetalleServicioInmuebleController {

	 @Autowired
	 private DetalleServicioInmuebleService service;
	 
	 @Autowired 
	 private TipoCaracteristicaInmuebleService tipoCaracteristicaInmuebleService;
	 
	 @Autowired
	 private ServicioService servicioService;
	 
	 private Class<?> CLASE = DetalleServicioInmueble.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="servicio/{servicioId}/tipoCaracteristicaInmueble{tipoCaracteristicaInmuebleId}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@PathVariable String servicioId, @PathVariable String tipoCaracteristicaInmuebleId, HttpServletRequest request) {
		 DetalleServicioInmueble clase = new DetalleServicioInmueble();
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 TipoCaracteristicaInmueble tipoCaracteristicaInmueble = tipoCaracteristicaInmuebleService.getOne(Long.valueOf(tipoCaracteristicaInmuebleId));
		 Servicio servicio = servicioService.getOne(Long.valueOf(servicioId));
		 
		 // PENDIENTE -> @ManyToOne
		 if (tipoCaracteristicaInmueble != null && servicio != null) {
				clase.setTipoCaracteristicaInmueble(tipoCaracteristicaInmueble);
				clase.setServicio(servicio);

				return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
		 } else if (tipoCaracteristicaInmueble == null) {
			 return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Tipo Caracretistica Inmueble");
		} else {
			return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Servicio");
		}
	 }

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody DetalleServicioInmueble clase, HttpServletRequest request) {
		 return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "DetalleOrdenServicio");
	 }

	 @RequestMapping(value="/borrarLogico/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrarLogico(@PathVariable String id, HttpServletRequest request) {
		 service.deleteLogic(id);
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "Rol");
	 }
	 
	 @RequestMapping(value="/activeDesactiveEstatus/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> activeDesactiveEstatus(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.messageAndObject(MessageUtil.ACTUALIZAR_REGISTRO, "Rol", service.activeDesactiveEstatus(id), CLASE, ResponseDefault.SINGULAR);
	 }
}

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
import fox.hound.spring.models.puente.Solicitud;
import fox.hound.spring.models.puente.SolicitudServicio;
import fox.hound.spring.services.ServicioService;
import fox.hound.spring.services.SolicitudService;
import fox.hound.spring.services.SolicitudServicioService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("solicitudservicio")
public class SolicitudServicioController {

	 @Autowired
	 private SolicitudServicioService service;
	 
	 @Autowired
	 private SolicitudService solicitudService;
	 
	 @Autowired
	 private ServicioService servicioService;

	 private Class<?> CLASE = SolicitudServicio.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/solicitud/{solicitudid}/servicio/{servicioid}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@RequestBody SolicitudServicio clase, @PathVariable String solicitudid, @PathVariable String servicioid, HttpServletRequest request) {
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 Solicitud solicitud = solicitudService.getOne(Long.valueOf(solicitudid));
		 Servicio servicio = servicioService.getOne(Long.valueOf(servicioid));
		 
		 if (solicitud != null && servicio != null) {
				clase.setSolicitud(solicitud);
				clase.setServicio(servicio);
				return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
			} else {
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Solicitud");
			}
	 }

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody SolicitudServicio clase, HttpServletRequest request) {
		 return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "SolicitudServicio");
	 }

}

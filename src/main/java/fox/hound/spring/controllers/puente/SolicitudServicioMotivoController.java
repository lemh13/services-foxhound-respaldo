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

import fox.hound.spring.models.combo.Motivo;
import fox.hound.spring.models.puente.SolicitudServicio;
import fox.hound.spring.models.puente.SolicitudServicioMotivo;
import fox.hound.spring.services.MotivoService;
import fox.hound.spring.services.SolicitudServicioMotivoService;
import fox.hound.spring.services.SolicitudServicioService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("solicitudserviciomotivo")
public class SolicitudServicioMotivoController {

	 @Autowired
	 private SolicitudServicioMotivoService service;
	 
	 @Autowired 
	 private SolicitudServicioService solicitudServicioService;
	 
	 @Autowired 
	 private MotivoService motivoService;

	 private Class<?> CLASE = SolicitudServicioMotivo.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/solicitudServicio/{solicitudServicioid}/motivo/{motivoid}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@RequestBody SolicitudServicioMotivo clase, @PathVariable String solicitudServicioid, @PathVariable String motivoid, HttpServletRequest request) {
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 SolicitudServicio solicitudServicio = solicitudServicioService.getOne(Long.valueOf(solicitudServicioid));
		 Motivo motivo = motivoService.getOne(Long.valueOf(motivoid));
		 
		 if (solicitudServicio != null && motivo != null) {
				clase.setSolicitudServicio(solicitudServicio);
				clase.setMotivo(motivo);
				return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
			} else {
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Valoracion Orden Servicio");
			}
	 }

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody SolicitudServicioMotivo clase, HttpServletRequest request) {
		 return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "SolicitudServicioMotivo");
	 }

}

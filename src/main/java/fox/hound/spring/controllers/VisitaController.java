package fox.hound.spring.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fox.hound.spring.models.OrdenServicio;
import fox.hound.spring.models.Visita;
import fox.hound.spring.models.maestros.TipoVisita;
import fox.hound.spring.models.maestros.Turno;
import fox.hound.spring.models.puente.Solicitud;
import fox.hound.spring.services.OrdenServicioService;
import fox.hound.spring.services.SolicitudService;
import fox.hound.spring.services.TipoVisitaService;
import fox.hound.spring.services.TurnoService;
import fox.hound.spring.services.VisitaService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("visita")
public class VisitaController {

	 @Autowired
	 private VisitaService service;
	 @Autowired
	 private TurnoService turnoService;
	 @Autowired
	 private TipoVisitaService tipoVisitaService;
	 @Autowired
	 private SolicitudService solicitudService;
	 @Autowired
	 private OrdenServicioService ordenServicioService;
	 
	 private Class<?> CLASE = Visita.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="turno/{turnoId}/tipoVisita/{tipoVisitaId}/solicitud/{solicitudId}/ordenServicio/{ordenServicioId}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@RequestBody Visita clase, @PathVariable String turnoId, @PathVariable String tipoVisitaId, @PathVariable String solicitudId, @PathVariable String ordenServicioId, HttpServletRequest request) {
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 Turno turno = turnoService.getOne(Long.valueOf(turnoId));
		 TipoVisita tipoVisita = tipoVisitaService.getOne(Long.valueOf(tipoVisitaId));
		 Solicitud solicitud = solicitudService.getOne(Long.valueOf(solicitudId));
		 OrdenServicio ordenServicio = ordenServicioService.getOne(Long.valueOf(ordenServicioId));
		 
		 if (turno != null && tipoVisita != null && solicitud != null && ordenServicio != null) {
				return ResponseDefault.messageAndObject(MessageUtil.GUARDAR_REGISTRO, "Visita", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
		 } else if (turno == null) {
			 return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Turno");
		 } else if (tipoVisita == null) {
			 return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Tipo de Visita");
		 } else if (solicitud == null) {
			 return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Solicitud");
		 } else {
			 return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Orde de Servicio");
		 }
	 }

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody Visita clase, HttpServletRequest request) {
			return ResponseDefault.messageAndObject(MessageUtil.ACTUALIZAR_REGISTRO, "Visita", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "Visita");
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

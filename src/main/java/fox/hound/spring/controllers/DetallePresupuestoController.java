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
import fox.hound.spring.models.DetallePresupuesto;
import fox.hound.spring.models.combo.Presupuesto;
import fox.hound.spring.models.puente.DetalleDiagnosticoVisita;
import fox.hound.spring.models.puente.SolicitudServicio;
import fox.hound.spring.services.*;
import fox.hound.spring.services.DetalleDiagnosticoVisitaService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("detallepresupuesto")
public class DetallePresupuestoController {

	@Autowired
	private DetallePresupuestoService service;
	@Autowired
	private DetalleDiagnosticoVisitaService detalleDiagnosticoVisitaService;
	@Autowired
	private PresupuestoService presupuestoService;
	@Autowired
	private SolicitudServicioService solicitudServicioService;

	 private Class<?> CLASE = DetallePresupuesto.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/presupuesto/{id_p}/detalleDiagnosticoVisita/{id_d}/SolicitudServicio/{id_s}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@RequestBody DetallePresupuesto clase, @PathVariable String id_p, @PathVariable String id_d,@PathVariable String id_s, HttpServletRequest request) {
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 Presupuesto presupuesto = presupuestoService.getOne(Long.valueOf(id_p));
		 DetalleDiagnosticoVisita detalleDiagnosticoVisita = detalleDiagnosticoVisitaService.getOne(Long.valueOf(id_d));
		 SolicitudServicio solicitudServicio = solicitudServicioService.getOne(Long.valueOf(id_s));		 
		 if(presupuesto != null) {
			 clase.setPresupuesto(presupuesto);
			 if(detalleDiagnosticoVisita!=null) {
				 clase.setDetalleDiagnosticoVisita(detalleDiagnosticoVisita);
				 if(solicitudServicio != null) {
					clase.setSolicitudServicio(solicitudServicio);
					return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
				 }else {
						return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Solicitud Servicio");
				  }					 
			 }else{
					return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Detalle Diagnotico Visita");
			 }
		 }else{
			return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Presupuesto");
		 }
	 }

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody DetallePresupuesto clase, HttpServletRequest request) {
			return ResponseDefault.messageAndObject(MessageUtil.ACTUALIZAR_REGISTRO, "Detalle Presupuesto", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "DetallePresupuesto");
	 }

}

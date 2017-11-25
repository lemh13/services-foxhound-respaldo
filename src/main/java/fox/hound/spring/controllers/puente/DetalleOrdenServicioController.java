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

import fox.hound.spring.models.OrdenServicio;
import fox.hound.spring.models.combo.ServicioTarea;
import fox.hound.spring.models.puente.DetalleOrdenServicio;
import fox.hound.spring.models.puente.SolicitudServicio;
import fox.hound.spring.services.DetalleOrdenServicioService;
import fox.hound.spring.services.OrdenServicioService;
import fox.hound.spring.services.ServicioTareaService;
import fox.hound.spring.services.SolicitudServicioService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("detalleordenservicio")
public class DetalleOrdenServicioController {

	 @Autowired
	 private DetalleOrdenServicioService service;
	 
	 @Autowired 
	 private OrdenServicioService ordenServicioService;
	 
	 @Autowired
	 private ServicioTareaService servicioTareaService;
	 
	 @Autowired
	 private SolicitudServicioService solicitudServicioServices;
	 
	 private Class<?> CLASE = DetalleOrdenServicio.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="servicioTarea/{servicioTareaid}/ordenServicio/{ordenServicioid}/solicitudServicio{solicitudServicioid}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@PathVariable String servicioTareaid, @PathVariable String ordenServicioid, @PathVariable String solicitudServicioid, HttpServletRequest request) {
		 DetalleOrdenServicio clase = new DetalleOrdenServicio();
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 OrdenServicio ordenServicio = ordenServicioService.getOne(Long.valueOf(ordenServicioid));
		 ServicioTarea servicioTarea = servicioTareaService.getOne(Long.valueOf(servicioTareaid));
		 SolicitudServicio solicitudServicio = solicitudServicioServices.getOne(Long.valueOf(solicitudServicioid));
		 
		 // PENDIENTE -> @ManyToOne
		 if (servicioTarea != null && ordenServicio != null && solicitudServicio != null ) {
				clase.setOrdenServicio(ordenServicio);
				clase.setServicioTarea(servicioTarea);
				clase.setSolicitudServicio(solicitudServicio);
				return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
			} else {
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Detalle Orden de Servicio");
			}
	 }

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody DetalleOrdenServicio clase, HttpServletRequest request) {
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
	 
	 @RequestMapping(value="/trabajador/{id}/buscarPorTrabajador", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ResponseEntity<?> buscarPorTrabajador(@PathVariable String id, HttpServletRequest request) {
			return ResponseDefault.ok(service.getOrdenServicioPorTrabajador(id), CLASE, ResponseDefault.SINGULAR); 
		}

}

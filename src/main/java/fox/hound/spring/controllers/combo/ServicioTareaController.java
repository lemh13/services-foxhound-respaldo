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

import fox.hound.spring.models.Servicio;
import fox.hound.spring.models.combo.ServicioTarea;
import fox.hound.spring.models.maestros.Tarea;
import fox.hound.spring.services.ServicioService;
import fox.hound.spring.services.ServicioTareaService;
import fox.hound.spring.services.TareaService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("serviciotarea")
public class ServicioTareaController {

	 @Autowired
	 private ServicioTareaService service;
	 
	 @Autowired
	 private TareaService tareaservice;
	 
	 @Autowired
	 private ServicioService servicioservice;

	 private Class<?> CLASE = ServicioTarea.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="tarea/{id}/servicio/{id_s}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@RequestBody ServicioTarea clase, @PathVariable String id,  @PathVariable String id_s, HttpServletRequest request) {
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 Tarea tarea = tareaservice.getOne(Long.valueOf(id));
		 Servicio servicio = servicioservice.getOne(Long.valueOf(id_s));
		 
			
			if (tarea != null && servicio!=null) 
			{
				clase.setServicio(servicio);
				clase.setTarea(tarea);
				return ResponseDefault.messageAndObject(MessageUtil.GUARDAR_REGISTRO, "Servicio Tarea", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
			} 
			else if (tarea==null)
			{
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Tarea");
			}
			else 
			{
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Servicio");
			}
		
		
	 }

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody ServicioTarea clase, HttpServletRequest request) {
		 return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "ServicioTarea");
	 }

}

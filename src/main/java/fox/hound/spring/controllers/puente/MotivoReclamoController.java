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
import fox.hound.spring.models.puente.MotivoReclamo;
import fox.hound.spring.services.MotivoReclamoService;
import fox.hound.spring.services.MotivoService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("motivoreclamo")
public class MotivoReclamoController {

	 @Autowired
	 private MotivoReclamoService service;
	 
	 @Autowired
	 private MotivoService motivoService;


	 private Class<?> CLASE = MotivoReclamo.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }


	 @RequestMapping(value="motivo/{motivoid}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@RequestBody MotivoReclamo clase,@PathVariable String motivoid, HttpServletRequest request) {
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 Motivo motivo = motivoService.getOne(Long.valueOf(motivoid));
		 
		 if (motivo != null) {
				clase.setMotivo(motivo);
				return ResponseDefault.messageAndObject(MessageUtil.GUARDAR_REGISTRO, "Motivo de Reclamo", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
			} else {
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Motivo Reclamo");
				}
	 }

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody MotivoReclamo clase, HttpServletRequest request) {
		 return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "MotivoReclamo");
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

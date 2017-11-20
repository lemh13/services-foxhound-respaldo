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

import fox.hound.spring.models.Trabajador;
import fox.hound.spring.models.Visita;
import fox.hound.spring.models.puente.TrabajadorVisita;
import fox.hound.spring.services.TrabajadorService;
import fox.hound.spring.services.TrabajadorVisitaService;
import fox.hound.spring.services.VisitaService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("trabajadorvisita")
public class TrabajadorVisitaController {

	 @Autowired
	 private TrabajadorVisitaService service;
	 
	 @Autowired
	 private TrabajadorService trabajadorService;
	 
	 @Autowired
	 private VisitaService visitaService;

	 private Class<?> CLASE = TrabajadorVisita.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/tabajador/{trabajadorid}/visita/{visitaid}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar( @PathVariable String trabajadorid, @PathVariable String visitaid, HttpServletRequest request) {
		 TrabajadorVisita clase = new TrabajadorVisita();
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 Trabajador trabajador = trabajadorService.getOne(Long.valueOf(trabajadorid));
		 Visita visita = visitaService.getOne(Long.valueOf(visitaid));
		 
		 if (trabajador != null && visita != null) {
				clase.setTrabajador(trabajador);
				clase.setVisita(visita);
				return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
			} else {
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Trabajador Visita");
			}
	 }

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody TrabajadorVisita clase, HttpServletRequest request) {
		 return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "TrabajadorVisita");
	 }

}

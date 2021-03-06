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

import fox.hound.spring.models.DiagnosticoVisita;
import fox.hound.spring.models.maestros.TipoDiagnosticoVisita;
import fox.hound.spring.services.DiagnosticoVisitaService;
import fox.hound.spring.services.TipoDiagnosticoVisitaService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("diagnosticovisita")
public class DiagnosticoVisitaController {

	 @Autowired
	 private DiagnosticoVisitaService service;
	 
	 @Autowired
	 private TipoDiagnosticoVisitaService tipoDiagnosticoVisitaService;

	 private Class<?> CLASE = DiagnosticoVisita.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="tipoDiagnosticoVisita/{id_tdv}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@RequestBody DiagnosticoVisita clase, @PathVariable String id_tdv, HttpServletRequest request) {
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 TipoDiagnosticoVisita tipoDiagnosticoVisita = tipoDiagnosticoVisitaService.getOne(Long.valueOf(id_tdv));
		 
		 if (tipoDiagnosticoVisita != null) {
				clase.setTipoDiagnosticoVisita(tipoDiagnosticoVisita);
				return ResponseDefault.messageAndObject(MessageUtil.GUARDAR_REGISTRO, "Diagnostico Visita", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
			} else {
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "TipoDiagnosticoVisita");
			}
	 }

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody DiagnosticoVisita clase, HttpServletRequest request) {
			return ResponseDefault.messageAndObject(MessageUtil.ACTUALIZAR_REGISTRO, "Diagnostico Visita", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "DiagnosticoVisita");
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

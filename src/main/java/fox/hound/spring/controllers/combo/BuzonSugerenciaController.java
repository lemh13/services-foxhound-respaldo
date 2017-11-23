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

import fox.hound.spring.models.Persona;
import fox.hound.spring.models.combo.AsuntoComentario;
import fox.hound.spring.models.combo.BuzonSugerencia;
import fox.hound.spring.services.AsuntoComentarioService;
import fox.hound.spring.services.BuzonSugerenciaService;
import fox.hound.spring.services.PersonaService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("buzonsugerencia")
public class BuzonSugerenciaController {

	 @Autowired
	 private BuzonSugerenciaService service;
	 
	 @Autowired
	 private AsuntoComentarioService asuntoComentarioService;
	 
	 @Autowired
	 private PersonaService personaService;

	 private Class<?> CLASE = BuzonSugerencia.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="persona/{id_persona}/asuntoComentario/{id_ac}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@RequestBody BuzonSugerencia clase, @PathVariable String id_persona, @PathVariable String id_ac, HttpServletRequest request) {
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 Persona persona = personaService.getOne(Long.valueOf(id_persona));
		 AsuntoComentario asuntoComentario = asuntoComentarioService.getOne(Long.valueOf(id_ac));
			
			if (persona != null) {
				clase.setPersona(persona);
				if(asuntoComentario != null) {
					clase.setAsuntoComentario(asuntoComentario);
					return ResponseDefault.messageAndObject(MessageUtil.GUARDAR_REGISTRO, "Buzon de Sugerencia", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
				} else {
					return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "AsuntoComentario");
				}
			} else {
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Persona");
			}
	 }

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody BuzonSugerencia clase, HttpServletRequest request) {
		 return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "BuzonSugerencia");
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

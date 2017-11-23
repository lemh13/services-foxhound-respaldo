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
import fox.hound.spring.models.combo.OpcionPregunta;
import fox.hound.spring.models.maestros.Opcion;
import fox.hound.spring.models.maestros.Pregunta;
import fox.hound.spring.services.OpcionPreguntaService;
import fox.hound.spring.services.OpcionService;
import fox.hound.spring.services.PreguntaService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("opcionpregunta")
public class OpcionPreguntaController {

	 @Autowired
	 private OpcionPreguntaService service;
	 
	 @Autowired
	 private OpcionService opcionservice;
	 
	 @Autowired
	 private PreguntaService preguntaservice;

	 private Class<?> CLASE = OpcionPregunta.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="opcion/{id}/pregunta/{id_p}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@RequestBody OpcionPregunta clase, @PathVariable String id, @PathVariable String id_p, HttpServletRequest request) {
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 Opcion opcion = opcionservice.getOne(Long.valueOf(id));
		 Pregunta pregunta = preguntaservice.getOne(Long.valueOf(id_p));
			
			if (opcion != null && pregunta!=null) 
			{
				clase.setOpcion(opcion);
				clase.setPregunta(pregunta);
				return ResponseDefault.messageAndObject(MessageUtil.GUARDAR_REGISTRO, "Opcion Pregunta", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
			} 
			else if (opcion==null)
			{
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Opcion");
			}
			else 
			{
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "pregunta");
			}
			
			
			
	 }

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody OpcionPregunta clase, HttpServletRequest request) {
		 return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "OpcionPregunta");
	 }

}

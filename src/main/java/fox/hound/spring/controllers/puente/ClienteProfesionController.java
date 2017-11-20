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

import fox.hound.spring.models.Cliente;
import fox.hound.spring.models.maestros.Profesion;
import fox.hound.spring.models.puente.ClienteProfesion;
import fox.hound.spring.services.ClienteProfesionService;
import fox.hound.spring.services.PersonaService;
import fox.hound.spring.services.ProfesionService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("clienteprofesion")
public class ClienteProfesionController {

	 @Autowired
	 private ClienteProfesionService service;
	 @Autowired
	 private PersonaService personaService;
	 @Autowired
	 private ProfesionService profesionService;

	 private Class<?> CLASE = ClienteProfesion.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="cliente/{id_c}/profesion/{id_p}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@PathVariable String id_c, @PathVariable String id_p, HttpServletRequest request) {
		 ClienteProfesion clase = new ClienteProfesion();
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 Cliente cliente = (Cliente) personaService.getOne(Long.valueOf(id_c));
		 Profesion profesion = profesionService.getOne(Long.valueOf(id_p));

		 if(cliente != null && profesion != null) {
			clase.setCliente(cliente);
			clase.setProfesion(profesion);
			 
			return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
		 } else if(cliente==null) {
			return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Cliente");
		 } else {
			return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Profesion");
		 }
	}

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody ClienteProfesion clase, HttpServletRequest request) {
		 return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "ClienteProfesion");
	 }

}

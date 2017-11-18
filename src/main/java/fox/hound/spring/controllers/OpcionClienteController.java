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

import fox.hound.spring.models.Cliente;
import fox.hound.spring.models.OpcionCliente;
import fox.hound.spring.models.maestros.Opcion;
import fox.hound.spring.services.*;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("opcioncliente")
public class OpcionClienteController {

	 @Autowired
	 private OpcionClienteService service;
	 private PersonaService personaService;
	 private OpcionService opcionService;
	 

	 private Class<?> CLASE = OpcionCliente.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="cliente/{id_c}/opcion/{id_o}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@RequestBody OpcionCliente clase, @PathVariable String id_c,@PathVariable String id_o, HttpServletRequest request) {
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 Cliente cliente = (Cliente) personaService.getOne(Long.valueOf(id_c));
		 Opcion opcion = opcionService.getOne(Long.valueOf(id_o));
		 
		 if(clase!= null) {
			 clase.setCliente(cliente);
			 if(opcion != null) {
				 clase.setOpcion(opcion);
				 return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
			 }else {
					return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Opcion");
			 }
		 }else{
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Cliente");
		 }
	 }

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody OpcionCliente clase, HttpServletRequest request) {
		 return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "OpcionCliente");
	 }

}

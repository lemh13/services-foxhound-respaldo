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
import fox.hound.spring.models.puente.PreferenciaCliente;
import fox.hound.spring.models.puente.TipoCaracteristicaInmueble;
import fox.hound.spring.services.PersonaService;
import fox.hound.spring.services.PreferenciaClienteService;
import fox.hound.spring.services.TipoCaracteristicaInmuebleService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("preferenciacliente")
public class PreferenciaClienteController {

	 @Autowired
	 private PreferenciaClienteService service;
	 
	 @Autowired
	 private TipoCaracteristicaInmuebleService tipoCaracteristicaInmuebleService;
	 
	 @Autowired
	 private PersonaService clienteService;

	 private Class<?> CLASE = PreferenciaCliente.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/tipoCaracteristicaInmueble/{tipoCaracteristicaInmuebleid}/cliente/{clienteid}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@PathVariable String clienteid, @PathVariable String tipoCaracteristicaInmuebleid, HttpServletRequest request) {
		 PreferenciaCliente clase = new PreferenciaCliente();
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 TipoCaracteristicaInmueble tipoCaracteristicaInmueble = tipoCaracteristicaInmuebleService.getOne(Long.valueOf(tipoCaracteristicaInmuebleid));
		 Cliente cliente = (Cliente) clienteService.getOne(Long.valueOf(clienteid));
		 
		 if (cliente != null && tipoCaracteristicaInmueble != null) {
				clase.setCliente(cliente);
				clase.setTipoCaracteristicaInmueble(tipoCaracteristicaInmueble);
				return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
			} else {
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Preferencia Cliente");
			}
	 }

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody PreferenciaCliente clase, HttpServletRequest request) {
		 return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "PreferenciaCliente");
	 }

}

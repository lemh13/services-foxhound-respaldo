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
import fox.hound.spring.models.maestros.Categoria;
import fox.hound.spring.models.maestros.TipoServicio;
import fox.hound.spring.models.puente.PreferenciaCliente;
import fox.hound.spring.models.maestros.TipoCaracteristica;
import fox.hound.spring.services.CategoriaService;
import fox.hound.spring.services.PersonaService;
import fox.hound.spring.services.PreferenciaClienteService;
import fox.hound.spring.services.TipoCaracteristicaService;
import fox.hound.spring.services.TipoServicioService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("preferenciacliente")
public class PreferenciaClienteController {

	 @Autowired
	 private PreferenciaClienteService service;
	 
	 @Autowired
	 private CategoriaService categoriaService;
	 
	 @Autowired
	 private TipoServicioService tipoServicioService;
	 
	 @Autowired
	 private TipoCaracteristicaService tipoCaracteristicaService;
	 
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

	 @RequestMapping(value="/categoria/{categoriaid}/tipoServicio/{tipoServicioid}/tipoCaracteristica/{tipoCaracteristicaid}/cliente/{clienteid}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@PathVariable String clienteid, @PathVariable String tipoCaracteristicaid, 
			 @PathVariable String tipoServicioid, @PathVariable String categoriaid, HttpServletRequest request) {
		 
		 PreferenciaCliente clase = new PreferenciaCliente();
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 
		 TipoCaracteristica tipoCaracteristica = tipoCaracteristicaService.getOne(Long.valueOf(tipoCaracteristicaid));
		 Cliente cliente = (Cliente) clienteService.getOne(Long.valueOf(clienteid));
		 Categoria categoria = categoriaService.getOne(Long.valueOf(categoriaid));
		 TipoServicio tipoServicio = tipoServicioService.getOne(Long.valueOf(tipoServicioid));
		 
		 if (cliente != null && tipoCaracteristica != null || categoria != null || tipoServicio != null) {
				clase.setCliente(cliente);
				clase.setTipoCaracteristica(tipoCaracteristica);
				clase.setCategoria(categoria);
				clase.setTipoServicio(tipoServicio);
				
				return ResponseDefault.messageAndObject(MessageUtil.GUARDAR_REGISTRO, "Preferencia Cliente", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
		 } else if (cliente == null) {
			 return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Cliente");		
		 } else if (tipoCaracteristica == null) {
			 return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Tipo Categoria");
		 } else {
			 return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Otras");		
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

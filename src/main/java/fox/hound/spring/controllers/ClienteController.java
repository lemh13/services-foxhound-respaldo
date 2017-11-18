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
import fox.hound.spring.models.combo.Rol;
import fox.hound.spring.models.combo.Sector;
import fox.hound.spring.models.maestros.Estado;
import fox.hound.spring.models.maestros.TipoCliente;
import fox.hound.spring.services.PersonaService;
import fox.hound.spring.services.RolService;
import fox.hound.spring.services.SectorService;
import fox.hound.spring.services.TipoClienteService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("cliente")
public class ClienteController {

	 @Autowired
	 private PersonaService service;

	 @Autowired
	 private SectorService sectorService;
	 
	 @Autowired
	 private RolService rolService;
	 
	 @Autowired
	 private TipoClienteService tipoClienteService;
	 
	 private Class<?> CLASE = Cliente.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll("Cliente"), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="sector/{sectorId}/rol/{rolId}/tipoCliente/{id}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@RequestBody Cliente clase,@PathVariable String sectorId, @PathVariable String rolId, @PathVariable String id, HttpServletRequest request) {
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 TipoCliente tipoCliente = tipoClienteService.getOne(Long.valueOf(id));
		 Sector sector = sectorService.getOne(Long.valueOf(sectorId));
		 Rol rol = rolService.getOne(Long.valueOf(rolId));
			
			if (sector != null && rol != null && tipoCliente != null) {
				clase.setTipoCliente(tipoCliente);
				return ResponseDefault.messageAndObject(MessageUtil.GUARDAR_REGISTRO, "Cliente", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
			} else if (sector == null) {
				 return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Sector");
			 } else if (rol == null) {
				 return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Rol");
			} else {
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Tipo Cliente");
			}
	 }

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody Cliente clase, HttpServletRequest request) {
		 return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "Cliente");
	 }

}

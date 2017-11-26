package fox.hound.spring.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import fox.hound.spring.models.Persona;
import fox.hound.spring.models.Usuario;
import fox.hound.spring.models.combo.Rol;
import fox.hound.spring.models.combo.Sector;
import fox.hound.spring.print.PersonaGlobal;
import fox.hound.spring.security.TokenUtil;
import fox.hound.spring.services.PersonaService;
import fox.hound.spring.services.RolService;
import fox.hound.spring.services.SectorService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
	private final Logger logger = Logger.getLogger(this.getClass());
	 @Autowired
	 private PersonaService service;
	 @Value("${foxhound.token.header}")
	 private String tokenHeader;
	 @Autowired
	 private TokenUtil tokenUtils;
	 @Autowired
	 private SectorService sectorService;
	 @Autowired
	 private RolService rolService;
	 
	 private Class<?> CLASE = Persona.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll("Usuario"), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="sector/{sectorId}/rol/{rolId}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@RequestBody Usuario clase, @PathVariable String sectorId, @PathVariable String rolId, HttpServletRequest request) {
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 Sector sector = sectorService.getOne(Long.valueOf(sectorId));
		 Rol rol = rolService.getOne(Long.valueOf(rolId));
		 
		 if (sector != null && rol != null) {
			clase.setSector(sector);
			clase.setRol(rol);
			
			return ResponseDefault.messageAndObject(MessageUtil.GUARDAR_REGISTRO, "Usuario", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
		 } else if (sector == null) {
			 return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Sector");
		 } else {
			 return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Rol");
		 }
	 }

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody Usuario clase, HttpServletRequest request) {
			return ResponseDefault.messageAndObject(MessageUtil.ACTUALIZAR_REGISTRO, "Usuario", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "Persona");
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
	 @RequestMapping(value="/buscarClienteLoggeado", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getLogged(@PathVariable String id, HttpServletRequest request) {
		 PersonaGlobal p = tokenUtils.getUserFromToken(request.getHeader(tokenHeader));
		 logger.info(p.getId());
		 
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }
}

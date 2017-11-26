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
import fox.hound.spring.models.*;
import fox.hound.spring.models.combo.Sector;
import fox.hound.spring.models.maestros.*;
import fox.hound.spring.print.PersonaGlobal;
import fox.hound.spring.security.TokenUtil;
import fox.hound.spring.services.*;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("inmueble")
public class InmuebleController {
	
    private final Logger logger = Logger.getLogger(this.getClass());

	 @Value("${foxhound.token.header}")
	 private String tokenHeader;
	 @Autowired
	 private TokenUtil tokenUtils;
	
	 @Autowired
	 private InmuebleService service;
	 @Autowired
	 private TipoInmuebleService tipoInmuebleService;
	 @Autowired
	 private UsoInmuebleService usoInmuebleService;
	 @Autowired
	 private SectorService setorService;
	 @Autowired
	 private PersonaService personaService;
	 	 
	 private Class<?> CLASE = Inmueble.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscarTodosCliente", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAllCliente(HttpServletRequest request) {
		 PersonaGlobal p = tokenUtils.getUserFromToken(request.getHeader(tokenHeader));
		 logger.info(p.getId());
		 return ResponseDefault.ok(service.getAllByClienteId(p.getId()), CLASE, ResponseDefault.PLURAL);
	 }
	 
	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="tipoInmueble/{id_t}/usoInmueble/{id_u}/sector/{id_s}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@RequestBody Inmueble clase, @PathVariable String id_t,@PathVariable String id_u, @PathVariable String id_s, HttpServletRequest request) {
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 TipoInmueble tipoInmueble = tipoInmuebleService.getOne(Long.valueOf(id_t));
		 UsoInmueble usoInmueble = usoInmuebleService.getOne(Long.valueOf(id_u));
		 Sector sector = setorService.getOne(Long.valueOf(id_s));
		 
		 PersonaGlobal p = tokenUtils.getUserFromToken(request.getHeader(tokenHeader));
		 logger.info(p.getId());
		 Cliente cliente = (Cliente) personaService.getOne(p.getId());
		 
		 if(cliente !=null) {
			 clase.setCliente(cliente);
			 if(tipoInmueble!= null) {
				 clase.setTipoInmueble(tipoInmueble);
				 if(usoInmueble != null ) {
					 clase.setUsoInmueble(usoInmueble);
					 if(sector != null) {
						 clase.setSector(sector);
							return ResponseDefault.messageAndObject(MessageUtil.GUARDAR_REGISTRO, "Inmueble", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
					 }else {
						return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Sector");
					 }
				  }else {
					return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Uso Inmueble");
			      }
			 }else {
					return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Tipo Inmueble");
				 }
		 }else{
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Persona_cliente");
		 }
	 }

	 @RequestMapping(value="tipoInmueble/{id_t}/uso/{id_u}/sector/{id_s}/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody Inmueble clase,  @PathVariable String id_t,@PathVariable String id_u, @PathVariable String id_s, HttpServletRequest request) {
		 TipoInmueble tipoInmueble = tipoInmuebleService.getOne(Long.valueOf(id_t));
		 UsoInmueble usoInmueble = usoInmuebleService.getOne(Long.valueOf(id_u));
		 Sector sector = setorService.getOne(Long.valueOf(id_s));
		 
		 PersonaGlobal p = tokenUtils.getUserFromToken(request.getHeader(tokenHeader));
		 logger.info(p.getId());
		 Cliente cliente = (Cliente) personaService.getOne(p.getId());
		 
		 if(cliente !=null) {
			 clase.setCliente(cliente);
			 if(tipoInmueble!= null) {
				 clase.setTipoInmueble(tipoInmueble);
				 if(usoInmueble != null ) {
					 clase.setUsoInmueble(usoInmueble);
					 if(sector != null) {
						 clase.setSector(sector);
							return ResponseDefault.messageAndObject(MessageUtil.ACTUALIZAR_REGISTRO, "Inmueble", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
					 }else {
						return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Sector");
					 }
				  }else {
					return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Uso Inmueble");
			      }
			 }else {
					return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Tipo Inmueble");
				 }
		 }else{
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Persona_cliente");
		 }	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "Inmueble");
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

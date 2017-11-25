package fox.hound.spring.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fox.hound.spring.models.Empresa;
import fox.hound.spring.models.Garantia;
import fox.hound.spring.models.Servicio;
import fox.hound.spring.models.maestros.TipoServicio;
import fox.hound.spring.models.maestros.UnidadMedida;
import fox.hound.spring.print.TribagoRequest;
import fox.hound.spring.services.EmpresaService;
import fox.hound.spring.services.GarantiaService;
import fox.hound.spring.services.ServicioService;
import fox.hound.spring.services.TipoServicioService;
import fox.hound.spring.services.UnidadMedidaService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("servicio")
public class ServicioController {
	
	private final Logger logger = Logger.getLogger(this.getClass());

	 @Autowired
	 private ServicioService service;
	 
	 @Autowired
	 private TipoServicioService tipoServicioService;
	 
	 @Autowired
	 private UnidadMedidaService unidadMedidaService;
	 
	 @Autowired
	 private EmpresaService empresaService;
	 
	 @Autowired
	 private GarantiaService garantiaService;

	 private Class<?> CLASE = Servicio.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	 }
	 
	 @RequestMapping(value="/buscarActivos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAllActivos(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAllActive(), CLASE, ResponseDefault.PLURAL);
	 }
	 
	 @RequestMapping(value="/buscarUltimas", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getUltimas(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAllUltimas(), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="garantia/{id_g}/tipoServicio/{id_ts}/unidadMedida/{id_um}/empresa/{id_e}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@RequestBody Servicio clase, @PathVariable String id_g, @PathVariable String id_ts, @PathVariable String id_c, @PathVariable String id_um, @PathVariable String id_e,
			 HttpServletRequest request) {
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 
		 Garantia garantia = garantiaService.getOne(Long.valueOf(id_g));
		 TipoServicio tipoServicio = tipoServicioService.getOne(Long.valueOf(id_ts));
		 UnidadMedida unidadMedida = unidadMedidaService.getOne(Long.valueOf(id_um));
		 Empresa empresa = empresaService.getOne(Long.valueOf(id_e));
		 
		 if(tipoServicio != null && unidadMedida != null && empresa !=null && garantia != null) {
			 clase.setTipoServicio(tipoServicio);
			 clase.setUnidadMedida(unidadMedida);
			 clase.setEmpresa(empresa);
			 return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
		 } else if(tipoServicio==null) {
			  return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "tipoServicio");
		 } else if(unidadMedida==null) {
			  return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "unidadMedida");
		 } else if (garantia == null) {
			  return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Garantia");			 
		 }else {
			 return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "empresa");
		 }
		
	 }

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody Servicio clase, HttpServletRequest request) {
			return ResponseDefault.messageAndObject(MessageUtil.ACTUALIZAR_REGISTRO, "Servicio", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "Servicio");
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
	 
	 @RequestMapping(value="/tribago", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> activeDesactiveEstatus(@RequestBody TribagoRequest clase, HttpServletRequest request) {
		 
		 logger.info(clase.getTipoCategoria() + " " + clase.getTipoInmueble() + " " + clase.getTipoServicio());
		 
		 // Todos los Servicios Activos
		 List<Servicio> servicios = service.getAllActive();
		 List<Servicio> serviciosAux = new ArrayList<>();
		 logger.info("servicios:" + servicios.size());
		 
		 if (clase.getTipoInmueble() != null) {
			 for (Servicio s : service.getAllTipoInmueble(clase.getTipoInmueble())) {
				 logger.info(s.getTitulo() + " is in " + servicios.contains(s));
				 
				 if (servicios.contains(s)) {
					 logger.info("agregar");
					 serviciosAux.add(s);
				 }
			 }
			 servicios = serviciosAux;
			 logger.info("Tipo Inmueble servicios:" + servicios.size());
			 serviciosAux = new ArrayList<>();
			 logger.info("Tipo Inmueble servicios:" + servicios.size());
		 }
		 if (clase.getTipoCategoria() != null) {
			 for (Servicio s : service.getAllCategoria(clase.getTipoCategoria())) {
				 logger.info(s.getTitulo() + " is in " + servicios.contains(s));
				 
				 if (servicios.contains(s)) {
					 serviciosAux.add(s);
				 }
			 }
			 servicios = serviciosAux;
			 serviciosAux = new ArrayList<>();
			 logger.info("Categoria servicios:" + servicios.size());
		 }
		 if (clase.getTipoServicio() != null) {			 
			 for (Servicio s : service.getAllTipoServicio(clase.getTipoServicio())) {
				 logger.info(s.getTitulo() + " is in " + servicios.contains(s));
				 
				 if (servicios.contains(s)) {
					 logger.info("agregar");
					 serviciosAux.add(s);
				 }
			 }
			 servicios = serviciosAux;
			 logger.info("Tipo Servicio servicios:" + servicios.size());
			 serviciosAux = new ArrayList<>();
			 logger.info("Tipo Servicio servicios:" + servicios.size());
		 }
		 
		 return ResponseDefault.ok(servicios, CLASE, ResponseDefault.PLURAL);
	 }

}

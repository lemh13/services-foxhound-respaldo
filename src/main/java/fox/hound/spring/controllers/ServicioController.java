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

import fox.hound.spring.models.Empresa;
import fox.hound.spring.models.Servicio;
import fox.hound.spring.models.combo.Garantia;
import fox.hound.spring.models.maestros.Categoria;
import fox.hound.spring.models.maestros.TipoServicio;
import fox.hound.spring.models.maestros.UnidadMedida;
import fox.hound.spring.services.CategoriaService;
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

	 @Autowired
	 private ServicioService service;
	 
	 @Autowired
	 private TipoServicioService tipoServicioService;
	 
	 @Autowired
	 private CategoriaService categoriaService;
	 
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

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="garantia/{id_g}/tipoServicio/{id_ts}/categoria/{id_c}/unidadMedida/{id_um}/empresa/{id_e}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@RequestBody Servicio clase, @PathVariable String id_g, @PathVariable String id_ts, @PathVariable String id_c, @PathVariable String id_um, @PathVariable String id_e,
			 HttpServletRequest request) {
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 
		 Garantia garantia = garantiaService.getOne(Long.valueOf(id_g));
		 TipoServicio tipoServicio = tipoServicioService.getOne(Long.valueOf(id_ts));
		 Categoria categoria = categoriaService.getOne(Long.valueOf(id_c));
		 UnidadMedida unidadMedida = unidadMedidaService.getOne(Long.valueOf(id_um));
		 Empresa empresa = empresaService.getOne(Long.valueOf(id_e));
		 
		 if(tipoServicio != null && categoria != null && unidadMedida != null && empresa !=null && garantia != null) {
			 clase.setTipoServicio(tipoServicio);
			 clase.setCategoria(categoria);
			 clase.setUnidadMedida(unidadMedida);
			 clase.setEmpresa(empresa);
			 return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
		 } else if(tipoServicio==null) {
			  return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "tipoServicio");
		 } else if(categoria==null) {
			  return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "categoria");
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
		 return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "Servicio");
	 }

}
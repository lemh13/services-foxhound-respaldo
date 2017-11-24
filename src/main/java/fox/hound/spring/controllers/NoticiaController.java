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
import fox.hound.spring.models.Noticia;
import fox.hound.spring.services.EmpresaService;
import fox.hound.spring.services.NoticiaService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("noticia")
public class NoticiaController {

	 @Autowired
	 private NoticiaService service;
	 @Autowired
	 private EmpresaService empresaService;

	 private Class<?> CLASE = Noticia.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	 }
	 
	 @RequestMapping(value="/buscarActivos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getActivos(HttpServletRequest request) {
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

	 @RequestMapping(value="empresa/{id}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@RequestBody Noticia clase, @PathVariable String id, HttpServletRequest request) {
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 Empresa empresa = empresaService.getOne(Long.valueOf(id));
		 clase.setEmpresa(empresa);
		 return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody Noticia clase, HttpServletRequest request) {
		 Empresa empresa = empresaService.getOne(Long.valueOf(1));
		 clase.setEmpresa(empresa);
			return ResponseDefault.messageAndObject(MessageUtil.ACTUALIZAR_REGISTRO, "Noticia", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "Noticia");
	 }
	 
	 @RequestMapping(value="/borrarLogico/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrarLogico(@PathVariable String id, HttpServletRequest request) {
		 service.deleteLogic(id);
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "Noticia");
	 }
	 
	 @RequestMapping(value="/activeDesactiveEstatus/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> activeDesactiveEstatus(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.messageAndObject(MessageUtil.ACTUALIZAR_REGISTRO, "Noticia", service.activeDesactiveEstatus(id), CLASE, ResponseDefault.SINGULAR);
	 }

}

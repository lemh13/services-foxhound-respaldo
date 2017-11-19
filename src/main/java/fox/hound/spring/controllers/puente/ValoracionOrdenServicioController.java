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

import fox.hound.spring.models.OrdenServicio;
import fox.hound.spring.models.combo.OrdenEntrega;
import fox.hound.spring.models.puente.ValoracionOrdenServicio;
import fox.hound.spring.services.OrdenEntregaService;
import fox.hound.spring.services.OrdenServicioService;
import fox.hound.spring.services.ValoracionOrdenServicioService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("valoracionordenservicio")
public class ValoracionOrdenServicioController {

	 @Autowired
	 private ValoracionOrdenServicioService service;
	 
	 @Autowired
	 private OrdenEntregaService ordenEntregaService;
	 
	 @Autowired
	 private OrdenServicioService ordenServicioService;

	 private Class<?> CLASE = ValoracionOrdenServicio.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/ordenEntrega/{ordenEntregaid}/ordenServicio/{ordenServicioid}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@RequestBody ValoracionOrdenServicio clase, @PathVariable String ordenEntregaid, @PathVariable String ordenServicioid, HttpServletRequest request) {
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 OrdenEntrega ordenEntrega = ordenEntregaService.getOne(Long.valueOf(ordenEntregaid));
		 OrdenServicio ordenServicio = ordenServicioService.getOne(Long.valueOf(ordenServicioid));
		 
		 if (ordenEntrega != null && ordenServicio != null) {
				clase.setOrdenEntrega(ordenEntrega);
				clase.setOrdenServicio(ordenServicio);
				return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
			} else {
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Valoracion Orden Servicio");
			}
	 }

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody ValoracionOrdenServicio clase, HttpServletRequest request) {
		 return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "ValoracionOrdenServicio");
	 }

}

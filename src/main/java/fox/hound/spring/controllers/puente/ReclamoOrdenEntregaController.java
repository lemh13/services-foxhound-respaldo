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

import fox.hound.spring.models.maestros.TipoReclamo;
import fox.hound.spring.models.puente.MotivoReclamo;
import fox.hound.spring.models.puente.ReclamoOrdenEntrega;
import fox.hound.spring.services.ReclamoOrdenEntregaService;
import fox.hound.spring.services.MotivoReclamoService;
import fox.hound.spring.services.TipoReclamoService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("reclamoordenentrega")
public class ReclamoOrdenEntregaController {

	 @Autowired
	 private ReclamoOrdenEntregaService service;
	 
	 @Autowired
	 private TipoReclamoService tipoReclamoService;
	 
	 @Autowired
	 private MotivoReclamoService motivoReclamoService;

	 private Class<?> CLASE = ReclamoOrdenEntrega.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/tipoReclamo/{tipoReclamoid}/motivoReclamo/{motivoReclamoid}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@PathVariable String tipoReclamoid, @PathVariable String motivoReclamoid, HttpServletRequest request) {
		 ReclamoOrdenEntrega clase = new ReclamoOrdenEntrega();
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 TipoReclamo tipoReclamo = tipoReclamoService.getOne(Long.valueOf(tipoReclamoid));
		 MotivoReclamo motivoReclamo = motivoReclamoService.getOne(Long.valueOf(motivoReclamoid));
		 //Creo que falta la orden de entrega
		 if (tipoReclamo != null && motivoReclamo != null) {
				clase.setTipoReclamo(tipoReclamo);
				clase.setMotivoReclamo(motivoReclamo);
				return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
			} else {
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Reclamo Orden de Entrega");
			}
		 
	 }

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody ReclamoOrdenEntrega clase, HttpServletRequest request) {
		 return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "ReclamoOrdenEntrega");
	 }

}

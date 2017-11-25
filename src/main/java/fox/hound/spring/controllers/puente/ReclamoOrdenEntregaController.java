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

import fox.hound.spring.models.OrdenEntrega;
import fox.hound.spring.models.maestros.TipoReclamo;
import fox.hound.spring.models.puente.MotivoReclamo;
import fox.hound.spring.models.puente.ReclamoOrdenEntrega;
import fox.hound.spring.services.ReclamoOrdenEntregaService;
import fox.hound.spring.services.MotivoReclamoService;
import fox.hound.spring.services.OrdenEntregaService;
import fox.hound.spring.services.ReclamoOrdenEntregaService;
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
	 private OrdenEntregaService ordenentregaService;
	 
	 @Autowired
	 private TipoReclamoService tiporeclamoService;
	 
	 @Autowired
	 private MotivoReclamoService motivoreclamoService;

	 private Class<?> CLASE = ReclamoOrdenEntrega.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="ordenentrega/{id}/tipoReclamo/{id_t}/motivoReclamo/{id_m}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@RequestBody ReclamoOrdenEntrega clase, @PathVariable String id,  @PathVariable String id_t,  @PathVariable String id_m, HttpServletRequest request) {
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 OrdenEntrega ordenentrega = ordenentregaService.getOne(Long.valueOf(id));
		 TipoReclamo tiporeclamo = tiporeclamoService.getOne(Long.valueOf(id_t));
		 MotivoReclamo motivoreclamo = motivoreclamoService.getOne(Long.valueOf(id_m));
			
			if (ordenentrega != null && tiporeclamo != null && motivoreclamo !=null) {
				clase.setOrdenEntrega(ordenentrega);
				clase.setTipoReclamo(tiporeclamo);
				clase.setMotivoReclamo(motivoreclamo);
				
				return ResponseDefault.messageAndObject(MessageUtil.GUARDAR_REGISTRO, "Reclamo Orden Entrega", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
			} else if (ordenentrega == null )
			{
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Orden de Entrega");
			}else if (tiporeclamo == null ){
					return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Tipo de Reclamo");
			}else{
			 return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Motivo de Reclamo");
			}
	 }

	 @RequestMapping(value="ordenentrega/{id}/tipoReclamo/{id_t}/motivoReclamo/{id_m}/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody ReclamoOrdenEntrega clase, @PathVariable String id,  @PathVariable String id_t,  @PathVariable String id_m, HttpServletRequest request) {
		 OrdenEntrega ordenentrega = ordenentregaService.getOne(Long.valueOf(id));
		 TipoReclamo tiporeclamo = tiporeclamoService.getOne(Long.valueOf(id_t));
		 MotivoReclamo motivoreclamo = motivoreclamoService.getOne(Long.valueOf(id_m));
			
			if (ordenentrega != null && tiporeclamo != null && motivoreclamo !=null) {
				clase.setOrdenEntrega(ordenentrega);
				clase.setTipoReclamo(tiporeclamo);
				clase.setMotivoReclamo(motivoreclamo);
				
				return ResponseDefault.messageAndObject(MessageUtil.ACTUALIZAR_REGISTRO, "Reclamo Orden Entrega", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
			} else if (ordenentrega == null )
			{
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Orden de Entrega");
			}else if (tiporeclamo == null ){
					return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Tipo de Reclamo");
			}else{
			 return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Motivo de Reclamo");
			}	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "ReclamoOrdenEntrega");
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

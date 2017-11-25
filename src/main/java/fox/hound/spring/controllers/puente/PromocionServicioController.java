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

import fox.hound.spring.models.Servicio;
import fox.hound.spring.models.puente.Promocion;
import fox.hound.spring.models.puente.PromocionServicio;
import fox.hound.spring.services.PromocionService;
import fox.hound.spring.services.PromocionServicioService;
import fox.hound.spring.services.ServicioService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("promocionServicio")
public class PromocionServicioController {

	 @Autowired
	 private PromocionServicioService service;
	 
	 @Autowired
	 private PromocionService promocionService; 
	 
	 @Autowired
	 private ServicioService servicioService;

	 private Class<?> CLASE = PromocionServicio.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/promocion/{promocionId}/servicio/{servicioId}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar( @PathVariable String promocionId, @PathVariable String servicioId, HttpServletRequest request) {
		PromocionServicio clase = new PromocionServicio();
		clase.setFecha_creacion( DateUtil.getCurrentDate() );
		Promocion promocion = promocionService.getOne(Long.valueOf(promocionId));
		Servicio servicio = servicioService.getOne(Long.valueOf(servicioId));

		if (promocion != null && servicio != null) {
			clase.setPromocion(promocion);
			clase.setServicio(servicio);
			return ResponseDefault.messageAndObject(MessageUtil.GUARDAR_REGISTRO, "Promoción", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
		} else if (servicio == null) {
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Servicio");			 
		} else {
			return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Promocion");
		}	 
	}

	 @RequestMapping(value="/promocion/{promocionId}/servicio/{servicioId}/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@PathVariable String promocionId, @PathVariable String servicioId, HttpServletRequest request) {
		 PromocionServicio clase = new PromocionServicio();
			clase.setFecha_creacion( DateUtil.getCurrentDate() );
			Promocion promocion = promocionService.getOne(Long.valueOf(promocionId));
			Servicio servicio = servicioService.getOne(Long.valueOf(servicioId));

			if (promocion != null && servicio != null) {
				clase.setPromocion(promocion);
				clase.setServicio(servicio);
				return ResponseDefault.messageAndObject(MessageUtil.GUARDAR_REGISTRO, "Promoción", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
			} else if (servicio == null) {
					return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Servicio");			 
			} else {
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Promocion");
			}		 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "Promocion");
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
	 
	 @RequestMapping(value="/servicio/{id}/buscarPorServicio", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ResponseEntity<?> buscarPorServicio(@PathVariable String id, HttpServletRequest request) {
			return ResponseDefault.ok(service.getPromocionPorServicio(id), CLASE, ResponseDefault.SINGULAR); 
		}

}

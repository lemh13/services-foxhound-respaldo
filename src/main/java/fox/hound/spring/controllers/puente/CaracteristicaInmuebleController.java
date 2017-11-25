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

import fox.hound.spring.models.Inmueble;
import fox.hound.spring.models.combo.Caracteristica;
import fox.hound.spring.models.maestros.Ubicacion;
import fox.hound.spring.models.puente.CaracteristicaInmueble;
import fox.hound.spring.services.CaracteristicaInmuebleService;
import fox.hound.spring.services.CaracteristicaService;
import fox.hound.spring.services.InmuebleService;
import fox.hound.spring.services.UbicacionService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("caracteristicainmueble")
public class CaracteristicaInmuebleController {

	 @Autowired
	 private CaracteristicaInmuebleService service;
	 @Autowired
	 private InmuebleService inmuebleService;
	 @Autowired
	 private CaracteristicaService caracteristicaService;
	 @Autowired
	 private UbicacionService ubicacionService;

	 private Class<?> CLASE = CaracteristicaInmueble.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="inmueble/{id_i}/caracteristica/{id_c}/ubicacion/{id_u}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@PathVariable String id_i, @PathVariable String id_c, @PathVariable String id_u, HttpServletRequest request) {
		 CaracteristicaInmueble clase = new CaracteristicaInmueble();
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 Inmueble inmueble = inmuebleService.getOne(Long.valueOf(id_i));
		 Caracteristica caracteristica = caracteristicaService.getOne(Long.valueOf(id_c));
		 Ubicacion ubicacion = ubicacionService.getOne(Long.valueOf(id_u));
			
		 if (inmueble != null && caracteristica != null && ubicacion != null) {
				clase.setInmueble(inmueble);
				clase.setCaracteristica(caracteristica);
				clase.setUbicacion(ubicacion);
				
			 	 
			 return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
		} else if (inmueble == null){
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Inmueble");
		} else if (ubicacion == null){
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Ubicacion");
		}else {
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Caracteristica");
		}
	 }

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody CaracteristicaInmueble clase, HttpServletRequest request) {
		 return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "CaracteristicaInmueble");
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

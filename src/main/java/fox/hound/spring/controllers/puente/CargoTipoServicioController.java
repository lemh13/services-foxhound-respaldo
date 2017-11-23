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

import fox.hound.spring.models.maestros.Cargo;
import fox.hound.spring.models.maestros.TipoServicio;
import fox.hound.spring.models.puente.CargoTipoServicio;
import fox.hound.spring.services.CargoService;
import fox.hound.spring.services.CargoTipoServicioService;
import fox.hound.spring.services.TipoServicioService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("cargotiposervicio")
public class CargoTipoServicioController {

	 @Autowired
	 private CargoTipoServicioService service;
	 @Autowired
	 private CargoService cargoService;
	 @Autowired
	 private TipoServicioService tipoServicioService;

	 private Class<?> CLASE = CargoTipoServicio.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="cargo/{id_c}/tipoServicio/{id_t}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar( @PathVariable String id_t, @PathVariable String id_c, HttpServletRequest request) {
		 CargoTipoServicio clase = new CargoTipoServicio();
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 Cargo cargo = cargoService.getOne(Long.valueOf(id_c));
		 TipoServicio tipoServicio = tipoServicioService.getOne(Long.valueOf(id_t));
		 
		 if (cargo != null && tipoServicio != null) {
			 clase.setCargo(cargo);
			 clase.setTipoServicio(tipoServicio);
			 return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
		 } else if (cargo == null){
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Cargo");
			}else {
				return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "TipoServicio");

			}
	 }

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody CargoTipoServicio clase, HttpServletRequest request) {
		 return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "CargoTipoServicio");
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

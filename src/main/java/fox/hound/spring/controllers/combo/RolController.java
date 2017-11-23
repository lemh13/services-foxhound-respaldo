package fox.hound.spring.controllers.combo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import fox.hound.spring.models.combo.Rol;
import fox.hound.spring.print.RolPrint;
import fox.hound.spring.services.RolService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("rol")
public class RolController {
	
	private final Logger logger = Logger.getLogger(this.getClass());

	 @Autowired
	 private RolService service;

	 private Class<?> CLASE = Rol.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 List<Rol> roles = service.getAll(); 
		 return ResponseDefault.ok(buscarMenusId(roles), CLASE, ResponseDefault.PLURAL);
	 }

	@RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 
		 return ResponseDefault.ok(getMenuToList(service.getOne(Long.valueOf(id))), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@RequestBody Rol clase, HttpServletRequest request) {
		clase.setFecha_creacion( DateUtil.getCurrentDate() );
		clase.setMenu("-" + clase.getMenu()); 
		return ResponseDefault.messageAndObject(MessageUtil.GUARDAR_REGISTRO, "Rol", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody Rol clase, HttpServletRequest request) {
		 return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "Rol");
	 }
	 
	 private List<RolPrint> buscarMenusId(List<Rol> roles) {
		 List<RolPrint> rol = new ArrayList<>();
		 for (Rol r : roles) {
		 	rol.add( getMenuToList(r) );
		 }
		return rol;
	}
	 
	 private RolPrint getMenuToList(Rol clase) {
		 List<Integer> menu = new ArrayList<>();
		 for (String m : clase.getMenu().substring(1).split("-")) {
			 menu.add(Integer.valueOf(m));
		 }
		 return new RolPrint(clase.getId(), clase.getPersonas(), menu, clase.getDescripcion(), clase.getFecha_creacion(), clase.getFecha_modificacion(), clase.getEstatus());
	 }

}

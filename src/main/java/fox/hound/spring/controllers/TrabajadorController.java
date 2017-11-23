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
import fox.hound.spring.models.Trabajador;
import fox.hound.spring.models.combo.Rol;
import fox.hound.spring.models.combo.Sector;
import fox.hound.spring.models.maestros.Cargo;
import fox.hound.spring.models.puente.DetalleOrdenServicio;
import fox.hound.spring.services.CargoService;
import fox.hound.spring.services.DetalleOrdenServicioService;
import fox.hound.spring.services.EmpresaService;
import fox.hound.spring.services.PersonaService;
import fox.hound.spring.services.RolService;
import fox.hound.spring.services.SectorService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("trabajador")
public class TrabajadorController {

	 @Autowired
	 private PersonaService service;

	 @Autowired
	 private SectorService sectorService;
	 
	 @Autowired
	 private RolService rolService;
	 
	 @Autowired
	 private CargoService cargoService;
	 
	 @Autowired
	 private EmpresaService empresaService;
	 
	 @Autowired
	 private DetalleOrdenServicioService detalleOrdenServicioService;
	 
	 private Class<?> CLASE = Trabajador.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 return ResponseDefault.ok(service.getAll("Trabajador"), CLASE, ResponseDefault.PLURAL);
	 }

	 @RequestMapping(value="/buscar/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {
		 return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="sector/{sectorId}/rol/{rolId}/cargo/{cargoId}/empresa/{empresaId}/detalleOrdenServicio/{detalleOrdenServicioId}/agregar", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> agregar(@RequestBody Trabajador clase, @PathVariable String sectorId, @PathVariable String rolId, @PathVariable String cargoId, @PathVariable String empresaId, @PathVariable String detalleOrdenServicioId,  HttpServletRequest request) {
		 clase.setFecha_creacion( DateUtil.getCurrentDate() );
		 Cargo cargo = cargoService.getOne(Long.valueOf(cargoId));
		 Empresa empresa = empresaService.getOne(Long.valueOf(empresaId));
		 DetalleOrdenServicio detalleOrdenServicio = detalleOrdenServicioService.getOne(Long.valueOf(detalleOrdenServicioId));
		 Sector sector = sectorService.getOne(Long.valueOf(sectorId));
		 Rol rol = rolService.getOne(Long.valueOf(rolId));
		 
		 if (sector != null && rol != null && cargo != null && detalleOrdenServicio != null && empresa != null) {
			 clase.setCargo(cargo);
			 clase.setEmpresa(empresa);
			 clase.setSector(sector);
			 //clase.setDetalleOrdenServicio(detalleOrdenServicio);
			 
			return ResponseDefault.messageAndObject(MessageUtil.GUARDAR_REGISTRO, "Trabajador", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
		 } else if (cargo == null) {
			 return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Cargo");
		 } else if (empresa == null) {
			 return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Empresa");
		 } else if (sector == null) {
			 return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Sector");
		 } else if (rol == null) {
			 return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Rol");
		 } else {
			 return ResponseDefault.message(MessageUtil.ERROR_ASOCIACION, "Detalle Orden Servicio");
		 }
	 }

	 @RequestMapping(value="/modificar", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> modificar(@RequestBody Trabajador clase, HttpServletRequest request) {
			return ResponseDefault.messageAndObject(MessageUtil.ACTUALIZAR_REGISTRO, "Trabajador", service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);
	 }

	 @RequestMapping(value="/borrar/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {
		 service.delete(Long.valueOf(id));
		 return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, "Trabajador");
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

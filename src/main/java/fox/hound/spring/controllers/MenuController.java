package fox.hound.spring.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import fox.hound.spring.models.Menu;
import fox.hound.spring.utils.MessageUtil;
import fox.hound.spring.utils.ResponseDefault;
import java.util.stream.Collectors;

@RestController
@RequestMapping("menu")
public class MenuController {

	 private Class<?> CLASE = Menu.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 
		 List<Menu> menu = new ArrayList<>();
		 			 	 		
		menu.add(new Menu(1, "Gonfiguración General", "fa fa-plus", "none", configuracionGeneral()));
		menu.add(new Menu(100, "Servicios y Promociones", "fa fa-plus", "none", serviciosYPromociones()));
		menu.add(new Menu(200, "Visita", "fa fa-plus", "none", visita()));
		menu.add(new Menu(300, "Planificación", "fa fa-plus", "none", planificacion()));		 	
		menu.add(new Menu(400, "Ejecución", "fa fa-plus", "none", ejecucion()));
	 	
		menu.add(new Menu(500, "Reportes Estadisticos", "fa fa-plus", "none", null));
		
		menu.add(new Menu(600, "Difusión y Escucha al Cliente", "fa fa-plus", "none", difusionYEscuchaAlcliente()));
		menu.add(new Menu(700, "Administración", "fa fa-plus", "none", administracion()));
		 		
		 return ResponseDefault.ok(menu, CLASE, ResponseDefault.PLURAL);		 
	 }
	 
	 public List<Menu> configuracionGeneral() {
		 List<Menu> lista = new ArrayList<>();
		 lista.add(new Menu(9, "Mi Perfil", "fa fa-plus", "none", null));
		 lista.add(new Menu(10, "Mis Inmuebles", "fa fa-plus", "none", null));
		 lista.add(new Menu(11, "Segmentos", "fa fa-plus", "none", segmentos()));	
		 return lista;
	 }
	 
	 public List<Menu> segmentos() {
		List<Menu> lista = new ArrayList<>();
		lista.add(new Menu(12, "Cargo", "fa fa-plus", "none", null));
		lista.add(new Menu(13, "Categoria", "fa fa-plus", "none", null));
		lista.add(new Menu(14, "Categoria del Inmueble", "fa fa-plus", "none", null));
	 	lista.add(new Menu(15, "Categoria del Servicio", "fa fa-plus", "none", null));
	 	lista.add(new Menu(16, "Condición", "fa fa-plus", "none", null));
	 	lista.add(new Menu(17, "Estado", "fa fa-plus", "none", null));
	 	lista.add(new Menu(18, "Estado del Servicio", "fa fa-plus", "none", null));
	 	lista.add(new Menu(19, "Herramienta", "fa fa-plus", "none", null));
	 	lista.add(new Menu(20, "Ocupación", "fa fa-plus", "none", null));
	 	lista.add(new Menu(21, "Opción", "fa fa-plus", "none", null));
	 	lista.add(new Menu(22, "Pregunta", "fa fa-plus", "none", null));
	 	lista.add(new Menu(23, "Profesión", "fa fa-plus", "none", null));
	 	lista.add(new Menu(24, "Tarea", "fa fa-plus", "none", null));
	 	lista.add(new Menu(25, "Tipo de Caracteristica", "fa fa-plus", "none", null));
	 	lista.add(new Menu(26, "Tipo Cliente", "fa fa-plus", "none", null));
	 	lista.add(new Menu(27, "Tipo Diagnostico Visita", "fa fa-plus", "none", null));
	 	lista.add(new Menu(28, "Tipo de Eventualidad", "fa fa-plus", "none", null));
	 	lista.add(new Menu(29, "Tipo de Garantia", "fa fa-plus", "none", null));
	 	lista.add(new Menu(30, "Tipo de Inmueble", "fa fa-plus", "none", null));
	 	lista.add(new Menu(31, "Tipo de Motivo", "fa fa-plus", "none", null));
	 	lista.add(new Menu(32, "Tipo de Notificación", "fa fa-plus", "none", null));
	 	lista.add(new Menu(33, "Tipo de Promoción", "fa fa-plus", "none", null));
	 	lista.add(new Menu(34, "Tipo de Reclamo", "fa fa-plus", "none", null));
	 	lista.add(new Menu(35, "Tipo de Recurso", "fa fa-plus", "none", null));
	 	lista.add(new Menu(36, "Tipo de Respuesta", "fa fa-plus", "none", null));
	 	lista.add(new Menu(37, "Tipo de Servicio", "fa fa-plus", "none", null));
	 	lista.add(new Menu(38, "Tipo de Visita", "fa fa-plus", "none", null));
	 	lista.add(new Menu(39, "Turno", "fa fa-plus", "none", null));
	 	lista.add(new Menu(40, "Ubicación", "fa fa-plus", "none", null));
	 	lista.add(new Menu(41, "Unidad de Medida", "fa fa-plus", "none", null));
	 	lista.add(new Menu(42, "Uso del Inmueble", "fa fa-plus", "none", null));
		 return lista;
	 }
	
	 public List<Menu> serviciosYPromociones() {
		 List<Menu> lista = new ArrayList<>();
		 lista.add(new Menu(101, "Mis Servicios", "fa fa-plus", "none", null));
		 lista.add(new Menu(102, "Nuevo Servicio", "fa fa-plus", "none", null));
		 lista.add(new Menu(103, "Catalogo de Servicios", "fa fa-plus", "none", null));
		 return lista;
	 }
	 
	 public List<Menu> visita() {
		 List<Menu> lista = new ArrayList<>();
		 lista.add(new Menu(201, "Solicitudes Pendientes", "fa fa-plus", "none", null));
		 lista.add(new Menu(202, "Agendar", "fa fa-plus", "none", null));
		 lista.add(new Menu(203, "Diagnostico", "fa fa-plus", "none", null));
		 lista.add(new Menu(204, "Diagnosticos Realizados", "fa fa-plus", "none", null));
		 return lista;
	 }
	 
	 public List<Menu> planificacion() {
		 List<Menu> lista = new ArrayList<>();
		 lista.add(new Menu(301, "Orden de Servicio", "fa fa-plus", "none", null));
		 lista.add(new Menu(302, "Trabajadores", "fa fa-plus", "none", null));
		 lista.add(new Menu(303, "Materiales", "fa fa-plus", "none", null));
		 return lista;
	 }
	 
	 public List<Menu> ejecucion() {
		 List<Menu> lista = new ArrayList<>();
		 lista.add(new Menu(401, "Administrar Tareas", "fa fa-plus", "none", null));
		 lista.add(new Menu(402, "Eventualidades", "fa fa-plus", "none", null));
		 lista.add(new Menu(403, "Cierre de Servicio", "fa fa-plus", "none", null));
		 return lista;
	 }
	 
	 public List<Menu> difusionYEscuchaAlcliente() {
		 List<Menu> lista = new ArrayList<>();
		 lista.add(new Menu(601, "Búzon de Sugerencias", "fa fa-plus", "none", null));
		 lista.add(new Menu(602, "Atender Opiniones", "fa fa-plus", "none", null));
		 return lista;
	 }
	 
	 public List<Menu> administracion()  {
			List<Menu> lista = new ArrayList<>();
			lista.add(new Menu(701, "General", "fa fa-plus", "none", general()));
			lista.add(new Menu(720, "Sitio Web", "fa fa-plus", "none", sitioWeb()));
			lista.add(new Menu(740, "Seguridad Funcional", "fa fa-plus", "none", seguridadFuncional()));
		 	return lista;
		 }
	 
	 public List<Menu> general()  {
		List<Menu> lista = new ArrayList<>();
		lista.add(new Menu(702, "Garantia", "fa fa-plus", "none", null));
	 	lista.add(new Menu(703, "Eventualidades", "fa fa-plus", "none", null));
	 	lista.add(new Menu(704, "Atender Eventualidad", "fa fa-plus", "none", null));
	 	lista.add(new Menu(705, "Reclamos", "fa fa-plus", "none", null));		 		
	 	lista.add(new Menu(706, "Consultar Servicio", "fa fa-plus", "none", null));
	 	lista.add(new Menu(707, "Promociones", "fa fa-plus", "none", null));
	 	return lista;
	 }

	 public List<Menu> sitioWeb()  {
		List<Menu> lista = new ArrayList<>();
		lista.add(new Menu(721, "Noticias", "fa fa-plus", "none", null));
	 	lista.add(new Menu(722, "Empresa", "fa fa-plus", "none", null));
		return lista;
	}
	 
	public List<Menu> seguridadFuncional()  {
		List<Menu> lista = new ArrayList<>();
		lista.add(new Menu(741, "Usuarios", "fa fa-plus", "none", null));
 		lista.add(new Menu(742, "Roles", "fa fa-plus", "none", null));
		return lista;
	}
		 
}

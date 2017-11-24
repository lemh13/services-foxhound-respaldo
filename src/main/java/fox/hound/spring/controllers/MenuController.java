package fox.hound.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import fox.hound.spring.models.Menu;
import fox.hound.spring.models.combo.Rol;
import fox.hound.spring.print.PersonaGlobal;
import fox.hound.spring.security.TokenUtil;
import fox.hound.spring.services.RolService;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("menu")
public class MenuController {
	
	private final Logger logger = Logger.getLogger(this.getClass());

	 @Value("${foxhound.token.header}")
	 private String tokenHeader;
	 @Autowired
	 private TokenUtil tokenUtils;
	 @Autowired
	 private RolService rolService;
	
	 private Class<?> CLASE = Menu.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		return ResponseDefault.ok(buildMenu(), CLASE, ResponseDefault.PLURAL);		 
	}
	 
	 @RequestMapping(value="/buscarPorUsuario", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> buscarPorUsuario(HttpServletRequest request) {
		 PersonaGlobal p = tokenUtils.getUserFromToken(request.getHeader(tokenHeader));
		 logger.info(p.getId());
		 
		 Rol rol = rolService.getOne(p.getRol());		 
	 
		 return ResponseDefault.ok(buscarMenuPorRol(rol), CLASE, ResponseDefault.PLURAL);		 
	}
	 
	/**
	 * Iteramos desde el padre hasta el nieto en busca de coincidencias con con el id de los menus,
	 * para asi poder tener un menu personalizado dependiendo los permisos del rol.
	 * 
	 * @param rol
	 * @return List<Menu> 
	 **/ 
	private List<Menu> buscarMenuPorRol(Rol rol) {
		 boolean encontroHijo = false;
		 boolean encontroNieto = false;
		 List<Menu> menu = new ArrayList<>();
		 List<Menu> menuHijo = new ArrayList<>();
		 List<Menu> menuNieto = new ArrayList<>();
		 for (Menu m :  buildMenu()) { 
			
			 if (m.getChild() != null) {
				 menuHijo = new ArrayList<>();
				 for (Menu hijo : m.getChild()) {
					 
					 if (rol.getMenu().contains("-" + hijo.getId() +"-")) {
						 menuHijo.add(hijo);
						 encontroHijo = true;
						 continue;
					 }
					 
					 if (hijo.getChild() != null) {
						 menuNieto = new ArrayList<>();
						 for (Menu nieto : hijo.getChild()) {
							 
							 if (rol.getMenu().contains("-" + nieto.getId() +"-")) {
								 menuNieto.add(nieto);
								 encontroNieto = true;
								 encontroHijo = true;
								 continue;
							 }
						 }
					 }
					 
					 if (encontroNieto) {
						 hijo.setChild(menuNieto);
						 menuHijo.add(hijo);	 
					 } 
					 encontroNieto = false;
				 }
			 }
			 
			 
			 if (encontroHijo) {
				 m.setChild(menuHijo);
				 menu.add(m);
			 } 
			 encontroHijo = false; 
		 }
		 return menu;
	}

	/**
	 * Construimos el menu de Ocelot.
	 * 
	 * @return List<Menu> 
	 **/ 
	private List<Menu> buildMenu() {
		List<Menu> menu = new ArrayList<>();	
		menu.add(new Menu(1, "Gonfiguración General", "fa fa-plus", null, configuracionGeneral()));
		menu.add(new Menu(200, "Servicios y Promociones", "fa fa-plus", null, serviciosYPromociones()));
		menu.add(new Menu(400, "Visita", "fa fa-plus", null, visita()));
		menu.add(new Menu(600, "Planificación", "fa fa-plus", null, planificacion()));		 	
		menu.add(new Menu(800, "Ejecución", "fa fa-plus", null, ejecucion()));
		menu.add(new Menu(1000, "Reportes Estadisticos", "fa fa-plus", null, reportesEstadisticos()));
		menu.add(new Menu(1200, "Difusión y Escucha al Cliente", "fa fa-plus", null, difusionYEscuchaAlcliente()));
		menu.add(new Menu(1400, "Administración", "fa fa-plus", null, administracion()));
		
		return menu;
	}

	/**
	 * Construimos los hijos del menu Configuracion General.
	 * 
	 * @return List<Menu> 
	 **/ 
	public List<Menu> configuracionGeneral() {
		 List<Menu> lista = new ArrayList<>();
		 lista.add(new Menu(2, "Mi Perfil", "fa fa-plus", "mi_perfil_path", null));
		 lista.add(new Menu(3, "Mis Inmuebles", "fa fa-plus", "mis_inmuebles_path", null));
		 lista.add(new Menu(4, "Datos de Servicio", "fa fa-plus", null, servicio()));
		 lista.add(new Menu(24, "Datos de Inmueble", "fa fa-plus", null, inmueble()));
		 lista.add(new Menu(44, "Datos de Visita", "fa fa-plus", null, datosVisita()));
		 lista.add(new Menu(64, "Datos de Segmentos", "fa fa-plus", null, segmentos()));	
		 return lista;
	 }

	/**
	 * Construimos los hijos del menu Servicio.
	 * 
	 * @return List<Menu> 
	 **/ 
	private List<Menu> servicio() {
		List<Menu> lista = new ArrayList<>();
		lista.add(new Menu(5, "Estado", "fa fa-plus", "estado_path", null));
		lista.add(new Menu(6, "Ciudad", "fa fa-plus", "ciudad_path", null));	
		lista.add(new Menu(7, "Municipio", "fa fa-plus", "municipio_path", null));
		lista.add(new Menu(8, "Parroquia", "fa fa-plus", "parroquia_path", null));
		lista.add(new Menu(9, "Sector", "fa fa-plus", "sector_path", null));
		lista.add(new Menu(10, "Zona", "fa fa-plus", "zona_path", null));
		lista.add(new Menu(11, "Cargo", "fa fa-plus", "cargo_path", null));
		lista.add(new Menu(12, "Tarea", "fa fa-plus", "tarea_path", null));
		lista.add(new Menu(13, "Categoria", "fa fa-plus", "categoria_path", null));
	 	lista.add(new Menu(14, "Estado del Servicio", "fa fa-plus", "estado_servicio_path", null));
	 	lista.add(new Menu(15, "Respuesta", "fa fa-plus", "respuesta_path", null));
	 	lista.add(new Menu(16, "Tipo de Reclamo", "fa fa-plus", "tipo_reclamo_path", null));
	 	lista.add(new Menu(17, "Reclamo", "fa fa-plus", "reclamo_path", null));
	 	lista.add(new Menu(18, "Tipo de Garantia", "fa fa-plus", "tipo_garantia_path", null));
	 	lista.add(new Menu(19, "Tipo de Promoción", "fa fa-plus", "tipo_promocion_path", null));
	 	lista.add(new Menu(20, "Tipo de Servicio", "fa fa-plus", "tipo_servicio_path", null));		
		return lista;
	}
	
	/**
	 * Construimos los hijos del menu Inmueble.
	 * 
	 * @return List<Menu> 
	 **/ 
	 private List<Menu> inmueble() {
		List<Menu> lista = new ArrayList<>();
	 	lista.add(new Menu(25, "Ubicación", "fa fa-plus", "ubicacion_path", null));
	 	lista.add(new Menu(26, "Tipo de Caracteristica", "fa fa-plus", "tipo_caracteristica_path", null));
	 	lista.add(new Menu(27, "Caracteristica", "fa fa-plus", "caracteristica_path", null));
		lista.add(new Menu(28, "Categoria del Inmueble", "fa fa-plus", "categoria_inmueble_path", null));
	 	lista.add(new Menu(29, "Condición Inmueble", "fa fa-plus", "condicion_inmueble_path", null));
	 	lista.add(new Menu(30, "Condición", "fa fa-plus", "condicion_path", null));
	 	lista.add(new Menu(31, "Tipo de Inmueble", "fa fa-plus", "tipo_inmueble_path", null));
	 	lista.add(new Menu(32, "Unidad de Medida", "fa fa-plus", "unidad_medida_path", null));
	 	lista.add(new Menu(33, "Uso del Inmueble", "fa fa-plus", "uso_inmueble_path", null));

		return lista;
	}

	 /**
	 * Construimos los hijos del menu Datos de Visita.
	 * 
	 * @return List<Menu> 
	 **/ 
	private List<Menu> datosVisita() {
		List<Menu> lista = new ArrayList<>();
	 	lista.add(new Menu(45, "Tipo de Visita", "fa fa-plus", "tipo_visita_path", null));
	 	lista.add(new Menu(46, "Turno", "fa fa-plus", "turno_path", null));
	 	lista.add(new Menu(47, "Tipo de Diagnostico Visita", "fa fa-plus", "tipo_diagnostico_visita_path", null));
	 	lista.add(new Menu(48, "Eventualidad", "fa fa-plus", "eventualidad_path", null));
	 	lista.add(new Menu(49, "Tipo de Eventualidad", "fa fa-plus", "eventualidades_path", null));

		return lista;
	}
	 
	/**
	 * Construimos los hijos del menu Segmentos.
	 * 
	 * @return List<Menu> 
	 **/ 
	public List<Menu> segmentos() {
		List<Menu> lista = new ArrayList<>();
	 	lista.add(new Menu(65, "Profesión", "fa fa-plus", "profesion_path", null));	 	
	 	lista.add(new Menu(66, "Ocupación", "fa fa-plus", "ocupacion_path", null));
	 	lista.add(new Menu(67, "Tipo Cliente", "fa fa-plus", "tipo_cliente_path", null));	 	
	 	lista.add(new Menu(68, "Opción", "fa fa-plus", "opcion_path", null));
	 	lista.add(new Menu(69, "Opción Pregunta", "fa fa-plus", "opcion_pregunta_path", null));
	 	lista.add(new Menu(70, "Pregunta", "fa fa-plus", "pregunta_path", null));
	 	lista.add(new Menu(71, "Motivo", "fa fa-plus", "motivo_path", null));
	 	lista.add(new Menu(72, "Tipo de Motivo", "fa fa-plus", "tipo_motivo_path", null));
	 	lista.add(new Menu(73, "Tipo de Respuesta", "fa fa-plus", "tipo_respuesta_path", null));
		 return lista;
	 }
	
	/**
	 * Construimos los hijos del menu Servicios y Promociones.
	 * 
	 * @return List<Menu> 
	 **/ 
	 public List<Menu> serviciosYPromociones() {
		 List<Menu> lista = new ArrayList<>();
		 lista.add(new Menu(201, "Mis Servicios", "fa fa-plus", "ordenes_servicio_path", null));
		 lista.add(new Menu(202, "Nuevo Servicio", "fa fa-plus", "registrar_servicio_path", null));
		 lista.add(new Menu(203, "Catalogo de Servicios", "fa fa-plus", "catalogo_servicios_path", null));
		 return lista;
	 }
	 
	 /**
	 * Construimos los hijos del menu Visita.
	 * 
	 * @return List<Menu> 
	 **/ 
	 public List<Menu> visita() {
		 List<Menu> lista = new ArrayList<>();
		 lista.add(new Menu(401, "Solicitudes Pendientes", "fa fa-plus", "consultar_solicitud_path", null));
		 lista.add(new Menu(402, "Agendar", "fa fa-plus", "agenda_visitas_path", null));
		 lista.add(new Menu(403, "Diagnostico", "fa fa-plus", "informe_diagnostico_path", null));
		 lista.add(new Menu(404, "Diagnosticos Realizados", "fa fa-plus", "diagnosticos_realizados_path", null));
		 return lista;
	 }
	 
	 /**
	 * Construimos los hijos del menu Planificacion.
	 * 
	 * @return List<Menu> 
	 **/ 
	 public List<Menu> planificacion() {
		 List<Menu> lista = new ArrayList<>();
		 lista.add(new Menu(601, "Orden de Servicio", "fa fa-plus", "lista_presupuestos_path", null));
		 lista.add(new Menu(602, "Trabajadores", "fa fa-plus", "consultar_trabajadores_path", null));
		 lista.add(new Menu(603, "Materiales", "fa fa-plus", "consultar_materiales_path", null));
		 return lista;
	 }
	 
	 /**
	 * Construimos los hijos del menu Ejecucion.
	 * 
	 * @return List<Menu> 
	 **/ 
	 public List<Menu> ejecucion() {
		 List<Menu> lista = new ArrayList<>();
		 lista.add(new Menu(801, "Administrar Tareas", "fa fa-plus", "tareas_asignadas_path", null));
		 lista.add(new Menu(802, "Eventualidades", "fa fa-plus", "listado_eventualidades_path", null));
		 lista.add(new Menu(803, "Cierre de Servicio", "fa fa-plus", "cierre_servicio_path", null));
		 return lista;
	 }
	 
	 /**
	 * Construimos los hijos del menu Reportes Estadisticos.
	 * 
	 * @return List<Menu> 
	 **/ 
	 private List<Menu> reportesEstadisticos() {
		 List<Menu> lista = new ArrayList<>();
		 lista.add(new Menu(1001, "Servicios Aprobados", "fa fa-plus", "servicios_aprobados_path", null));
		 lista.add(new Menu(1002, "Estadistico Solicitudes", "fa fa-plus", "estadistico_solicitudes_path", null));
		 lista.add(new Menu(1003, "Comparativos de Solicitudes", "fa fa-plus", "comparativo_solicitudes_path", null));
		 lista.add(new Menu(1004, "Comparativo de Valoraciones de Servicio", "fa fa-plus", "comparativo_valoraciones_servicios_path", null));
		 lista.add(new Menu(1005, "Comparativos de Inmuebles", "fa fa-plus", "comparativo_inmuebles_path", null));
		 lista.add(new Menu(1006, "Comparativos de Reclamos", "fa fa-plus", "comparativo_reclamos_path", null));
		 lista.add(new Menu(1007, "Reporte Solicitudes", "fa fa-plus", "reporte_solicitudes_path", null));
		 lista.add(new Menu(1008, "Reporte Valoraciones", "fa fa-plus", "reporte_valoraciones_path", null));
		 lista.add(new Menu(1009, "Reporte Inmuebles", "fa fa-plus", "reporte_inmuebles_path", null));
		 lista.add(new Menu(1010, "Reporte Reclamos", "fa fa-plus", "reporte_reclamos_path", null));
		return lista;
	}
	 
	 /**
	 * Construimos los hijos del menu Difusion y Escucha al Cliente.
	 * 
	 * @return List<Menu> 
	 **/ 
	 public List<Menu> difusionYEscuchaAlcliente() {
		 List<Menu> lista = new ArrayList<>();
		 lista.add(new Menu(1201, "Búzon de Sugerencias", "fa fa-plus", "buzon_path", null));
		 lista.add(new Menu(1202, "Atender Opiniones", "fa fa-plus", "buzon_escucha_path", null));
		 return lista;
	 }
	 
	 /**
	 * Construimos los hijos del menu Administracion.
	 * 
	 * @return List<Menu> 
	 **/ 
	 public List<Menu> administracion()  {
		List<Menu> lista = new ArrayList<>();
		lista.add(new Menu(1401, "General", "fa fa-plus", null, general()));
		lista.add(new Menu(1500, "Sitio Web", "fa fa-plus", null, sitioWeb()));
		lista.add(new Menu(1600, "Seguridad Funcional", "fa fa-plus", null, seguridadFuncional()));
	 	return lista;
	 }
	 
	 /**
	 * Construimos los hijos del menu General.
	 * 
	 * @return List<Menu> 
	 **/ 
	 public List<Menu> general()  {
		List<Menu> lista = new ArrayList<>();
		lista.add(new Menu(1402, "Garantia", "fa fa-plus", "registrar_garantia_path", null));
	 	lista.add(new Menu(1403, "Servicios", "fa fa-plus", "gestion_servicio_path", null));
		lista.add(new Menu(104, "Eventualidades", "fa fa-plus", "listado_eventualidades_path", null));
	 	lista.add(new Menu(1405, "Reclamos", "fa fa-plus", "lista_reclamo_path", null));		 		
	 	lista.add(new Menu(1406, "Consultar Servicio", "fa fa-plus", "lista_servicios_asignados_supervisor_path", null));
	 	lista.add(new Menu(1407, "Promociones", "fa fa-plus", "lista_promociones_path", null));
	 	return lista;
	 }

	 /**
	 * Construimos los hijos del menu Sitio Web.
	 * 
	 * @return List<Menu> 
	 **/ 
	 public List<Menu> sitioWeb()  {
		List<Menu> lista = new ArrayList<>();
		lista.add(new Menu(1501, "Noticias", "fa fa-plus", "lista_noticias_path", null));
	 	//lista.add(new Menu(, "Empresa", "fa fa-plus", null, null));
		return lista;
	}
	
	 /**
	 * Construimos los hijos del menu Seguridad Funcional.
	 * 
	 * @return List<Menu> 
	 **/ 
	public List<Menu> seguridadFuncional()  {
		List<Menu> lista = new ArrayList<>();
		lista.add(new Menu(1601, "Usuarios", "fa fa-plus", "gestion_usuario_path", null));
 		lista.add(new Menu(1602, "Roles", "fa fa-plus", "gestion_rol_path", null));
		return lista;
	}
		 
}

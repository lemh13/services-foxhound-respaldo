package fox.hound.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import fox.hound.spring.models.Menu;
import fox.hound.spring.utils.ResponseDefault;

@RestController
@RequestMapping("menu")
public class MenuController {

	 private Class<?> CLASE = Menu.class;

	 @RequestMapping(value="/buscarTodos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> getAll(HttpServletRequest request) {
		 
		 List<Menu> menu = new ArrayList<>();
		 			 	 		
		menu.add(new Menu(1, "Gonfiguración General", "fa fa-plus", null, configuracionGeneral()));
		menu.add(new Menu(100, "Servicios y Promociones", "fa fa-plus", null, serviciosYPromociones()));
		menu.add(new Menu(200, "Visita", "fa fa-plus", null, visita()));
		menu.add(new Menu(300, "Planificación", "fa fa-plus", null, planificacion()));		 	
		menu.add(new Menu(400, "Ejecución", "fa fa-plus", null, ejecucion()));
	 	
		menu.add(new Menu(500, "Reportes Estadisticos", "fa fa-plus", null, reportesEstadisticos()));
		
		menu.add(new Menu(600, "Difusión y Escucha al Cliente", "fa fa-plus", null, difusionYEscuchaAlcliente()));
		menu.add(new Menu(700, "Administración", "fa fa-plus", null, administracion()));
		 		
		 return ResponseDefault.ok(menu, CLASE, ResponseDefault.PLURAL);		 
	}

	public List<Menu> configuracionGeneral() {
		 List<Menu> lista = new ArrayList<>();
		 lista.add(new Menu(9, "Mi Perfil", "fa fa-plus", "mi_perfil_path", null));
		 lista.add(new Menu(10, "Mis Inmuebles", "fa fa-plus", "mis_inmuebles_path", null));
		 lista.add(new Menu(10, "Datos de Servicio", "fa fa-plus", null, servicio()));
		 lista.add(new Menu(10, "Datos de Inmueble", "fa fa-plus", null, inmueble()));
		 lista.add(new Menu(10, "Datos de Visita", "fa fa-plus", null, datosVisita()));
		 lista.add(new Menu(11, "Datos de Segmentos", "fa fa-plus", null, segmentos()));	
		 return lista;
	 }

	private List<Menu> servicio() {
		List<Menu> lista = new ArrayList<>();
		lista.add(new Menu(17, "Estado", "fa fa-plus", "estado_path", null));
		lista.add(new Menu(17, "Ciudad", "fa fa-plus", "ciudad_path", null));	
		lista.add(new Menu(17, "Municipio", "fa fa-plus", "municipio_path", null));
		lista.add(new Menu(17, "Parroquia", "fa fa-plus", "parroquia_path", null));
		lista.add(new Menu(17, "Sector", "fa fa-plus", "sector_path", null));
		lista.add(new Menu(17, "Zona", "fa fa-plus", "zona_path", null));
		lista.add(new Menu(12, "Cargo", "fa fa-plus", "cargo_path", null));
		lista.add(new Menu(24, "Tarea", "fa fa-plus", "tarea_path", null));
		lista.add(new Menu(13, "Categoria", "fa fa-plus", "categoria_path", null));
	 	lista.add(new Menu(18, "Estado del Servicio", "fa fa-plus", "estado_servicio_path", null));
	 	lista.add(new Menu(28, "Tipo de Eventualidad", "fa fa-plus", "tipo_eventualidad_path", null));
	 	lista.add(new Menu(28, "Respuesta", "fa fa-plus", "respuesta_path", null));
	 	lista.add(new Menu(34, "Tipo de Reclamo", "fa fa-plus", "tipo_reclamo_path", null));
	 	lista.add(new Menu(34, "Reclamo", "fa fa-plus", "reclamo_path", null));
	 	lista.add(new Menu(29, "Tipo de Garantia", "fa fa-plus", "tipo_garantia_path", null));
	 	lista.add(new Menu(33, "Tipo de Promoción", "fa fa-plus", "tipo_promocion_path", null));
	 	lista.add(new Menu(37, "Tipo de Servicio", "fa fa-plus", "tipo_servicio_path", null));		
		return lista;
	}
	
	 private List<Menu> inmueble() {
		List<Menu> lista = new ArrayList<>();
	 	lista.add(new Menu(40, "Ubicación", "fa fa-plus", "ubicacion_path", null));
	 	lista.add(new Menu(25, "Tipo de Caracteristica", "fa fa-plus", "tipo_caracteristica_path", null));
	 	lista.add(new Menu(25, "Caracteristica", "fa fa-plus", "caracteristica_path", null));
		lista.add(new Menu(14, "Categoria del Inmueble", "fa fa-plus", "categoria_inmueble_path", null));
	 	lista.add(new Menu(16, "Condición", "fa fa-plus", "condicion_path", null));
	 	lista.add(new Menu(30, "Tipo de Inmueble", "fa fa-plus", "tipo_inmueble_path", null));
	 	lista.add(new Menu(41, "Unidad de Medida", "fa fa-plus", "unidad_medida_path", null));
	 	lista.add(new Menu(42, "Uso del Inmueble", "fa fa-plus", "uso_inmueble_path", null));

		return lista;
	}

	private List<Menu> datosVisita() {
		List<Menu> lista = new ArrayList<>();
	 	lista.add(new Menu(38, "Tipo de Visita", "fa fa-plus", "tipo_visita_path", null));
	 	lista.add(new Menu(39, "Turno", "fa fa-plus", "turno_path", null));
	 	lista.add(new Menu(32, "Tipo de Diagnostico Visita", "fa fa-plus", "tipo_diagnostico_visita_path", null));
	 	lista.add(new Menu(32, "Tipo de Eventualidad", "fa fa-plus", "tipo_eventualidad_path", null));

		return lista;
	}
	 
	public List<Menu> segmentos() {
		List<Menu> lista = new ArrayList<>();
	 	lista.add(new Menu(23, "Profesión", "fa fa-plus", "profesion_path", null));	 	
	 	lista.add(new Menu(20, "Ocupación", "fa fa-plus", "ocupacion_path", null));
	 	lista.add(new Menu(26, "Tipo Cliente", "fa fa-plus", "tipo_cliente_path", null));	 	
	 	lista.add(new Menu(21, "Opción", "fa fa-plus", "opcion_path", null));
	 	lista.add(new Menu(21, "Opción Pregunta", "fa fa-plus", "opcion_pregunta_path", null));
	 	lista.add(new Menu(22, "Pregunta", "fa fa-plus", "pregunta_path", null));
	 	lista.add(new Menu(31, "Tipo de Motivo", "fa fa-plus", "tipo_motivo_path", null));
	 	lista.add(new Menu(36, "Tipo de Respuesta", "fa fa-plus", "tipo_respuesta_path", null));
		 return lista;
	 }
	
	 public List<Menu> serviciosYPromociones() {
		 List<Menu> lista = new ArrayList<>();
		 lista.add(new Menu(101, "Mis Servicios", "fa fa-plus", "ordenes_servicio_path", null));
		 lista.add(new Menu(102, "Nuevo Servicio", "fa fa-plus", "registrar_servicio_path", null));
		 lista.add(new Menu(103, "Catalogo de Servicios", "fa fa-plus", "catalogo_servicios_path", null));
		 return lista;
	 }
	 
	 public List<Menu> visita() {
		 List<Menu> lista = new ArrayList<>();
		 lista.add(new Menu(201, "Solicitudes Pendientes", "fa fa-plus", "consultar_solicitud_path", null));
		 lista.add(new Menu(202, "Agendar", "fa fa-plus", "agenda_visitas_path", null));
		 lista.add(new Menu(203, "Diagnostico", "fa fa-plus", "informe_diagnostico_path", null));
		 lista.add(new Menu(204, "Diagnosticos Realizados", "fa fa-plus", "diagnosticos_realizados_path", null));
		 return lista;
	 }
	 
	 public List<Menu> planificacion() {
		 List<Menu> lista = new ArrayList<>();
		 lista.add(new Menu(301, "Orden de Servicio", "fa fa-plus", "lista_presupuestos_path", null));
		 lista.add(new Menu(302, "Trabajadores", "fa fa-plus", "consultar_trabajadores_path", null));
		 lista.add(new Menu(303, "Materiales", "fa fa-plus", "consultar_materiales_path", null));
		 return lista;
	 }
	 
	 public List<Menu> ejecucion() {
		 List<Menu> lista = new ArrayList<>();
		 lista.add(new Menu(401, "Administrar Tareas", "fa fa-plus", "tareas_asignadas_path", null));
		 lista.add(new Menu(402, "Eventualidades", "fa fa-plus", "listado_eventualidades_path", null));
		 lista.add(new Menu(403, "Cierre de Servicio", "fa fa-plus", "cierre_servicio_path", null));
		 return lista;
	 }
	 
	 private List<Menu> reportesEstadisticos() {
		 List<Menu> lista = new ArrayList<>();
		 lista.add(new Menu(403, "Servicios Aprobados", "fa fa-plus", "servicios_aprobados_path", null));

		return lista;
	}
	 
	 public List<Menu> difusionYEscuchaAlcliente() {
		 List<Menu> lista = new ArrayList<>();
		 lista.add(new Menu(601, "Búzon de Sugerencias", "fa fa-plus", "buzon_path", null));
		 lista.add(new Menu(602, "Atender Opiniones", "fa fa-plus", "buzon_escucha_path", null));
		 return lista;
	 }
	 
	 public List<Menu> administracion()  {
			List<Menu> lista = new ArrayList<>();
			lista.add(new Menu(701, "General", "fa fa-plus", null, general()));
			lista.add(new Menu(720, "Sitio Web", "fa fa-plus", null, sitioWeb()));
			lista.add(new Menu(740, "Seguridad Funcional", "fa fa-plus", null, seguridadFuncional()));
		 	return lista;
		 }
	 
	 public List<Menu> general()  {
		List<Menu> lista = new ArrayList<>();
		lista.add(new Menu(702, "Garantia", "fa fa-plus", "registrar_garantia_path", null));
	 	lista.add(new Menu(703, "Servicios", "fa fa-plus", "gestion_servicio_path", null));
		lista.add(new Menu(703, "Eventualidades", "fa fa-plus", "listado_eventualidades_path", null));
	 	lista.add(new Menu(705, "Reclamos", "fa fa-plus", "lista_reclamo_path", null));		 		
	 	lista.add(new Menu(706, "Consultar Servicio", "fa fa-plus", "lista_servicios_asignados_supervisor_path", null));
	 	lista.add(new Menu(707, "Promociones", "fa fa-plus", "lista_promociones_path", null));
	 	return lista;
	 }

	 public List<Menu> sitioWeb()  {
		List<Menu> lista = new ArrayList<>();
		lista.add(new Menu(721, "Noticias", "fa fa-plus", "lista_noticias_path", null));
	 	//lista.add(new Menu(722, "Empresa", "fa fa-plus", null, null));
		return lista;
	}
	 
	public List<Menu> seguridadFuncional()  {
		List<Menu> lista = new ArrayList<>();
		lista.add(new Menu(741, "Usuarios", "fa fa-plus", "gestion_usuario_path", null));
 		lista.add(new Menu(742, "Roles", "fa fa-plus", "gestion_rol_path", null));
		return lista;
	}
		 
}

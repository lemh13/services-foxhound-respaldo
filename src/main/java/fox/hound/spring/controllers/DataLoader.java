package fox.hound.spring.controllers;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import fox.hound.spring.models.Cliente;
import fox.hound.spring.models.DetallePresupuesto;
import fox.hound.spring.models.DiagnosticoVisita;
import fox.hound.spring.models.Empresa;
import fox.hound.spring.models.Garantia;
import fox.hound.spring.models.Inmueble;
import fox.hound.spring.models.Noticia;
import fox.hound.spring.models.OpcionCliente;
import fox.hound.spring.models.OrdenEntrega;
import fox.hound.spring.models.OrdenServicio;
import fox.hound.spring.models.Persona;
import fox.hound.spring.models.Respuesta;
import fox.hound.spring.models.Servicio;
import fox.hound.spring.models.Trabajador;
import fox.hound.spring.models.Visita;
import fox.hound.spring.models.combo.AsuntoComentario;
import fox.hound.spring.models.combo.BuzonSugerencia;
import fox.hound.spring.models.combo.Caracteristica;
import fox.hound.spring.models.combo.Ciudad;
import fox.hound.spring.models.combo.ComentarioExterno;
import fox.hound.spring.models.combo.CondicionInmueble;
import fox.hound.spring.models.combo.Descuento;
import fox.hound.spring.models.combo.Eventualidad;
import fox.hound.spring.models.combo.Motivo;
import fox.hound.spring.models.combo.Municipio;
import fox.hound.spring.models.combo.OpcionPregunta;
import fox.hound.spring.models.combo.Parroquia;
import fox.hound.spring.models.combo.Presupuesto;
import fox.hound.spring.models.combo.Rol;
import fox.hound.spring.models.combo.Sector;
import fox.hound.spring.models.combo.ServicioTarea;
import fox.hound.spring.models.combo.Zona;
import fox.hound.spring.models.maestros.CalificarServicio;
import fox.hound.spring.models.maestros.Cargo;
import fox.hound.spring.models.maestros.Categoria;
import fox.hound.spring.models.maestros.CategoriaCalificarServicio;
import fox.hound.spring.models.maestros.CategoriaInmueble;
import fox.hound.spring.models.maestros.Condicion;
import fox.hound.spring.models.maestros.CondicionGarantia;
import fox.hound.spring.models.maestros.Estado;
import fox.hound.spring.models.maestros.Ocupacion;
import fox.hound.spring.models.maestros.Opcion;
import fox.hound.spring.models.maestros.Pregunta;
import fox.hound.spring.models.maestros.Profesion;
import fox.hound.spring.models.maestros.RedSocial;
import fox.hound.spring.models.maestros.Tarea;
import fox.hound.spring.models.maestros.TipoCaracteristica;
import fox.hound.spring.models.maestros.TipoCliente;
import fox.hound.spring.models.maestros.TipoDiagnosticoVisita;
import fox.hound.spring.models.maestros.TipoEventualidad;
import fox.hound.spring.models.maestros.TipoInmueble;
import fox.hound.spring.models.maestros.TipoMotivo;
import fox.hound.spring.models.maestros.TipoOrdenServicio;
import fox.hound.spring.models.maestros.TipoPromocion;
import fox.hound.spring.models.maestros.TipoReclamo;
import fox.hound.spring.models.maestros.TipoRespuesta;
import fox.hound.spring.models.maestros.TipoServicio;
import fox.hound.spring.models.maestros.TipoVisita;
import fox.hound.spring.models.maestros.Turno;
import fox.hound.spring.models.maestros.Ubicacion;
import fox.hound.spring.models.maestros.UnidadMedida;
import fox.hound.spring.models.maestros.UsoInmueble;
import fox.hound.spring.models.puente.CaracteristicaInmueble;
import fox.hound.spring.models.puente.CargoTipoServicio;
import fox.hound.spring.models.puente.ClienteOcupacion;
import fox.hound.spring.models.puente.ClienteProfesion;
import fox.hound.spring.models.puente.CondicionDiagnostico;
import fox.hound.spring.models.puente.DetalleDiagnosticoVisita;
import fox.hound.spring.models.puente.DetalleOrdenServicio;
import fox.hound.spring.models.puente.DetalleServicioInmueble;
import fox.hound.spring.models.puente.MotivoSolicitud;
import fox.hound.spring.models.puente.MotivoOrdenServicioEventualidad;
import fox.hound.spring.models.puente.MotivoPresupuesto;
import fox.hound.spring.models.puente.MotivoReclamo;
import fox.hound.spring.models.puente.OrdenServicioEventualidad;
import fox.hound.spring.models.puente.PreferenciaCliente;
import fox.hound.spring.models.puente.Promocion;
import fox.hound.spring.models.puente.PromocionServicio;
import fox.hound.spring.models.puente.ReclamoOrdenEntrega;
import fox.hound.spring.models.puente.RolFuncion;
import fox.hound.spring.models.puente.Solicitud;
import fox.hound.spring.models.puente.SolicitudEventualidad;
import fox.hound.spring.models.puente.SolicitudServicio;
import fox.hound.spring.models.puente.SolicitudServicioMotivo;
import fox.hound.spring.models.puente.TipoCaracteristicaInmueble;
import fox.hound.spring.models.puente.TipoCaracteristicaServicio;
import fox.hound.spring.models.puente.TrabajadorVisita;
import fox.hound.spring.models.puente.ValoracionOrdenServicio;
import fox.hound.spring.services.AsuntoComentarioService;
import fox.hound.spring.services.BuzonSugerenciaService;
import fox.hound.spring.services.CalificarServicioService;
import fox.hound.spring.services.CaracteristicaInmuebleService;
import fox.hound.spring.services.CaracteristicaService;
import fox.hound.spring.services.CargoService;
import fox.hound.spring.services.CargoTipoServicioService;
import fox.hound.spring.services.CategoriaInmuebleService;
import fox.hound.spring.services.CategoriaService;
import fox.hound.spring.services.CiudadService;
import fox.hound.spring.services.ClienteOcupacionService;
import fox.hound.spring.services.ClienteProfesionService;
import fox.hound.spring.services.ClienteService;
import fox.hound.spring.services.ComentarioExternoService;
import fox.hound.spring.services.CondicionDiagnosticoService;
import fox.hound.spring.services.CondicionGarantiaService;
import fox.hound.spring.services.CondicionInmuebleService;
import fox.hound.spring.services.CondicionService;
import fox.hound.spring.services.DescuentoService;
import fox.hound.spring.services.DetalleDiagnosticoVisitaService;
import fox.hound.spring.services.DetalleOrdenServicioService;
import fox.hound.spring.services.DetallePresupuestoService;
import fox.hound.spring.services.DetalleServicioInmuebleService;
import fox.hound.spring.services.DiagnosticoVisitaService;
import fox.hound.spring.services.EmpresaService;
import fox.hound.spring.services.EstadoService;
import fox.hound.spring.services.EventualidadService;
import fox.hound.spring.services.GarantiaService;
import fox.hound.spring.services.InmuebleService;
import fox.hound.spring.services.MotivoOrdenServicioEventualidadService;
import fox.hound.spring.services.MotivoPresupuestoService;
import fox.hound.spring.services.MotivoReclamoService;
import fox.hound.spring.services.MotivoService;
import fox.hound.spring.services.MunicipioService;
import fox.hound.spring.services.NoticiaService;
import fox.hound.spring.services.OcupacionService;
import fox.hound.spring.services.OpcionClienteService;
import fox.hound.spring.services.OpcionPreguntaService;
import fox.hound.spring.services.OpcionService;
import fox.hound.spring.services.OrdenEntregaService;
import fox.hound.spring.services.OrdenServicioEventualidadService;
import fox.hound.spring.services.OrdenServicioService;
import fox.hound.spring.services.ParroquiaService;
import fox.hound.spring.services.PersonaService;
import fox.hound.spring.services.PreferenciaClienteService;
import fox.hound.spring.services.PreguntaService;
import fox.hound.spring.services.PresupuestoService;
import fox.hound.spring.services.ProfesionService;
import fox.hound.spring.services.PromocionService;
import fox.hound.spring.services.PromocionServicioService;
import fox.hound.spring.services.ReclamoOrdenEntregaService;
import fox.hound.spring.services.RedSocialService;
import fox.hound.spring.services.RespuestaService;
import fox.hound.spring.services.RolFuncionService;
import fox.hound.spring.services.RolService;
import fox.hound.spring.services.SectorService;
import fox.hound.spring.services.ServicioService;
import fox.hound.spring.services.ServicioTareaService;
import fox.hound.spring.services.SolicitudEventualidadService;
import fox.hound.spring.services.SolicitudService;
import fox.hound.spring.services.SolicitudServicioMotivoService;
import fox.hound.spring.services.SolicitudServicioService;
import fox.hound.spring.services.TareaService;
import fox.hound.spring.services.TipoCaracteristicaInmuebleService;
import fox.hound.spring.services.TipoCaracteristicaService;
import fox.hound.spring.services.TipoCaracteristicaServicioService;
import fox.hound.spring.services.TipoClienteService;
import fox.hound.spring.services.TipoDiagnosticoVisitaService;
import fox.hound.spring.services.TipoEventualidadService;
import fox.hound.spring.services.TipoInmuebleService;
import fox.hound.spring.services.TipoMotivoService;
import fox.hound.spring.services.TipoOrdenServicioService;
import fox.hound.spring.services.TipoPromocionService;
import fox.hound.spring.services.TipoReclamoService;
import fox.hound.spring.services.TipoRespuestaService;
import fox.hound.spring.services.TipoServicioService;
import fox.hound.spring.services.TipoVisitaService;
import fox.hound.spring.services.TrabajadorService;
import fox.hound.spring.services.TrabajadorVisitaService;
import fox.hound.spring.services.TurnoService;
import fox.hound.spring.services.UbicacionService;
import fox.hound.spring.services.UnidadMedidaService;
import fox.hound.spring.services.UsoInmuebleService;
import fox.hound.spring.services.ValoracionOrdenServicioService;
import fox.hound.spring.services.VisitaService;
import fox.hound.spring.services.ZonaService;
import fox.hound.spring.utils.DateUtil;
import fox.hound.spring.utils.EncryptionUtil;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private CargoService cargoService;
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private EstadoService estadoService;
	@Autowired
	private CiudadService ciudadService;
	@Autowired
	private MunicipioService municipioService;
	@Autowired
	private DiagnosticoVisitaService diagnosticoservicioService;
	@Autowired
	private TipoCaracteristicaInmuebleService tipocaracteristicaService1;
	@Autowired
	private ParroquiaService parroquiaService;
	@Autowired
	private DetalleDiagnosticoVisitaService detalledvService;
	@Autowired
	private SectorService sectorService;
	@Autowired
	private RolService rolService;
	@Autowired
	private TipoClienteService tipoClienteService;
	@Autowired
	private PersonaService personaService;
	@Autowired
	private ProfesionService profesionService;
	@Autowired
	private UsoInmuebleService usoInmuebleService;
	@Autowired
	private CategoriaInmuebleService categoriaInmuebleService;
	@Autowired
	private TipoInmuebleService tipoInmuebleService;
	@Autowired
	private TipoDiagnosticoVisitaService tipodiagnosticovisitaService;
	@Autowired
	private InmuebleService inmuebleService;
	@Autowired
	private EmpresaService empresaService;
	@Autowired
	private UnidadMedidaService unidadMedidaService;
	@Autowired
	private TipoServicioService tipoServicioService;
	@Autowired
	private CaracteristicaInmuebleService caracteristicainmService;
	
	@Autowired
	private CondicionDiagnosticoService condiciondiagnosticoService;
	@Autowired
	private CondicionGarantiaService condicionGarantiaService;
	@Autowired
	private GarantiaService garantiaService;
	@Autowired
	private DetallePresupuestoService detallePresupuestoService;
	@Autowired
	private ServicioService servicioService;
	@Autowired
	private TipoPromocionService tipoPromocionService;
	@Autowired
	private SolicitudServicioService solicitudservicioservice;
	@Autowired
	private SolicitudServicioMotivoService solicitudServicioMotivoService;
	@Autowired
	private DescuentoService descuentoService;
	@Autowired
	private CondicionInmuebleService condicioninmuebleService;
	@Autowired
	private PromocionService promocionService;
	@Autowired
	private PromocionServicioService promocionServicioService;
	@Autowired
	private NoticiaService noticiaService;
	@Autowired
	private TipoOrdenServicioService tipoOrdenServicioService;
	@Autowired
	private OrdenServicioService ordenServicioService;
	@Autowired
	private TipoCaracteristicaService tipocaracteristicaService;
	@Autowired
	private TipoEventualidadService tipoEventualidadService;
	@Autowired
	private EventualidadService eventualidadService;
	@Autowired
	private OcupacionService ocupacionService;
	@Autowired
	private TurnoService turnoService;
	@Autowired
	private TipoVisitaService tipoVisitaService;
	@Autowired
	private SolicitudService solicitudService;
	@Autowired
	private VisitaService visitaService;
	@Autowired
	private TrabajadorVisitaService trabajadorVisitaService;
	@Autowired
	private AsuntoComentarioService asuntoComentarioService;
	@Autowired
	private BuzonSugerenciaService buzonSugerenciaService;
	@Autowired
	private CalificarServicioService calificarServicioService;
	@Autowired
	private CaracteristicaInmuebleService caracteristicaInmuebleService;
	@Autowired
	private CargoTipoServicioService cargoTipoServicioService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ClienteOcupacionService clienteOcupacionService;
	@Autowired
	private ClienteProfesionService clienteProfesionService;
	@Autowired
	private ComentarioExternoService comentarioExternoService;
	@Autowired
	private CondicionDiagnosticoService condicioDiagnosticoSevice;
	@Autowired
	private CondicionInmuebleService condicionInmuebleService;
	@Autowired
	private CondicionService condicionService;
	@Autowired
	private DetalleDiagnosticoVisitaService detalleDiagnosticoVisita;
	private DetalleDiagnosticoVisitaService detalleDiagnosticoVisitaService;
	@Autowired
	private DetalleOrdenServicioService detalleOrdenService;
	private DetalleOrdenServicioService detalleOrdenServicioService;
	@Autowired
	private DetallePresupuestoService detallePresupuesto;
	@Autowired
	private DetalleServicioInmuebleService detalleServicioInmuebleService;
	@Autowired
	private DiagnosticoVisitaService diagnosticoVisitaService;
	@Autowired
	private MotivoOrdenServicioEventualidadService motivoOrdenServicioEventualidadService;
	@Autowired
	private MotivoPresupuestoService motivoPresupuestoService;
	@Autowired
	private MotivoReclamoService motivoReclamoService;
	@Autowired
	private MotivoService motivoService;
	@Autowired
	private OpcionClienteService opcionClienteService;
	@Autowired
	private OpcionPreguntaService opcionPreguntaService;
	@Autowired
	private OpcionService opcionService;
	@Autowired
	private OrdenEntregaService ordenEntregaService;
	@Autowired
	private PreguntaService preguntaService;
	@Autowired
	private PresupuestoService presupuestoClienteService;
	@Autowired
	private ReclamoOrdenEntregaService reclamoOrdenEntregaService;
	@Autowired
	private RedSocialService redSocialService;
	@Autowired
	private RespuestaService respuestaService;
	@Autowired
	private RolFuncionService rolFuncionesService;
	@Autowired
	private ServicioTareaService servicioTareaService;
	@Autowired
	private SolicitudEventualidadService solicitudEventualidadService;
	@Autowired
	private SolicitudServicioService solicitudServicioService;
	@Autowired
	private TareaService tareaService;
	 private PreferenciaClienteService preferenciaClienteService;
	@Autowired
	private TipoCaracteristicaInmuebleService tipoCaracteristicaInmuebleService;
	@Autowired
	private TipoCaracteristicaService tipoCaracteristicaService;
	@Autowired
	private TipoCaracteristicaServicioService tipoCaracteristcaServicioService;
	@Autowired
	private TipoDiagnosticoVisitaService tipoDiagnosticoVisitaService;
	@Autowired
	private TipoMotivoService tipoMotivoService;
	@Autowired
	private TipoReclamoService tipoReclamoService;
	@Autowired
	private TipoRespuestaService tipoRespuestaService;
	@Autowired
	private TrabajadorService trabajadorService;
	@Autowired
	private UbicacionService ubicacionService;
	@Autowired
	private ValoracionOrdenServicioService valoracionOrdenServicioService;
	@Autowired
	private ZonaService zonaService;
	@Autowired
	private CaracteristicaService caracteristicaService;
	@Autowired
	private CondicionDiagnosticoService condicionDiagnosticoService;
	@Autowired
	private PresupuestoService presupuestoService;
	@Autowired
	private TipoCaracteristicaServicioService tipoCaracteristicaServicioService;
	@Autowired
	private ServicioTareaService servicioTareaService2;
	
	private OrdenServicioEventualidadService ordenServicioEventualidadService;
	//@Autowired
	//private Categoria


	
	
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		// Cargo
		Cargo cargo = new Cargo();
		cargo.setDescripcion("Obrero");
		cargo.setEstatus(0);
		cargo.setFecha_creacion( DateUtil.getCurrentDate() );
		cargoService.saveOrUpdate(cargo);
		
		// Categoria
		Categoria categoria = new Categoria();
		categoria.setDescripcion("Reparación");
		categoria.setEstatus(0);
		categoria.setFecha_creacion( DateUtil.getCurrentDate() );
		categoriaService.saveOrUpdate(categoria);
		
		Categoria categoria2 = new Categoria();
		categoria2.setDescripcion("Mantenimiento");
		categoria2.setEstatus(0);
		categoria2.setFecha_creacion( DateUtil.getCurrentDate() );
		categoriaService.saveOrUpdate(categoria2);
		
		Categoria categoria3 = new Categoria();
		categoria3.setDescripcion("Instalación");
		categoria3.setEstatus(0);
		categoria3.setFecha_creacion( DateUtil.getCurrentDate() );
		categoriaService.saveOrUpdate(categoria3);
		
		// Estado
		Estado estado = new Estado();
		estado.setDescripcion("Lara");
		estado.setEstatus(0);
		estado.setFecha_creacion( DateUtil.getCurrentDate() );
		estadoService.saveOrUpdate(estado);
		
		// Ciudad
		Ciudad ciudad = new Ciudad();
		ciudad.setDescripcion("Barquisimeto");
		ciudad.setEstatus(0);
		ciudad.setEstado(estado);
		ciudad.setFecha_creacion( DateUtil.getCurrentDate() );
		ciudadService.saveOrUpdate(ciudad);
		
		// Municipio
		Municipio municipio = new Municipio();
		municipio.setDescripcion("Iribarren");
		municipio.setEstatus(0);
		municipio.setCiudad(ciudad);
		municipio.setFecha_creacion( DateUtil.getCurrentDate() );
		municipioService.saveOrUpdate(municipio);
		
		// Parroquia
		Parroquia parroquia = new Parroquia();
		parroquia.setDescripcion("Concepción");
		parroquia.setEstatus(0);
		parroquia.setMunicipio(municipio);
		parroquia.setFecha_creacion( DateUtil.getCurrentDate() );
		parroquiaService.saveOrUpdate(parroquia);
		
		

		//Presupuesto
		
		Presupuesto p = new Presupuesto();
		p.setEstatus(0);
		p.setFecha_creacion(DateUtil.getCurrentDate());
		p.setDescripcion("presupuesto justo");
		p.setMontoTotal(2000);
		presupuestoService.saveOrUpdate(p);
		

		// Sector
		Sector sector = new Sector();
		sector.setDescripcion("Barrio Nuevo");
		sector.setEstatus(0);
		sector.setParroquia(parroquia);
		sector.setFecha_creacion( DateUtil.getCurrentDate() );
		sectorService.saveOrUpdate(sector);
		
		// Rol
		Rol rol = new Rol();
		rol.setDescripcion("Cliente Simple");
		rol.setMenu("-2-5-201-203-");
		rol.setEstatus(0);
		rol.setFecha_creacion( DateUtil.getCurrentDate() );
		rolService.saveOrUpdate(rol);
		
		Rol rol2 = new Rol();
		rol2.setDescripcion("Tecnico");
		rol2.setMenu("-2-5-201-203-403-");
		rol2.setEstatus(0);
		rol2.setFecha_creacion( DateUtil.getCurrentDate() );
		rolService.saveOrUpdate(rol2);
		
		Rol rol3 = new Rol();
		rol3.setDescripcion("Tecnico");
		rol3.setMenu("-2-5-201-203-803-");
		rol3.setEstatus(0);
		rol3.setFecha_creacion( DateUtil.getCurrentDate() );
		rolService.saveOrUpdate(rol3);
		
		// Tipo Cliente
		TipoCliente tipoCliente = new TipoCliente();
		tipoCliente.setDescripcion("Propietario");
		tipoCliente.setEstatus(0);
		tipoCliente.setFecha_creacion( DateUtil.getCurrentDate() );
		tipoClienteService.saveOrUpdate(tipoCliente);
		
		// Cliente
		Cliente cliente = new Cliente();
		cliente.setNombre("Jose Duin");
		cliente.setSexo('M');
		cliente.setTipoPersona(0);
		cliente.setIdentificacion(21526571);
		cliente.setDireccion("Carrera 13b");
		cliente.setSector(sector);
		cliente.setTelefono("04120523025");
		cliente.setFecha_de_nacimiento( DateUtil.getCurrentDate() );
		cliente.setEmail("josemiguelduin@gmail.com");
		cliente.setPassword( "123" );
		cliente.setRol(rol);
		cliente.setTokenMovil("null");
		cliente.setTipoCliente(tipoCliente);
		cliente.setEstatus(0);
		cliente.setFecha_creacion( DateUtil.getCurrentDate() );
		clienteService.saveOrUpdate(cliente);
		
		Cliente cliente2 = new Cliente();
		cliente2.setNombre("Fox Hound");
		cliente2.setSexo('M');
		cliente2.setTipoPersona(0);
		cliente2.setIdentificacion(21526571);
		cliente2.setDireccion("Carrera 13b");
		cliente2.setSector(sector);
		cliente2.setTelefono("04120523025");
		cliente2.setFecha_de_nacimiento( DateUtil.getCurrentDate() );
		cliente2.setEmail("foxhound@gmail.com");
		cliente2.setPassword( "123" );
		cliente2.setRol(rol);
		cliente2.setTokenMovil("null");
		cliente2.setTipoCliente(tipoCliente);
		cliente2.setEstatus(0);
		cliente2.setFecha_creacion( DateUtil.getCurrentDate() );
		clienteService.saveOrUpdate(cliente2);
		
		// Profesion
		Profesion profesion = new Profesion();
		profesion.setDescripcion("Ingeniero en Informatica");
		profesion.setEstatus(0);
		profesion.setFecha_creacion( DateUtil.getCurrentDate() );
		profesionService.saveOrUpdate(profesion);
		
		Profesion profesion2 = new Profesion();
		profesion2.setDescripcion("Ingeniero en Negocios");
		profesion2.setEstatus(0);
		profesion2.setFecha_creacion( DateUtil.getCurrentDate() );
		profesionService.saveOrUpdate(profesion2);
		
		// Uso Inmueble
		UsoInmueble usoInmueble = new UsoInmueble();
		usoInmueble.setDescripcion("Alquiler");
		usoInmueble.setEstatus(0);
		usoInmueble.setFecha_creacion( DateUtil.getCurrentDate() );
		usoInmuebleService.saveOrUpdate(usoInmueble);
		
		// Categoria Inmueble
		CategoriaInmueble categoriaInmueble = new CategoriaInmueble();
		categoriaInmueble.setDescripcion("Casa");
		categoriaInmueble.setEstatus(0);
		categoriaInmueble.setFecha_creacion( DateUtil.getCurrentDate() );
		categoriaInmuebleService.saveOrUpdate(categoriaInmueble);
		
		CategoriaInmueble categoriaInmueble1 = new CategoriaInmueble();
		categoriaInmueble1.setDescripcion("Edificio");
		categoriaInmueble1.setEstatus(0);
		categoriaInmueble1.setFecha_creacion( DateUtil.getCurrentDate() );
		categoriaInmuebleService.saveOrUpdate(categoriaInmueble1);
		
		// Tipo Inmueble
		TipoInmueble tipoInmueble = new TipoInmueble();
		tipoInmueble.setDescripcion("Casa");
		tipoInmueble.setEstatus(0);
		tipoInmueble.setCategoriaInmueble(categoriaInmueble);
		tipoInmueble.setFecha_creacion( DateUtil.getCurrentDate() );
		tipoInmuebleService.saveOrUpdate(tipoInmueble);
		
		TipoInmueble tipoInmueble2 = new TipoInmueble();
		tipoInmueble2.setDescripcion("Edificio");
		tipoInmueble2.setEstatus(0);
		tipoInmueble2.setCategoriaInmueble(categoriaInmueble);
		tipoInmueble2.setFecha_creacion( DateUtil.getCurrentDate() );
		tipoInmuebleService.saveOrUpdate(tipoInmueble2);
		
		TipoInmueble tipoInmueble3 = new TipoInmueble();
		tipoInmueble3.setDescripcion("Apartamento");
		tipoInmueble3.setEstatus(0);
		tipoInmueble3.setCategoriaInmueble(categoriaInmueble);
		tipoInmueble3.setFecha_creacion( DateUtil.getCurrentDate() );
		tipoInmuebleService.saveOrUpdate(tipoInmueble3);
		
		// Inmueble
		Inmueble inmueble = new Inmueble();
		inmueble.setCliente(cliente);
		inmueble.setTipoInmueble(tipoInmueble);
		inmueble.setUsoInmueble(usoInmueble);
		inmueble.setSector(sector);
		inmueble.setDireccion("carrera 13c");
		inmueble.setDescripcion("mi casita");
		inmueble.setEstatus(0);
		inmueble.setFecha_creacion( DateUtil.getCurrentDate() );
		inmuebleService.saveOrUpdate(inmueble);
		
		Inmueble inmueble2 = new Inmueble();
		inmueble2.setCliente(cliente2);
		inmueble2.setTipoInmueble(tipoInmueble);
		inmueble2.setUsoInmueble(usoInmueble);
		inmueble2.setSector(sector);
		inmueble2.setDireccion("carrera 13c");
		inmueble2.setDescripcion("mi casita");
		inmueble2.setEstatus(0);
		inmueble2.setFecha_creacion( DateUtil.getCurrentDate() );
		inmuebleService.saveOrUpdate(inmueble2);
		
		
		//TipoCaracteristica
		TipoCaracteristica tipocaracteristica = new TipoCaracteristica();
		tipocaracteristica.setEstatus(0);
		tipocaracteristica.setFecha_creacion(DateUtil.getCurrentDate());
		tipocaracteristica.setDescripcion("color");
		tipocaracteristicaService.saveOrUpdate(tipocaracteristica);
		
		
		// Caracteristicas
		Caracteristica caracteristica = new Caracteristica();
		caracteristica.setEstatus(0);
		caracteristica.setFecha_creacion(DateUtil.getCurrentDate());
		caracteristica.setDescripcion("Pared de color rojo");
		caracteristica.setTipoCaracteristica(tipocaracteristica);
		caracteristicaService.saveOrUpdate(caracteristica);
		
		Caracteristica caracteristica2 = new Caracteristica();
		caracteristica2.setEstatus(0);
		caracteristica2.setFecha_creacion(DateUtil.getCurrentDate());
		caracteristica2.setDescripcion("Pared de color marron");
		caracteristica2.setTipoCaracteristica(tipocaracteristica);
		caracteristicaService.saveOrUpdate(caracteristica2);
		
		Caracteristica caracteristica3 = new Caracteristica();
		caracteristica3.setEstatus(0);
		caracteristica3.setFecha_creacion(DateUtil.getCurrentDate());
		caracteristica3.setDescripcion("Pared de piedra");
		caracteristica3.setTipoCaracteristica(tipocaracteristica);
		caracteristicaService.saveOrUpdate(caracteristica3);
		
		//Ubicacion
		Ubicacion ubicacion = new Ubicacion();
		ubicacion.setEstatus(0);
		ubicacion.setDescripcion("sala");
		ubicacion.setFecha_creacion(DateUtil.getCurrentDate());
		ubicacionService.saveOrUpdate(ubicacion);
		
		//tipoCaracteristicaInmueble
		
		TipoCaracteristicaInmueble tc = new TipoCaracteristicaInmueble();
		tc.setEstatus(0);
		tc.setFecha_creacion(DateUtil.getCurrentDate());
         tc.setTipoCaracteristica(tipocaracteristica);
         tc.setTipoInmueble(tipoInmueble);
         tipocaracteristicaService1.saveOrUpdate(tc);
         
         
		
		//CaracteristicaInmueble
        
		CaracteristicaInmueble ci = new CaracteristicaInmueble(); 
        ci.setEstatus(0);
        ci.setFecha_creacion(DateUtil.getCurrentDate());
        ci.setCaracteristica(caracteristica);
        ci.setInmueble(inmueble);
        ci.setUbicacion(ubicacion);
        caracteristicainmService.saveOrUpdate(ci);
        
		CaracteristicaInmueble ci2 = new CaracteristicaInmueble(); 
        ci2.setEstatus(0);
        ci2.setFecha_creacion(DateUtil.getCurrentDate());
        ci2.setCaracteristica(caracteristica2);
        ci2.setInmueble(inmueble2);
        ci2.setUbicacion(ubicacion);
        caracteristicainmService.saveOrUpdate(ci2);
        
		CaracteristicaInmueble ci3 = new CaracteristicaInmueble(); 
        ci3.setEstatus(0);
        ci3.setFecha_creacion(DateUtil.getCurrentDate());
        ci3.setCaracteristica(caracteristica3);
        ci3.setInmueble(inmueble);
        ci3.setUbicacion(ubicacion);
        caracteristicainmService.saveOrUpdate(ci2);
        
		CaracteristicaInmueble ci4 = new CaracteristicaInmueble(); 
        ci4.setEstatus(0);
        ci4.setFecha_creacion(DateUtil.getCurrentDate());
        ci4.setCaracteristica(caracteristica);
        ci4.setInmueble(inmueble2);
        ci4.setUbicacion(ubicacion);
        caracteristicainmService.saveOrUpdate(ci4);
        
		CaracteristicaInmueble ci5 = new CaracteristicaInmueble(); 
        ci5.setEstatus(0);
        ci5.setFecha_creacion(DateUtil.getCurrentDate());
        ci5.setCaracteristica(caracteristica2);
        ci5.setInmueble(inmueble2);
        ci5.setUbicacion(ubicacion);
        caracteristicainmService.saveOrUpdate(ci5); 
        
		
        //Condicion
        Condicion condicion = new Condicion();
        condicion.setEstatus(0);
        condicion.setFecha_creacion(DateUtil.getCurrentDate());
        condicion.setDescripcion("mala condicion");
        condicionService.saveOrUpdate(condicion);
        
        
        
        
        //CondicionInmueble
        CondicionInmueble cI = new CondicionInmueble();
        cI.setEstatus(0);
        cI.setFecha_creacion(DateUtil.getCurrentDate());
        cI.setDescripcion("perdida de color");
        cI.setCondicion(condicion);
        condicioninmuebleService.saveOrUpdate(cI);
        
        //TipodiagnosticoVisita
        TipoDiagnosticoVisita tipodiagnosticovisita = new TipoDiagnosticoVisita();
        tipodiagnosticovisita.setEstatus(0);
        tipodiagnosticovisita.setFecha_creacion(DateUtil.getCurrentDate());
        tipodiagnosticovisita.setDescripcion("Por servicio");
        tipodiagnosticovisitaService.saveOrUpdate(tipodiagnosticovisita);
        
        //DiagnosticoVisita
        DiagnosticoVisita dv = new DiagnosticoVisita();
        dv.setEstatus(0);
        dv.setFecha_creacion(DateUtil.getCurrentDate());
        dv.setDescripcion("se realizaron arreglos a la pared");
        dv.setTipoDiagnosticoVisita(tipodiagnosticovisita);
        diagnosticoservicioService.saveOrUpdate(dv);
     
        
        //Condicion Diagnostico
		
		CondicionDiagnostico cd = new CondicionDiagnostico();
		cd.setEstatus(0);
		cd.setFecha_creacion(DateUtil.getCurrentDate());
		cd.setCaracteristicaInmueble(ci);
		cd.setCondicionInmueble(cI);
		cd.setDiagnosticoVisita(dv);
		condiciondiagnosticoService.saveOrUpdate(cd);
	
		 
		
		
		
		//DetalleDiagnosticoVisita
		
		DetalleDiagnosticoVisita dd = new DetalleDiagnosticoVisita();
		dd.setEstatus(0);
		dd.setFecha_creacion(DateUtil.getCurrentDate());
		dd.setArea(88);
		dd.setCondicionDiagnostico(cd);
		detalledvService.saveOrUpdate(dd);
		
		
		// Empresa
		Empresa empresa = new Empresa();
		empresa.setRazonSocial("Una empresa de encargada de brindar mantenimineto y reparacion a inmuebles");
		empresa.setRif("J-0021526571");
		empresa.setQuienesSomos("lo maximo");
		empresa.setMision("Misionsita");
		empresa.setVision("ser los numeros 1");
		empresa.setValores("humirdaj");
		empresa.setLogo("");
		empresa.setEstatus(0);
		empresa.setFecha_creacion( DateUtil.getCurrentDate() );
		empresaService.saveOrUpdate(empresa);
		
		//Unidad Medida
		UnidadMedida unidadMedida = new UnidadMedida();
		unidadMedida.setDescripcion("mts2");
		unidadMedida.setEstatus(0);
		unidadMedida.setFecha_creacion( DateUtil.getCurrentDate() );
		unidadMedidaService.saveOrUpdate(unidadMedida);
		
		//Tipo Servicio
		TipoServicio tipoServicio = new TipoServicio();
		tipoServicio.setDescripcion("Jardineria");
		tipoServicio.setImagenServicio("imagen/p4.jpg");
		tipoServicio.setCategoria(categoria);
		tipoServicio.setEstatus(0);
		tipoServicio.setFecha_creacion( DateUtil.getCurrentDate() );
		tipoServicioService.saveOrUpdate(tipoServicio);
		
		TipoServicio tipoServicio2 = new TipoServicio();
		tipoServicio2.setDescripcion("Pintura");
		tipoServicio2.setImagenServicio("imagen/p4.jpg");
		tipoServicio2.setCategoria(categoria2);
		tipoServicio2.setEstatus(0);
		tipoServicio2.setFecha_creacion( DateUtil.getCurrentDate() );
		tipoServicioService.saveOrUpdate(tipoServicio2);
		
		TipoServicio tipoServicio3 = new TipoServicio();
		tipoServicio3.setDescripcion("Electricidad");
		tipoServicio3.setImagenServicio("imagen/p4.jpg");
		tipoServicio3.setCategoria(categoria3);
		tipoServicio3.setEstatus(0);
		tipoServicio3.setFecha_creacion( DateUtil.getCurrentDate() );
		tipoServicioService.saveOrUpdate(tipoServicio3);
		
		TipoServicio tipoServicio4 = new TipoServicio();
		tipoServicio4.setDescripcion("Limpieza");
		tipoServicio4.setImagenServicio("imagen/p4.jpg");
		tipoServicio4.setCategoria(categoria);
		tipoServicio4.setEstatus(0);
		tipoServicio4.setFecha_creacion( DateUtil.getCurrentDate() );
		tipoServicioService.saveOrUpdate(tipoServicio4);
		
		// Garantia
		Garantia garantia = new Garantia();
		garantia.setTitulo("Garantia Pisos");
		garantia.setValido(30);
		garantia.setDescripcion("pisos");
		garantia.setEstatus(0);
		garantia.setFecha_creacion( DateUtil.getCurrentDate() );
		garantiaService.saveOrUpdate(garantia);
		
		// CondicionGarantia
		CondicionGarantia condicionGarantia = new CondicionGarantia();
		condicionGarantia.setDescripcion("Pisos rotos");
		condicionGarantia.setEstatus(0);
		condicionGarantia.setGarantia(garantia);
		condicionGarantia.setFecha_creacion( DateUtil.getCurrentDate() );
		condicionGarantiaService.saveOrUpdate(condicionGarantia);
		
		CondicionGarantia condicionGarantia2 = new CondicionGarantia();
		condicionGarantia2.setDescripcion("Pisos agrietado");
		condicionGarantia2.setEstatus(0);
		condicionGarantia2.setGarantia(garantia);
		condicionGarantia2.setFecha_creacion( DateUtil.getCurrentDate() );
		condicionGarantiaService.saveOrUpdate(condicionGarantia2);
		
		// Servicio
		Servicio servicio = new Servicio();
		servicio.setGarantia(garantia);
		servicio.setTipoServicio(tipoServicio);
		servicio.setUnidadMedida(unidadMedida);
		servicio.setEmpresa(empresa);
		servicio.setTitulo("Reparacion de Pisos");
		servicio.setImagenServicio("imagen/p4.jpg");
		servicio.setDescripcion("Reparalo ya.");
		servicio.setCosto(200.00);
		servicio.setEstatus(0);
		servicio.setFecha_creacion( DateUtil.getCurrentDate() );
		servicioService.saveOrUpdate(servicio);
		
		Servicio servicio2 = new Servicio();
		servicio2.setGarantia(garantia);
		servicio2.setTipoServicio(tipoServicio4);
		servicio2.setUnidadMedida(unidadMedida);
		servicio2.setEmpresa(empresa);
		servicio2.setTitulo("Mantenimiento de Platabanda");
		servicio2.setImagenServicio("imagen/p2.jpg");
		servicio2.setDescripcion("Reparalo ya: filtros, grietas, rayones y mucho mas..");
		servicio2.setCosto(200.00);
		servicio2.setEstatus(0);
		servicio2.setFecha_creacion( DateUtil.getCurrentDate() );
		servicioService.saveOrUpdate(servicio2);
		
		Servicio servicio3 = new Servicio();
		servicio3.setGarantia(garantia);
		servicio3.setTipoServicio(tipoServicio3);
		servicio3.setUnidadMedida(unidadMedida);
		servicio3.setEmpresa(empresa);
		servicio3.setTitulo("Mantenimiento de Platabanda");
		servicio3.setImagenServicio("imagen/p2.jpg");
		servicio3.setDescripcion("Reparalo ya: filtros, grietas, rayones y mucho mas..");
		servicio3.setCosto(200.00);
		servicio3.setEstatus(0);
		servicio3.setFecha_creacion( DateUtil.getCurrentDate() );
		servicioService.saveOrUpdate(servicio3);
		
		Servicio servicio4 = new Servicio();
		servicio4.setGarantia(garantia);
		servicio4.setTipoServicio(tipoServicio2);
		servicio4.setUnidadMedida(unidadMedida);
		servicio4.setEmpresa(empresa);
		servicio4.setTitulo("Reparacion de Pisos");
		servicio4.setImagenServicio("imagen/p4.jpg");
		servicio4.setDescripcion("Reparalo ya.");
		servicio4.setCosto(200.00);
		servicio4.setEstatus(0);
		servicio4.setFecha_creacion( DateUtil.getCurrentDate() );
		servicioService.saveOrUpdate(servicio4);
		
//		Servicio servicio5 = new Servicio();
//		servicio5.setGarantia(garantia);
//		servicio5.setTipoServicio(tipoServicio3);
//		servicio5.setUnidadMedida(unidadMedida);
//		servicio5.setEmpresa(empresa);
//		servicio5.setTitulo("Reparacion de Pisos");
//		servicio5.setImagenServicio("imagen/p4.jpg");
//		servicio5.setDescripcion("Reparalo ya.");
//		servicio5.setCosto(200.00);
//		servicio5.setEstatus(0);
//		servicio5.setFecha_creacion( DateUtil.getCurrentDate() );
//		servicioService.saveOrUpdate(servicio5);
//		
//		Servicio servicio6 = new Servicio();
//		servicio6.setGarantia(garantia);
//		servicio6.setTipoServicio(tipoServicio4);
//		servicio6.setUnidadMedida(unidadMedida);
//		servicio6.setEmpresa(empresa);
//		servicio6.setTitulo("Reparacion de Pisos");
//		servicio6.setImagenServicio("imagen/p4.jpg");
//		servicio6.setDescripcion("Reparalo ya.");
//		servicio6.setCosto(200.00);
//		servicio6.setEstatus(0);
//		servicio6.setFecha_creacion( DateUtil.getCurrentDate() );
//		servicioService.saveOrUpdate(servicio6);
		
		// tipoCaracteristica
		TipoCaracteristica tipoCaracteristica = new TipoCaracteristica();
		tipoCaracteristica.setDescripcion("Edificio");
		tipoCaracteristica.setEstatus(0);
		tipoCaracteristica.setFecha_creacion( DateUtil.getCurrentDate() );
		tipoCaracteristicaService.saveOrUpdate(tipoCaracteristica);
		
		//tipoCaracteristicaInmueble
		TipoCaracteristicaInmueble tipoCaracteristicaInmueble = new TipoCaracteristicaInmueble();
		tipoCaracteristicaInmueble.setTipoInmueble(tipoInmueble);
		tipoCaracteristicaInmueble.setTipoCaracteristica(tipoCaracteristica);
		tipoCaracteristicaInmuebleService.saveOrUpdate(tipoCaracteristicaInmueble);
		
		TipoCaracteristicaInmueble tipoCaracteristicaInmueble2 = new TipoCaracteristicaInmueble();
		tipoCaracteristicaInmueble2.setTipoInmueble(tipoInmueble2);
		tipoCaracteristicaInmueble2.setTipoCaracteristica(tipoCaracteristica);
		tipoCaracteristicaInmuebleService.saveOrUpdate(tipoCaracteristicaInmueble2);
		
		TipoCaracteristicaInmueble tipoCaracteristicaInmueble3 = new TipoCaracteristicaInmueble();
		tipoCaracteristicaInmueble3.setTipoInmueble(tipoInmueble);
		tipoCaracteristicaInmueble3.setTipoCaracteristica(tipoCaracteristica);
		tipoCaracteristicaInmuebleService.saveOrUpdate(tipoCaracteristicaInmueble3);
		
		// DetalleServicioInmueble
		DetalleServicioInmueble detalleServicioInmueble = new DetalleServicioInmueble();
		detalleServicioInmueble.setTipoCaracteristicaInmueble(tipoCaracteristicaInmueble);
		detalleServicioInmueble.setServicio(servicio);
		detalleServicioInmuebleService.saveOrUpdate(detalleServicioInmueble);
		
		DetalleServicioInmueble detalleServicioInmueble2 = new DetalleServicioInmueble();
		detalleServicioInmueble2.setTipoCaracteristicaInmueble(tipoCaracteristicaInmueble2);
		detalleServicioInmueble2.setServicio(servicio2);
		detalleServicioInmuebleService.saveOrUpdate(detalleServicioInmueble2);
		
		DetalleServicioInmueble detalleServicioInmueble3 = new DetalleServicioInmueble();
		detalleServicioInmueble3.setTipoCaracteristicaInmueble(tipoCaracteristicaInmueble3);
		detalleServicioInmueble3.setServicio(servicio3);
		detalleServicioInmuebleService.saveOrUpdate(detalleServicioInmueble3);
		
		DetalleServicioInmueble detalleServicioInmueble4 = new DetalleServicioInmueble();
		detalleServicioInmueble4.setTipoCaracteristicaInmueble(tipoCaracteristicaInmueble);
		detalleServicioInmueble4.setServicio(servicio4);
		detalleServicioInmuebleService.saveOrUpdate(detalleServicioInmueble4);
		
		DetalleServicioInmueble detalleServicioInmueble5 = new DetalleServicioInmueble();
		detalleServicioInmueble5.setTipoCaracteristicaInmueble(tipoCaracteristicaInmueble2);
		detalleServicioInmueble5.setServicio(servicio);
		detalleServicioInmuebleService.saveOrUpdate(detalleServicioInmueble5);
		
		DetalleServicioInmueble detalleServicioInmueble6 = new DetalleServicioInmueble();
		detalleServicioInmueble6.setTipoCaracteristicaInmueble(tipoCaracteristicaInmueble);
		detalleServicioInmueble6.setServicio(servicio2);
		detalleServicioInmuebleService.saveOrUpdate(detalleServicioInmueble6);
		
		// TipoPromocion
		TipoPromocion tipoPromocion = new TipoPromocion();
		tipoPromocion.setDescripcion("Días de las Madres");
		tipoPromocion.setEstatus(0);
		tipoPromocion.setFecha_creacion( DateUtil.getCurrentDate() );
		tipoPromocionService.saveOrUpdate(tipoPromocion);
		
		// Descuento
		Descuento descuento = new Descuento();
		descuento.setDescripcion("Días de las Madres");
		descuento.setPorcentaje(0.43);
		descuento.setEstatus(0);
		descuento.setFecha_creacion( DateUtil.getCurrentDate() );
		descuentoService.saveOrUpdate(descuento);
		
		// Promocion
		Promocion promocion = new Promocion();
		promocion.setTitulo("Promoción del Pisos para el Dia de las Madres");
		promocion.setDescripcion("Limpiamos rapidos sus pisos, tan limpios y puros como el corazon de sus seres queridos");
		promocion.setFecha_inicio( DateUtil.getCurrentDate() );
		
		Calendar cal = Calendar.getInstance(); 
		cal.add(Calendar.MONTH, 1);
		promocion.setFecha_caducidad( cal.getTime() );
		promocion.setImagenServicio("imagen/p2.jpg");
		promocion.setTipoPromocion(tipoPromocion);
		promocion.setDescuento(descuento);
		promocion.setEstatus(0);
		promocion.setFecha_creacion( DateUtil.getCurrentDate() );
		promocionService.saveOrUpdate(promocion);
		
		// PromocionServicio
		PromocionServicio promocionServicio = new PromocionServicio();
		promocionServicio.setPromocion(promocion);
		promocionServicio.setServicio(servicio);
		promocion.setEstatus(0);
		promocion.setFecha_creacion( DateUtil.getCurrentDate() );
		promocionServicioService.saveOrUpdate(promocionServicio);
		
		// Noticia
		Noticia noticia = new Noticia();
		noticia.setTitulo("Mañana habra mantenimiento del sistema");
		noticia.setDescripcion("Por cuestiones de mantenimiento del ascensor no prestaremos servicios, pedimos disculpas por las molestias ocacionadas");
		noticia.setImgNoticia("imagen/p2.jpg");
		noticia.setEmpresa(empresa);
		noticia.setEstatus(0);
		noticia.setFecha_caducidad(cal.getTime());
		noticia.setFecha_creacion( DateUtil.getCurrentDate() );
		noticiaService.saveOrUpdate(noticia);
		
		Noticia noticia2 = new Noticia();
		noticia2.setTitulo("Fulano consigue record Guiness de barredor.");
		noticia2.setDescripcion("Por cuestiones de mantenimiento del ascensor no prestaremos servicios, pedimos disculpas por las molestias ocacionadas");
		noticia2.setImgNoticia("imagen/p2.jpg");
		noticia2.setEmpresa(empresa);
		noticia2.setEstatus(0);
		noticia2.setFecha_caducidad(cal.getTime());
		noticia2.setFecha_creacion( DateUtil.getCurrentDate() );
		noticiaService.saveOrUpdate(noticia2);
		
		Noticia noticia3 = new Noticia();
		noticia3.setTitulo("Conoce a nuestra aplicación móvil");
		noticia3.setDescripcion("At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleni.");
		noticia3.setImgNoticia("imagen/p2.jpg");
		noticia3.setEmpresa(empresa);
		noticia3.setEstatus(0);
		noticia3.setFecha_caducidad(cal.getTime());
		noticia3.setFecha_creacion( DateUtil.getCurrentDate() );
		noticiaService.saveOrUpdate(noticia3);
		
		Noticia noticia4 = new Noticia();
		noticia4.setTitulo("Fulano consigue record Guiness de albaliñeria");
		noticia4.setDescripcion("Por cuestiones de mantenimiento del ascensor no prestaremos servicios, pedimos disculpas por las molestias ocacionadas");
		noticia4.setImgNoticia("imagen/p2.jpg");
		noticia4.setEmpresa(empresa);
		noticia4.setEstatus(0);
		noticia4.setFecha_caducidad(cal.getTime());
		noticia4.setFecha_creacion( DateUtil.getCurrentDate() );
		noticiaService.saveOrUpdate(noticia4);
		
		// Tipo Orden Servicio
		TipoOrdenServicio tipoOrdenServicio = new TipoOrdenServicio();
		tipoOrdenServicio.setDescripcion("Servicios");
		tipoOrdenServicio.setEstatus(0);
		tipoOrdenServicio.setFecha_creacion( DateUtil.getCurrentDate() );
		tipoOrdenServicioService.saveOrUpdate(tipoOrdenServicio);
		
		// Orden de servicio
		OrdenServicio ordenServicio = new OrdenServicio();
		ordenServicio.setFechaInicio( DateUtil.getCurrentDate()  );
		ordenServicio.setFechaCulminacion(cal.getTime());
		ordenServicio.setTipoOrdenServicio(tipoOrdenServicio);
		ordenServicio.setEstatus(0);
		ordenServicio.setFecha_creacion( DateUtil.getCurrentDate() );
		ordenServicioService.saveOrUpdate(ordenServicio);

		// Tipo Eventualidad
		TipoEventualidad tipoEventualidad = new TipoEventualidad();
		tipoEventualidad.setDescripcion("Daños");
		tipoEventualidad.setEstatus(0);
		tipoEventualidad.setFecha_creacion( DateUtil.getCurrentDate() );
		tipoEventualidadService.saveOrUpdate(tipoEventualidad);
		
		// Eventualidad
		Eventualidad eventualidad = new Eventualidad();
		eventualidad.setDescripcion("Daños");
		eventualidad.setTipoEventualidad(tipoEventualidad);
		eventualidad.setEstatus(0);
		eventualidad.setFecha_creacion( DateUtil.getCurrentDate() );
		eventualidadService.saveOrUpdate(eventualidad);
		
		// Ocupacion
		Ocupacion ocupacion = new Ocupacion();
		ocupacion.setDescripcion("Docente");
		ocupacion.setEstatus(0);
		ocupacion.setFecha_creacion( DateUtil.getCurrentDate() );
		ocupacionService.saveOrUpdate(ocupacion);
		
		Ocupacion ocupacion2 = new Ocupacion();
		ocupacion2.setDescripcion("Ama de Casa");
		ocupacion2.setEstatus(0);
		ocupacion2.setFecha_creacion( DateUtil.getCurrentDate() );
		ocupacionService.saveOrUpdate(ocupacion2);
		
		// Turno
		Turno turno = new Turno();
		turno.setDescripcion("Mañana");
		turno.setEstatus(0);
		turno.setFecha_creacion( DateUtil.getCurrentDate() );
		turnoService.saveOrUpdate(turno);
		
		// TipoVisita
		TipoVisita tipoVisita = new TipoVisita();
		tipoVisita.setDescripcion("Diagnostica");
		tipoVisita.setEstatus(0);
		tipoVisita.setFecha_creacion( DateUtil.getCurrentDate() );
		tipoVisitaService.saveOrUpdate(tipoVisita);
		
		// Solicitud
		Solicitud solicitud = new Solicitud();
		solicitud.setInmueble(inmueble);
		solicitud.setEstatus(0);
		solicitud.setFecha_creacion( DateUtil.getCurrentDate() );
		solicitudService.saveOrUpdate(solicitud);

//		
//		Solicitud solicitud1 = new Solicitud();
//		solicitud1.setInmueble(inmueble);
//		solicitud1.setEstatus(0);
//		solicitud1.setFecha_creacion( DateUtil.getCurrentDate() );
//		solicitudService.saveOrUpdate(solicitud1);
//		
		//TipoMotivo
		TipoMotivo tipomotivo = new TipoMotivo();
		tipomotivo.setEstatus(0);
		tipomotivo.setFecha_creacion(DateUtil.getCurrentDate());
		tipomotivo.setDescripcion("Deterioro del piso de la cocina");
		tipomotivo.setFecha_creacion( DateUtil.getCurrentDate() );
		tipoMotivoService.saveOrUpdate(tipomotivo);
		
		//Motivo
		Motivo motivo = new Motivo();
		motivo.setEstatus(0);
		motivo.setFecha_creacion(DateUtil.getCurrentDate());
		motivo.setDescripcion("Deterioro del piso de la cocina");
		motivo.setTipoMotivo(tipomotivo);
		motivo.setFecha_creacion( DateUtil.getCurrentDate() );
		motivoService.saveOrUpdate(motivo);		
		
		//SolicitudServicio
		SolicitudServicio solicitudServicio= new SolicitudServicio();
		solicitudServicio.setEstatus(0);
		solicitudServicio.setFecha_creacion(DateUtil.getCurrentDate());
		solicitudServicio.setServicio(servicio);
		solicitudServicio.setSolicitud(solicitud);
		solicitudservicioservice.saveOrUpdate(solicitudServicio);


		//SolicitudServicioMotivo
		SolicitudServicioMotivo solicitudServicioMotivo = new SolicitudServicioMotivo();
		solicitudServicioMotivo.setEstatus(0);
		solicitudServicioMotivo.setFecha_creacion(DateUtil.getCurrentDate());
		solicitudServicioMotivo.setMotivo(motivo);
		solicitudServicioMotivo.setSolicitudServicio(solicitudServicio);
		solicitudServicioMotivoService.saveOrUpdate(solicitudServicioMotivo);
//		
//		//DetallePresupuesto
//		
//		DetallePresupuesto d= new DetallePresupuesto();
//	    d.setEstatus(0);
//	    d.setFecha_creacion(DateUtil.getCurrentDate());
//	    d.setCosto(3000);
//		d.setDetalleDiagnosticoVisita(dd);
//		d.setPresupuesto(p);
//		d.setSolicitudServicio(solicitudServicio);
//		detallePresupuestoService.saveOrUpdate(d);


		// Visita
//		Visita visita = new Visita();
//		visita.setFechaVisita(DateUtil.getCurrentDate());
//		visita.setTurno(turno);
//		visita.setTipoVisita(tipoVisita);
//		visita.setSolicitud(solicitud);
//		visita.setOrdenServicio(ordenServicio);
//		visita.setEstatus(0);
//		visita.setFecha_creacion( DateUtil.getCurrentDate() );
//		visitaService.saveOrUpdate(visita);
		
	
		// Trabajador
		Trabajador trabajador = new Trabajador();
		trabajador.setNombre("Jose Duin");
		trabajador.setSexo('M');
		trabajador.setTipoPersona(0);
		trabajador.setCargo(cargo);
		trabajador.setIdentificacion(21526571);
		trabajador.setDireccion("Carrera 13b");
		trabajador.setSector(sector);
		trabajador.setTelefono("04120523025");
		trabajador.setFecha_de_nacimiento( DateUtil.getCurrentDate() );
		trabajador.setEmail("jose@duin.com");
		trabajador.setPassword( "123" );
		trabajador.setRol(rol);
		trabajador.setEstatus(0);
		trabajador.setFecha_creacion( DateUtil.getCurrentDate() );
		trabajador.setEmpresa(empresa);
		trabajador.setFecha_ingreso(DateUtil.getCurrentDate());
		trabajadorService.saveOrUpdate(trabajador);
//		
//		Trabajador trabajador2 = new Trabajador();
//		trabajador2.setNombre("Jose Miguel");
//		trabajador2.setSexo('M');
//		trabajador2.setTipoPersona(0);
//		trabajador2.setCargo(cargo);
//		trabajador2.setIdentificacion(21526571);
//		trabajador2.setDireccion("Carrera 13b");
//		trabajador2.setSector(sector);
//		trabajador2.setTelefono("04120523025");
//		trabajador2.setFecha_de_nacimiento( DateUtil.getCurrentDate() );
//		trabajador2.setEmail("jose@miguel.com");
//		trabajador2.setPassword( "123" );
//		trabajador2.setRol(rol2);
//		trabajador2.setEstatus(0);
//		trabajador2.setFecha_creacion( DateUtil.getCurrentDate() );
//		trabajador2.setEmpresa(empresa);
//		trabajador2.setFecha_ingreso(DateUtil.getCurrentDate());
//		trabajadorService.saveOrUpdate(trabajador2);
//		
//		Trabajador trabajador3 = new Trabajador();
//		trabajador3.setNombre("Luis Medina");
//		trabajador3.setSexo('M');
//		trabajador3.setTipoPersona(0);
//		trabajador3.setCargo(cargo);
//		trabajador3.setIdentificacion(21526571);
//		trabajador3.setDireccion("Carrera 13b");
//		trabajador3.setSector(sector);
//		trabajador3.setTelefono("04120523025");
//		trabajador3.setFecha_de_nacimiento( DateUtil.getCurrentDate() );
//		trabajador3.setEmail("luis@medina.com");
//		trabajador3.setPassword( "123" );
//		trabajador3.setRol(rol2);
//		trabajador3.setEstatus(0);
//		trabajador3.setFecha_creacion( DateUtil.getCurrentDate() );
//		trabajador3.setEmpresa(empresa);
//		trabajador3.setFecha_ingreso(DateUtil.getCurrentDate());
//		trabajadorService.saveOrUpdate(trabajador3);
//		
//		Trabajador trabajador4 = new Trabajador();
//		trabajador4.setNombre("Pedro Medina");
//		trabajador4.setSexo('M');
//		trabajador4.setTipoPersona(0);
//		trabajador4.setCargo(cargo);
//		trabajador4.setIdentificacion(21526571);
//		trabajador4.setDireccion("Carrera 13b");
//		trabajador4.setSector(sector);
//		trabajador4.setTelefono("04120523025");
//		trabajador4.setFecha_de_nacimiento( DateUtil.getCurrentDate() );
//		trabajador4.setEmail("pedro@medina.com");
//		trabajador4.setPassword( "123" );
//		trabajador4.setRol(rol3);
//		trabajador4.setEstatus(0);
//		trabajador4.setFecha_creacion( DateUtil.getCurrentDate() );
//		trabajador4.setEmpresa(empresa);
//		trabajador4.setFecha_ingreso(DateUtil.getCurrentDate());
//		trabajadorService.saveOrUpdate(trabajador4);
//		
//		Trabajador trabajador5 = new Trabajador();
//		trabajador5.setNombre("Alfredo Medina");
//		trabajador5.setSexo('M');
//		trabajador5.setTipoPersona(0);
//		trabajador5.setCargo(cargo);
//		trabajador5.setIdentificacion(21526571);
//		trabajador5.setDireccion("Carrera 13b");
//		trabajador5.setSector(sector);
//		trabajador5.setTelefono("04120523025");
//		trabajador5.setFecha_de_nacimiento( DateUtil.getCurrentDate() );
//		trabajador5.setEmail("alfredo@medina.com");
//		trabajador5.setPassword( "123" );
//		trabajador5.setRol(rol3);
//		trabajador5.setEstatus(0);
//		trabajador5.setFecha_creacion( DateUtil.getCurrentDate() );
//		trabajador5.setEmpresa(empresa);
//		trabajador5.setFecha_ingreso(DateUtil.getCurrentDate());
//		trabajadorService.saveOrUpdate(trabajador5);
//		
		// TrabajadorVisita

//		TrabajadorVisita trabajadorVisita = new TrabajadorVisita();
//		trabajadorVisita.setTrabajador(trabajador);
//		trabajadorVisita.setVisita(visita);
//		trabajadorVisita.setEstatus(0);
//		trabajadorVisita.setFecha_creacion( DateUtil.getCurrentDate() );
//		trabajadorVisitaService.saveOrUpdate(trabajadorVisita);

		
		// AsuntoComentario
		AsuntoComentario asuntoComentario = new AsuntoComentario();
		asuntoComentario.setDescripcion("Sugerencia");
		asuntoComentario.setEstatus(0);
		asuntoComentario.setFecha_creacion( DateUtil.getCurrentDate() );
		asuntoComentarioService.saveOrUpdate(asuntoComentario);
		
		AsuntoComentario asuntoComentario2 = new AsuntoComentario();
		asuntoComentario2.setDescripcion("Queja");
		asuntoComentario2.setEstatus(0);
		asuntoComentario2.setFecha_creacion( DateUtil.getCurrentDate() );
		asuntoComentarioService.saveOrUpdate(asuntoComentario2);
		
		AsuntoComentario asuntoComentario3 = new AsuntoComentario();
		asuntoComentario3.setDescripcion("Opinión");
		asuntoComentario3.setEstatus(0);
		asuntoComentario3.setFecha_creacion( DateUtil.getCurrentDate() );
		asuntoComentarioService.saveOrUpdate(asuntoComentario3);
//
///*
//		 * ******************************************
//		 *          Aqui trabaja Mahola             *
//		 * ******************************************
//		 */
//		
//		//ASUNTO  COMENTARIO
//		AsuntoComentario asuntoComentario1 = new AsuntoComentario();
//		//asuntoComentario.setBuzonSugerencias(buzonSugerencias);;// PREGUNTAR A CHEMI
//		//asuntoComentario.setComentarioExternos(comentarioExternos);//  PREGUNTAR A CHEMI
//		asuntoComentario1.setDescripcion("descripcion del comentario externo");
//		asuntoComentario1.setEstatus(0);
//		asuntoComentario1.setFecha_creacion(DateUtil.getCurrentDate());
//		asuntoComentario1.setFecha_modificacion(cal.getTime());
//		asuntoComentarioService.saveOrUpdate(asuntoComentario1);
//		
//		// BUZON DE SUGERENCIA 
//		
//		BuzonSugerencia buzonSugerencia = new BuzonSugerencia();
//		buzonSugerencia.setAsuntoComentario(asuntoComentario1);
//		buzonSugerencia.setAsunto("Mal trato del personal");
//		buzonSugerencia.setDescripcion("No me gusta el personal que mandaron");
//		buzonSugerencia.setEstatus(0);
//		buzonSugerencia.setFecha_creacion(DateUtil.getCurrentDate());
//		buzonSugerencia.setFecha_modificacion(cal.getTime());
//		buzonSugerencia.setPersona(cliente);
//		buzonSugerenciaService.saveOrUpdate(buzonSugerencia);		
//
//		
//		//ASUNTO  COMENTARIO
//		AsuntoComentario asuntoComentario4 = new AsuntoComentario();
//		asuntoComentario4.setDescripcion("descripcion del comentario externo");
//		asuntoComentario4.setEstatus(0);
//		asuntoComentario4.setFecha_creacion(DateUtil.getCurrentDate());
//		asuntoComentario4.setFecha_modificacion(cal.getTime());
//		asuntoComentarioService.saveOrUpdate(asuntoComentario4);
//
//		// CALIFICAR SERVICIO
//		
//		CalificarServicio calificarServicio = new CalificarServicio();
//		calificarServicio.setDescripcion("Excelente");
//		calificarServicio.setEstatus(0);
//		calificarServicio.setFecha_creacion(DateUtil.getCurrentDate());
//		calificarServicio.setFecha_modificacion(cal.getTime());
//		calificarServicio.setServicio(servicio2);
//		calificarServicio.setValor(5);
//		calificarServicioService.saveOrUpdate(calificarServicio);
//		
//		// Tipo Caracteristica
//		TipoCaracteristica tipoCaracteristica1 = new TipoCaracteristica();
//		tipoCaracteristica1.setDescripcion("PenHouse");
//		tipoCaracteristica1.setEstatus(0);
//		tipoCaracteristica1.setFecha_creacion(DateUtil.getCurrentDate());
//		tipoCaracteristica1.setFecha_modificacion(cal.getTime());
//		tipoCaracteristicaService.saveOrUpdate(tipoCaracteristica1);
//		
//		TipoCaracteristica tipoCaracteristica2 = new TipoCaracteristica();
//		tipoCaracteristica2.setDescripcion("Cabaña");
//		tipoCaracteristica2.setEstatus(0);
//		tipoCaracteristica2.setFecha_creacion(DateUtil.getCurrentDate());
//		tipoCaracteristica2.setFecha_modificacion(cal.getTime());
//		tipoCaracteristicaService.saveOrUpdate(tipoCaracteristica2);
//		
//		// caracteristica
//		
//		Caracteristica caracteristica1 = new Caracteristica();
//		caracteristica1.setDescripcion("piso");
//		caracteristica1.setEstatus(0);
//		caracteristica1.setFecha_creacion(DateUtil.getCurrentDate());
//		caracteristica1.setFecha_modificacion(cal.getTime());
////		caracteristica.setPadre_descripcion("padre descripcion");
////		caracteristica.setTipoCaracteristica(tipoCaracteristica1);
//		caracteristicaService.saveOrUpdate(caracteristica1);
//		
//		// Ubicacion
//		Ubicacion ubicacion2 = new Ubicacion();
//		ubicacion2.setDescripcion("Cocina");
//		ubicacion2.setEstatus(0);
//		ubicacion2.setFecha_creacion(DateUtil.getCurrentDate());
//		ubicacion2.setFecha_modificacion(cal.getTime());
//		ubicacionService.saveOrUpdate(ubicacion2);
//		
//		// TipoCaracteristicaServicio
//		TipoCaracteristicaServicio tipoCaracteristicaServicio = new TipoCaracteristicaServicio();
//		tipoCaracteristicaServicio.setServicio(servicio);
//		tipoCaracteristicaServicio.setTipoCaracteristica(tipoCaracteristica1);
//		tipoCaracteristicaServicioService.saveOrUpdate(tipoCaracteristicaServicio);
//		
//		TipoCaracteristicaServicio tipoCaracteristicaServicio2 = new TipoCaracteristicaServicio();
//		tipoCaracteristicaServicio2.setServicio(servicio);
//		tipoCaracteristicaServicio2.setTipoCaracteristica(tipoCaracteristica2);
//		
//		TipoCaracteristicaServicio tipoCaracteristicaServicio3 = new TipoCaracteristicaServicio();
//		tipoCaracteristicaServicio3.setServicio(servicio2);
//		tipoCaracteristicaServicio3.setTipoCaracteristica(tipoCaracteristica1);
//		tipoCaracteristicaServicioService.saveOrUpdate(tipoCaracteristicaServicio3);
//		
//		TipoCaracteristicaServicio tipoCaracteristicaServicio4 = new TipoCaracteristicaServicio();
//		tipoCaracteristicaServicio4.setServicio(servicio3);
//		tipoCaracteristicaServicio4.setTipoCaracteristica(tipoCaracteristica2);
//		tipoCaracteristicaServicioService.saveOrUpdate(tipoCaracteristicaServicio4);
//		
//		TipoCaracteristicaServicio tipoCaracteristicaServicio5 = new TipoCaracteristicaServicio();
//		tipoCaracteristicaServicio5.setServicio(servicio4);
//		tipoCaracteristicaServicio5.setTipoCaracteristica(tipoCaracteristica1);
//		tipoCaracteristicaServicioService.saveOrUpdate(tipoCaracteristicaServicio5);
//		
//		TipoCaracteristicaServicio tipoCaracteristicaServicio6 = new TipoCaracteristicaServicio();
//		tipoCaracteristicaServicio6.setServicio(servicio3);
//		tipoCaracteristicaServicio6.setTipoCaracteristica(tipoCaracteristica1);
//		tipoCaracteristicaServicioService.saveOrUpdate(tipoCaracteristicaServicio6);
//		
//		//caracteristicaInmueble
//		
//		CaracteristicaInmueble caracteristicaInmueble = new CaracteristicaInmueble();
//		caracteristicaInmueble.setCaracteristica(caracteristica1);
//		caracteristicaInmueble.setEstatus(0);
//		caracteristicaInmueble.setFecha_creacion(DateUtil.getCurrentDate());
//		caracteristicaInmueble.setFecha_modificacion(cal.getTime());
//		caracteristicaInmueble.setInmueble(inmueble);
//		caracteristicaInmueble.setUbicacion(ubicacion2);
//		caracteristicaInmuebleService.saveOrUpdate(caracteristicaInmueble);
//		
//		// cargo tipo servicio
//		CargoTipoServicio cargoTipoServicio = new CargoTipoServicio();
//		cargoTipoServicio.setCargo(cargo);
//		cargoTipoServicio.setEstatus(0);
//		cargoTipoServicio.setFecha_creacion(DateUtil.getCurrentDate());
//		cargoTipoServicio.setFecha_modificacion(cal.getTime());
//		cargoTipoServicio.setTipoServicio(tipoServicio3);
//		cargoTipoServicioService.saveOrUpdate(cargoTipoServicio);
//		
//		//cliente profesion
//		
//		ClienteProfesion clienteProfesion = new ClienteProfesion();
//		clienteProfesion.setCliente(cliente2);
//		clienteProfesion.setEstatus(0);
//		clienteProfesion.setFecha_creacion(DateUtil.getCurrentDate());
//		clienteProfesion.setProfesion(profesion);
//		clienteProfesion.setFecha_modificacion(cal.getTime());
//		clienteProfesion.setProfesionStr("ingeniero");
//		clienteProfesionService.saveOrUpdate(clienteProfesion);
//				
//		//comentario externo
//		
//		ComentarioExterno comentarioExterno = new ComentarioExterno();
//		comentarioExterno.setAsuntoComentario(asuntoComentario1);
//		comentarioExterno.setCorreoEmisor("mhlyrda@gmail.com");
//		comentarioExterno.setDescripcion("descripcion del comentario");
//		comentarioExterno.setEstatus(0);
//		comentarioExterno.setFecha_creacion(DateUtil.getCurrentDate());
//		comentarioExterno.setFecha_modificacion(cal.getTime());
//		comentarioExternoService.saveOrUpdate(comentarioExterno);
//		
//		// condicion
//		Condicion condicion1 = new Condicion();
//		condicion1.setDescripcion("Buena");
//		condicion1.setEstatus(0);
//		condicion1.setFecha_creacion(DateUtil.getCurrentDate());
//		condicion1.setFecha_modificacion(cal.getTime());
//		condicionService.saveOrUpdate(condicion1);
//		
//		// condicionInmueble
//		//condicion 
//		
//		Condicion condicion11 = new Condicion();
//		//condicion.setCondicionInmueble(condicionInmueble);
//		condicion11.setDescripcion("condicion del inmueble");
//		condicion11.setEstatus(0);
//		condicion11.setFecha_creacion(DateUtil.getCurrentDate());
//		condicion11.setFecha_modificacion(cal.getTime());
//		condicionService.saveOrUpdate(condicion11);
//		
//		// condicion inmueble
//		CondicionInmueble condicionInmueble = new CondicionInmueble();
//		condicionInmueble.setDescripcion("Buena");
//		condicionInmueble.setEstatus(0);
//		condicionInmueble.setFecha_creacion(DateUtil.getCurrentDate());
//		condicionInmueble.setFecha_modificacion(cal.getTime());
//		condicionInmueble.setCondicion(condicion11);
//		condicionInmuebleService.saveOrUpdate(condicionInmueble);
//		
//		// tipoRespuesta
//		
//		// opcion
//		Opcion opcion = new Opcion();
//		opcion.setDescripcion("descripcion de la opcion");
//		opcion.setEstatus(0);
//		opcion.setFecha_creacion(DateUtil.getCurrentDate());
//		opcion.setFecha_modificacion(cal.getTime());
//		//opcion.setOpcionClientes(opcionClientes);
//		//opcion.setOpcionPreguntas(opcionPreguntas);
//		opcionService.saveOrUpdate(opcion);
//		
//		//opcion clientes
//		OpcionCliente opcionCliente = new OpcionCliente();
//		opcionCliente.setCliente(cliente);
//		opcionCliente.setEstatus(0);
//		opcionCliente.setFecha_creacion(DateUtil.getCurrentDate());
//		opcionCliente.setFecha_modificacion(cal.getTime());
//		opcionCliente.setOpcion(opcion);
//		
//		
//		
//		
//		
//		//pregunta
//		
//		Pregunta pregunta = new Pregunta();
//		pregunta.setDescripcion("descripcion Pregunta");
//		pregunta.setEstatus(0);
//		pregunta.setFecha_creacion(DateUtil.getCurrentDate());
//		pregunta.setFecha_modificacion(cal.getTime());
//		//pregunta.setOpcionPreguntas(opcionPreguntas);
//		preguntaService.saveOrUpdate(pregunta);
//		
//		// red social
//		
//		RedSocial redSocial = new RedSocial();
//		redSocial.setDescripcion("facebook");
//		redSocial.setEmpresa(empresa);
//		redSocial.setEstatus(0);
//		redSocial.setFecha_creacion(DateUtil.getCurrentDate());
//		redSocial.setFecha_modificacion(cal.getTime());
//		redSocialService.saveOrUpdate(redSocial);
//	
//		// rol funcion
//		
//		RolFuncion rolFuncion = new RolFuncion();
//		rolFuncion.setEstatus(0);
//		rolFuncion.setFecha_creacion(DateUtil.getCurrentDate());
//		rolFuncion.setRol(rol);
//		///rolFuncion.setMenu(menu);
//		rolFuncionesService.saveOrUpdate(rolFuncion);
//		
//		//solicitud eventualdad
//		
//		SolicitudEventualidad solicitudEventualidad = new SolicitudEventualidad();
//		solicitudEventualidad.setEstatus(0);
//		solicitudEventualidad.setEventualidad(eventualidad);
//		solicitudEventualidad.setFecha_creacion(DateUtil.getCurrentDate());
//		solicitudEventualidad.setFecha_modificacion(cal.getTime());
//		solicitudEventualidad.setSolicitud(solicitud);
//		solicitudEventualidadService.saveOrUpdate(solicitudEventualidad);
//		
//		// solicitud servicio
//		
//		SolicitudServicio solicitudServicio1 = new SolicitudServicio();
//		//solicitudServicio.setDetalleOrdenServicios(detalleOrdenServicios);
//		solicitudServicio1.setEstatus(0);
//		solicitudServicio1.setFecha_creacion(DateUtil.getCurrentDate());
//		solicitudServicio1.setFecha_modificacion(cal.getTime());
//		solicitudServicio1.setServicio(servicio2);
//		solicitudServicio1.setSolicitud(solicitud);
//		solicitudServicioService.saveOrUpdate(solicitudServicio1);
//
//		
//		// tarea
//		Tarea tarea = new Tarea();
//		tarea.setDescripcion("limpiar piso");
//		tarea.setEstatus(0);
//		tarea.setFecha_creacion(DateUtil.getCurrentDate());
//		tarea.setFecha_modificacion(cal.getTime());
//		tareaService.saveOrUpdate(tarea);
//		
//		// servicio tarea
//		
//		ServicioTarea servicioTarea = new ServicioTarea();
//		servicioTarea.setDescripcion("descripcion de sevicio tarea");
//		servicioTarea.setEstatus(0);
//		servicioTarea.setFecha_creacion(DateUtil.getCurrentDate());
//		servicioTarea.setFecha_modificacion(cal.getTime());
//		servicioTarea.setServicio(servicio2);
//		servicioTarea.setTarea(tarea);
//		servicioTareaService.saveOrUpdate(servicioTarea);
//		
//		// tipo Catactristica
//		
//		TipoCaracteristica tipoCaracteristica11 = new TipoCaracteristica();
//		tipoCaracteristica11.setDescripcion("piso");
//		tipoCaracteristica11.setEstatus(0);
//		tipoCaracteristica11.setFecha_creacion(DateUtil.getCurrentDate());
//		tipoCaracteristica11.setFecha_modificacion(cal.getTime());
//		tipoCaracteristicaService.saveOrUpdate(tipoCaracteristica11);
//		
//		//tipoCaracteristica inmueble
//		TipoCaracteristicaInmueble tipoCaracteristicaInmueble1 = new TipoCaracteristicaInmueble();
//		tipoCaracteristicaInmueble1.setEstatus(0);
//		tipoCaracteristicaInmueble1.setFecha_creacion(DateUtil.getCurrentDate());
//		tipoCaracteristicaInmueble1.setFecha_modificacion(cal.getTime());
//		tipoCaracteristicaInmueble1.setTipoCaracteristica(tipoCaracteristica11);
//		tipoCaracteristicaInmueble1.setTipoInmueble(tipoInmueble);
//		tipoCaracteristicaInmuebleService.saveOrUpdate(tipoCaracteristicaInmueble1);
//		
//		//tipo caracteristica servicio
//		TipoCaracteristicaServicio tipoCaracteristicaServicio1 = new TipoCaracteristicaServicio();
//		tipoCaracteristicaServicio1.setEstatus(0);
//		tipoCaracteristicaServicio1.setFecha_creacion(DateUtil.getCurrentDate());
//		tipoCaracteristicaServicio1.setFecha_modificacion(cal.getTime());
//		tipoCaracteristicaServicio1.setServicio(servicio);
//		tipoCaracteristicaServicio1.setTipoCaracteristica(tipoCaracteristica11);
//		tipoCaracteristcaServicioService.saveOrUpdate(tipoCaracteristicaServicio1);
//		
//		//tipo diagnostico visita
//		TipoDiagnosticoVisita tipoDiagnosticoVisita = new TipoDiagnosticoVisita();
//		tipoDiagnosticoVisita.setDescripcion("Reclamo");
//		tipoDiagnosticoVisita.setEstatus(0);
//		tipoDiagnosticoVisita.setFecha_creacion(DateUtil.getCurrentDate());
//		tipoDiagnosticoVisita.setFecha_modificacion(cal.getTime());
//		tipoDiagnosticoVisitaService.saveOrUpdate(tipoDiagnosticoVisita);
//		
//		// tipo motivo
//		TipoMotivo tipoMotivo = new TipoMotivo();
//		tipoMotivo.setDescripcion("descripcion del motivo");
//		tipoMotivo.setFecha_creacion(DateUtil.getCurrentDate());
//		tipoMotivo.setFecha_modificacion(cal.getTime());
//		tipoMotivoService.saveOrUpdate(tipoMotivo);
//		
//		//tipo reclammo
//		TipoReclamo tipoReclamo = new TipoReclamo();
//		tipoReclamo.setDescripcion("descripcion del reclamo");
//		tipoReclamo.setEstatus(0);
//		tipoReclamo.setFecha_creacion(DateUtil.getCurrentDate());
//		tipoReclamo.setFecha_modificacion(cal.getTime());
//		tipoReclamoService.saveOrUpdate(tipoReclamo);
//		
//		// tipo respuesta
//		
//		TipoRespuesta tipoRespuesta = new TipoRespuesta();
//		tipoRespuesta.setDescripcion("SI");
//		tipoRespuesta.setEstatus(0);
//		tipoRespuesta.setFecha_creacion(DateUtil.getCurrentDate());
//		tipoRespuesta.setFecha_modificacion(cal.getTime());
//		tipoRespuestaService.saveOrUpdate(tipoRespuesta);
//		
//		//ubicacion 
//		Ubicacion ubicacion11 = new Ubicacion();
//		ubicacion11.setDescripcion("descripcion de la ubicacion");
//		ubicacion11.setEstatus(0);
//		ubicacion11.setFecha_creacion(DateUtil.getCurrentDate());
//		ubicacion11.setFecha_modificacion(cal.getTime());
//		ubicacionService.saveOrUpdate(ubicacion11);
//		
//		
//		//respuestas
//		
//		// respuesta
//		Respuesta respuesta = new Respuesta();
//		respuesta.setDescripcion("Buena");
//		respuesta.setEstatus(0);
//		respuesta.setFecha_creacion(DateUtil.getCurrentDate());
//		respuesta.setFecha_modificacion(cal.getTime());
//		respuesta.setTipoRespuesta(tipoRespuesta);
//		respuesta.setOrdenServicio(ordenServicio);
//		
//		// presupuesto
//		Presupuesto presupuesto = new Presupuesto();
//		presupuesto.setDescripcion("Presupuesto");
//		presupuesto.setEstatus(0);
//		presupuesto.setFecha_creacion(DateUtil.getCurrentDate());
//		presupuesto.setFecha_modificacion(cal.getTime());
//		presupuesto.setMontoTotal(200.00);
//		presupuesto.setRespuesta(respuesta);	
//		presupuestoService.saveOrUpdate(presupuesto);
//		
//		//tipoDiagnosticoVisita
//		TipoDiagnosticoVisita tipoDiagnosticoVisita1 = new TipoDiagnosticoVisita();
//		tipoDiagnosticoVisita1.setDescripcion("Garantia");
//		tipoDiagnosticoVisita1.setEstatus(0);
//		tipoDiagnosticoVisita1.setFecha_creacion(DateUtil.getCurrentDate());
//		tipoDiagnosticoVisita1.setFecha_modificacion(cal.getTime());
//		tipoDiagnosticoVisitaService.saveOrUpdate(tipoDiagnosticoVisita1);
//		
//		// DiagnosticoVisita
//		DiagnosticoVisita diagnosticoVisitas = new DiagnosticoVisita();
//		diagnosticoVisitas.setDescripcion("Detalle diagnostico 1");
//		diagnosticoVisitas.setEstatus(0);
//		diagnosticoVisitas.setFecha_creacion(DateUtil.getCurrentDate());
//		diagnosticoVisitas.setFecha_modificacion(cal.getTime());
//		diagnosticoVisitas.setTipoDiagnosticoVisita(tipoDiagnosticoVisita1);
//		//diagnosticoVisitas.setPresupuesto(presupuesto);
//		diagnosticoVisitas.setOrdenServicio(ordenServicio);
//		diagnosticoVisitaService.saveOrUpdate(diagnosticoVisitas);
//		
//		//presupuesto.setDiagnosticoVisita(diagnosticoVisitas);
//		presupuestoService.saveOrUpdate(presupuesto);
//		
//		// diagnostico visita
//		DiagnosticoVisita diagnosticoVisita = new DiagnosticoVisita();
//		diagnosticoVisita.setDescripcion("descripcion de la visita");
//		//diagnosticoVisita.setDetalleDiagnosticos(detalleDiagnosticos);
//		diagnosticoVisita.setEstatus(0);
//		diagnosticoVisita.setFecha_creacion(DateUtil.getCurrentDate());
//		diagnosticoVisita.setFecha_modificacion(cal.getTime());
//		diagnosticoVisita.setOrdenServicio(ordenServicio);
//		diagnosticoVisita.setPresupuesto(presupuesto);
//		//diagnosticoVisita.setRespuesta(respuesta);
//		diagnosticoVisita.setTipoDiagnosticoVisita(tipoDiagnosticoVisita1);
//		//diagnosticoVisita.setValoracionOrdenServicio(valoracionOrdenServicio);
//		//diagnosticoVisita.setVisita(visita);
//		diagnosticoVisitaService.saveOrUpdate(diagnosticoVisita);		
//		
//		respuesta.setDiagnosticoVisita(diagnosticoVisitas);
//		respuesta.setPresupuesto(presupuesto);
//		respuestaService.saveOrUpdate(respuesta);
//
//		//condicion diagnostico
//		
//		CondicionDiagnostico condicionDiagnostico = new CondicionDiagnostico();
//		condicionDiagnostico.setCaracteristicaInmueble(caracteristicaInmueble);
//		condicionDiagnostico.setCondicionInmueble(condicionInmueble);
//		condicionDiagnostico.setDiagnosticoVisita(diagnosticoVisitas);
//		condicionDiagnostico.setEstatus(0);
//		condicionDiagnostico.setFecha_creacion(DateUtil.getCurrentDate());
//		condicionDiagnostico.setFecha_modificacion(cal.getTime());
//		condicionDiagnosticoService.saveOrUpdate(condicionDiagnostico);
//		//zona
//		
//		Zona zona = new Zona();
//		zona.setDescripcion("descripcion de la zona");
//		zona.setEstatus(0);
//		zona.setFecha_creacion(DateUtil.getCurrentDate());
//		zona.setFecha_modificacion(cal.getTime());
//		zona.setSector(sector);
//		zona.setServicio(servicio);
//		zonaService.saveOrUpdate(zona);
//		
//		// motivo
//		
//		Motivo motivo1 = new Motivo();
//		motivo1.setDescripcion("descripcion del motivo");
//		motivo1.setEstatus(0);
//		motivo1.setFecha_creacion(DateUtil.getCurrentDate());
//		motivo1.setFecha_modificacion(cal.getTime());
//		//motivo.setMotivoOrdenServicioEventualidads(motivoOrdenServicioEventualidads);
//		motivo1.setTipoMotivo(tipoMotivo);
//		motivoService.saveOrUpdate(motivo1);
//		//motivo presupuesto
//				
//		MotivoPresupuesto motivoPresupuesto = new MotivoPresupuesto();
//		motivoPresupuesto.setEstatus(0);
//		motivoPresupuesto.setFecha_creacion(DateUtil.getCurrentDate());
//		motivoPresupuesto.setFecha_modificacion(cal.getTime());
//		motivoPresupuesto.setMotivo(motivo1);
//		motivoPresupuesto.setPresupuesto(presupuesto);
//		motivoPresupuestoService.saveOrUpdate(motivoPresupuesto);
//				
//		//solicitud servicio motivo
//				
//		SolicitudServicioMotivo solicitudServicioMotivo1 = new SolicitudServicioMotivo();
//		solicitudServicioMotivo1.setEstatus(0);
//		solicitudServicioMotivo1.setFecha_creacion(DateUtil.getCurrentDate());
//		solicitudServicioMotivo1.setFecha_modificacion(cal.getTime());
//		solicitudServicioMotivo1.setMotivo(motivo1);
//		solicitudServicioMotivo1.setSolicitudServicio(solicitudServicio1);
//		solicitudServicioMotivoService.saveOrUpdate(solicitudServicioMotivo1);
//				
//		//ordenServicio eventualidad
////				
////		OrdenServicioEventualidad ordenServicioEventualidad = new OrdenServicioEventualidad();
////		ordenServicioEventualidad.setDescripcionEstatus("descripcion orden servicio eventualidad");
////		ordenServicioEventualidad.setEstatus(0);
////		ordenServicioEventualidad.setEventualidad(eventualidad);
////		ordenServicioEventualidad.setFecha_creacion(DateUtil.getCurrentDate());
////		ordenServicioEventualidad.setFecha_modificacion(cal.getTime());
////		//ordenServicioEventualidad.setMotivoOrdenServicioEventualidads(motivoOrdenServicioEventualidads);
////		ordenServicioEventualidad.setOrdenServicio(ordenServicio);
////		ordenServicioEventualidadService.saveOrUpdate(ordenServicioEventualidad);			
////				
////		//motivo orden de servicio eventualidad
//						
////		MotivoOrdenServicioEventualidad motivoOrdenEventualidad = new MotivoOrdenServicioEventualidad();
////		motivoOrdenEventualidad.setEstatus(0);
////		motivoOrdenEventualidad.setFecha_creacion(DateUtil.getCurrentDate());
////		motivoOrdenEventualidad.setFecha_modificacion(cal.getTime());
////		motivoOrdenEventualidad.setMotivo(motivo1);
////		motivoOrdenEventualidad.setOrdenServicioEventualidad(ordenServicioEventualidad);
////		motivoOrdenServicioEventualidadService.saveOrUpdate(motivoOrdenEventualidad);
////						
//			
//		//motivo reclamo
//						
//		MotivoReclamo motivoReclamo = new MotivoReclamo();
//		motivoReclamo.setEstatus(0);
//		motivoReclamo.setFecha_creacion(DateUtil.getCurrentDate());
//		motivoReclamo.setFecha_modificacion(cal.getTime());
//		motivoReclamo.setMotivo(motivo1);
//		motivoReclamo.setDescripcion("Queja");
//		//motivoReclamo.setReclamoOrdenEntregas(reclamoOrdenEntregas);
//		motivoReclamoService.saveOrUpdate(motivoReclamo);
//						
//		//reclamo orden entrega
//						
//		ReclamoOrdenEntrega reclamoOrdenEntrega = new ReclamoOrdenEntrega();
//		reclamoOrdenEntrega.setEstatus(0);
//		reclamoOrdenEntrega.setFecha_creacion(DateUtil.getCurrentDate());
//		reclamoOrdenEntrega.setFecha_modificacion(cal.getTime());
//		reclamoOrdenEntrega.setMotivoReclamo(motivoReclamo);
//		//reclamoOrdenEntrega.setOrdenEntregas(ordenEntregas);
//		reclamoOrdenEntrega.setTipoReclamo(tipoReclamo);
//		reclamoOrdenEntregaService.saveOrUpdate(reclamoOrdenEntrega);
//						
//		
//		// detalle orden servicio
////				
////		DetalleOrdenServicio detalleOrdenServicio = new DetalleOrdenServicio();
////		detalleOrdenServicio.setEstatus(0);
////		detalleOrdenServicio.setFecha_creacion(DateUtil.getCurrentDate());
////		detalleOrdenServicio.setFecha_modificacion(cal.getTime());
////		detalleOrdenServicio.setOrdenServicio(ordenServicio);
////		detalleOrdenServicio.setServicioTarea(servicioTarea);
////		detalleOrdenServicio.setSolicitudServicio(solicitudServicio1);
////		detalleOrdenServicio.setTrabajador(trabajador);
////		detalleOrdenServicioService.saveOrUpdate(detalleOrdenServicio);
////				
//		//opcion pregunta
//		OpcionPregunta opcionPregunta = new OpcionPregunta();
//		opcionPregunta.setDescripcion("esta de acuerdo con el precio del servicio");
//		opcionPregunta.setEstatus(0);
//		opcionPregunta.setFecha_creacion(DateUtil.getCurrentDate());
//		opcionPregunta.setFecha_modificacion(cal.getTime());
//		opcionPregunta.setOpcion(opcion);
//		opcionPregunta.setPregunta(pregunta);
//		opcionPreguntaService.saveOrUpdate(opcionPregunta);
//	
//				
//				
//		//************** la cagada **************			
//		//valoracion orden servicio
//				
//		ValoracionOrdenServicio valoracionOrdenServico = new ValoracionOrdenServicio();
//		valoracionOrdenServico.setDiagnosticoVisita(diagnosticoVisita);
//		valoracionOrdenServico.setEstatus(0);
//		valoracionOrdenServico.setFecha_creacion(DateUtil.getCurrentDate());
//		valoracionOrdenServico.setFecha_modificacion(cal.getTime());
//		//valoracionOrdenServico.setOrdenEntrega(ordenEntrega);
//		valoracionOrdenServico.setOrdenServicio(ordenServicio);
//		valoracionOrdenServicioService.saveOrUpdate(valoracionOrdenServico);
//				
//		//orden de entrega
//				
//		OrdenEntrega ordenEntrega = new OrdenEntrega();
//		//ordenEntrega.setCalificarServicios(calificarServicios)
//		ordenEntrega.setDescripcion("descripcion de la orden de entrega");
//		ordenEntrega.setEstatus(0);
//		ordenEntrega.setFecha_creacion(DateUtil.getCurrentDate());
//		ordenEntrega.setFecha_modificacion(cal.getTime());
//		//ordenEntrega.setValoracionOrdenServicio(valoracionOrdenServico);
//		ordenEntregaService.saveOrUpdate(ordenEntrega);
//				
//				
//				
//		// CALIFICAR SERVICIO
//				
//		CalificarServicio calificarServicio1 = new CalificarServicio();
//		calificarServicio1.setDescripcion("Excelente");
//		calificarServicio1.setEstatus(0);
//		calificarServicio1.setFecha_creacion(DateUtil.getCurrentDate());
//		calificarServicio1.setFecha_modificacion(cal.getTime());
//		calificarServicio1.setOrdenEntrega(ordenEntrega);
//		calificarServicio1.setServicio(servicio2);
//		calificarServicio1.setValor(5);
//		calificarServicioService.saveOrUpdate(calificarServicio1);
//				
//		// caracteristica
//				
//		Caracteristica caracteristica11 = new Caracteristica();
//		//caracteristica.setCaracteristicaInmuebles(caracteristicaInmuebles);
//		caracteristica11.setDescripcion("piso");
//		caracteristica11.setEstatus(0);
//		caracteristica11.setFecha_creacion(DateUtil.getCurrentDate());
//		caracteristica11.setFecha_modificacion(cal.getTime());
//		caracteristica11.setPadre_descripcion("padre descripcion");
//		//caracteristica.setPadre_id(padre_id);
//		caracteristica11.setTipoCaracteristica(tipoCaracteristica11);
//		caracteristicaService.saveOrUpdate(caracteristica11);
//				
//		//caracteristicaInmueble
//				
//		CaracteristicaInmueble caracteristicaInmueble1 = new CaracteristicaInmueble();
//		caracteristicaInmueble1.setCaracteristica(caracteristica11);
//		//caracteristicaInmueble.setDetalleDiagnostico(detalleDiagnostico);
//		caracteristicaInmueble1.setEstatus(0);
//		caracteristicaInmueble1.setFecha_creacion(DateUtil.getCurrentDate());
//		caracteristicaInmueble1.setFecha_modificacion(cal.getTime());
//		caracteristicaInmueble1.setInmueble(inmueble);
//		caracteristicaInmueble1.setUbicacion(ubicacion11);
//		caracteristicaInmuebleService.saveOrUpdate(caracteristicaInmueble1);
//				
//				
//		//preferencia cliente
//				
////		PreferenciaCliente preferenciaCliente = new PreferenciaCliente();
////		preferenciaCliente.setCliente(cliente);
////		preferenciaCliente.setEstatus(0);
////		preferenciaCliente.setFecha_creacion(DateUtil.getCurrentDate());
////		preferenciaCliente.setFecha_modificacion(cal.getTime());
////		preferenciaCliente.setCategoria(categoria);
////		preferenciaCliente.setTipoServicio(tipoServicio);
////		preferenciaCliente.setTipoCaracteristica(tipocaracteristica);;
////		preferenciaClienteService.saveOrUpdate(preferenciaCliente);
////				
//				
//		//detalle servicio inmueble
//				
//		DetalleServicioInmueble detalleServicioInmueble1 = new DetalleServicioInmueble();
//		detalleServicioInmueble1.setEstatus(0);
//		detalleServicioInmueble1.setFecha_creacion(DateUtil.getCurrentDate());
//		detalleServicioInmueble1.setFecha_modificacion(cal.getTime());
//		detalleServicioInmueble1.setServicio(servicio);
//		detalleServicioInmueble1.setTipoCaracteristicaInmueble(tipoCaracteristicaInmueble1);
//		detalleServicioInmuebleService.saveOrUpdate(detalleServicioInmueble1);
//				
//		//condicion diagnostico
//				
//		CondicionDiagnostico condicionDiagnostico1 = new CondicionDiagnostico();
//		condicionDiagnostico1.setCaracteristicaInmueble(caracteristicaInmueble1);
//		condicionDiagnostico1.setCondicionInmueble(condicionInmueble);
//				
//		//condicionDiagnostico.setDetalleDiagnosticoVisitas(detalleDiagnosticoVisitas);
//		condicionDiagnostico1.setDiagnosticoVisita(diagnosticoVisita);
//		condicionDiagnostico1.setEstatus(0);
//		condicionDiagnostico1.setFecha_creacion(DateUtil.getCurrentDate());
//		condicionDiagnostico1.setFecha_modificacion(cal.getTime());
//		condicionDiagnosticoService.saveOrUpdate(condicionDiagnostico1);
//				
//		// detalle diagnostico visita
////				
////		DetalleDiagnosticoVisita detalleDiagnosticoVisita = new DetalleDiagnosticoVisita();
////		detalleDiagnosticoVisita.setArea(12.2);
////		detalleDiagnosticoVisita.setCondicionDiagnostico(condicionDiagnostico1);
////		//detalleDiagnosticoVisita.setDetallePresupuestos(0);
////		detalleDiagnosticoVisita.setEstatus(0);
////		detalleDiagnosticoVisita.setFecha_creacion(DateUtil.getCurrentDate());
////		detalleDiagnosticoVisita.setFecha_modificacion(cal.getTime());
////		detalleDiagnosticoVisitaService.saveOrUpdate(detalleDiagnosticoVisita);
//				
//		//detalle presupuesto
//				
////		DetallePresupuesto detallePresupuesto = new DetallePresupuesto();
////		detallePresupuesto.setArea(12.6);
////		detallePresupuesto.setCosto(1000.00);
////		detallePresupuesto.setDetalleDiagnosticoVisita(detalleDiagnosticoVisita);
////		detallePresupuesto.setEstatus(0);
////		detallePresupuesto.setFecha_creacion(DateUtil.getCurrentDate());
////		detallePresupuesto.setFecha_modificacion(cal.getTime());
////		detallePresupuesto.setPresupuesto(presupuesto);
////		detallePresupuesto.setSolicitudServicio(solicitudServicio1);
////		detallePresupuestoService.saveOrUpdate(detallePresupuesto);
//		
//		Tarea tarea1 = new Tarea();
//		tarea1.setDescripcion("Limpiar pisos");
//		tarea1.setEstatus(0);
//		tarea1.setFecha_creacion( DateUtil.getCurrentDate() );
//		tareaService.saveOrUpdate(tarea1);
//		
//
//		//Tipo Caracteristica
//		TipoCaracteristica tipoCaracteristica21 = new TipoCaracteristica();
//		tipoCaracteristica21.setDescripcion("techo de platabanda");
//		tipoCaracteristica21.setEstatus(0);
//		tipoCaracteristica21.setFecha_creacion( DateUtil.getCurrentDate() );
//		tipoCaracteristicaService.saveOrUpdate(tipoCaracteristica21);
//		
//		TipoCaracteristica tipoCaracteristica111 = new TipoCaracteristica();
//		tipoCaracteristica111.setDescripcion("Piso de madera");
//		tipoCaracteristica111.setEstatus(0);
//		tipoCaracteristica111.setFecha_creacion( DateUtil.getCurrentDate() );
//		tipoCaracteristicaService.saveOrUpdate(tipoCaracteristica111);
//		
//		//Tipo Caracteristica Inmueble
//		TipoCaracteristicaInmueble tipoCaracteristicaInmueble31 = new TipoCaracteristicaInmueble();
//		tipoCaracteristicaInmueble31.setTipoCaracteristica(tipoCaracteristica111);
//		tipoCaracteristicaInmueble31.setTipoInmueble(tipoInmueble);
//		tipoCaracteristicaInmueble31.setEstatus(0);
//		tipoCaracteristicaInmueble31.setFecha_creacion( DateUtil.getCurrentDate() );
//		tipoCaracteristicaInmuebleService.saveOrUpdate(tipoCaracteristicaInmueble31);
//		
//		TipoCaracteristicaInmueble tipoCaracteristicaInmueble13 = new TipoCaracteristicaInmueble();
//		tipoCaracteristicaInmueble13.setTipoCaracteristica(tipoCaracteristica11);
//		tipoCaracteristicaInmueble13.setTipoInmueble(tipoInmueble);
//		tipoCaracteristicaInmueble13.setEstatus(0);
//		tipoCaracteristicaInmueble13.setFecha_creacion( DateUtil.getCurrentDate() );
//		tipoCaracteristicaInmuebleService.saveOrUpdate(tipoCaracteristicaInmueble13);
//		
//		// Preferencia Cliente
////		PreferenciaCliente preferenciaCliente1 = new PreferenciaCliente();
////		preferenciaCliente1.setTipoCaracteristica(tipoCaracteristica);
////		preferenciaCliente1.setCliente(cliente);
////		preferenciaCliente1.setCategoria(categoria);
////		preferenciaCliente1.setTipoServicio(tipoServicio);
////		preferenciaCliente1.setEstatus(0);
////		preferenciaCliente1.setFecha_creacion( DateUtil.getCurrentDate() );
////		preferenciaClienteService.saveOrUpdate(preferenciaCliente1);
////
////		
////		PreferenciaCliente preferenciaCliente2 = new PreferenciaCliente();
////		preferenciaCliente2.setEstatus(0);
////		preferenciaCliente2.setFecha_creacion( DateUtil.getCurrentDate() );
////		preferenciaCliente2.setCategoria(categoria2);
////		preferenciaCliente2.setCliente(cliente2);
////		preferenciaCliente2.setTipoCaracteristica(tipoCaracteristica111);
////		preferenciaCliente2.setTipoServicio(tipoServicio2);
////		preferenciaClienteService.saveOrUpdate(preferenciaCliente2);
//		
//		//Solicitud Servicio
////		SolicitudServicio solserv = new SolicitudServicio();
////		solserv.setServicio(servicio);
////		solserv.setSolicitud(solicitud);
////		solserv.setEstatus(0);
////		solserv.setFecha_creacion(DateUtil.getCurrentDate());
////		solicitudServicioService.saveOrUpdate(solserv);
////		
////		//Servicio Tarea
//		ServicioTarea servtar = new ServicioTarea();
//		servtar.setDescripcion("Una cosa ahi");
//		servtar.setServicio(servicio4);
//		servtar.setTarea(tarea1);
//		servicioTareaService.saveOrUpdate(servtar);
//		
////		//Detalle orden de servicio
////		DetalleOrdenServicio detOrdenServ1 = new DetalleOrdenServicio();
////		detOrdenServ1.setOrdenServicio(ordenServicio);
////		detOrdenServ1.setTrabajador(trabajador3);
////		detOrdenServ1.setServicioTarea(servtar);
////		detOrdenServ1.setSolicitudServicio(solserv);
////		detalleOrdenServicioService.saveOrUpdate(detOrdenServ1);
////		
//		//TipoDiagnosticoVisita
//		TipoDiagnosticoVisita tdvta1 = new TipoDiagnosticoVisita();
//		tdvta1.setDescripcion("Diagnostico");
//		tipoDiagnosticoVisitaService.saveOrUpdate(tdvta1);
//		
//		//DiagnosticoVisita
//		DiagnosticoVisita diagv1 = new DiagnosticoVisita();
//		diagv1.setDescripcion("Diagnostico Interesante");
//		diagv1.setTipoDiagnosticoVisita(tdvta1);
//		diagnosticoVisitaService.saveOrUpdate(diagv1);
//		
	}
}

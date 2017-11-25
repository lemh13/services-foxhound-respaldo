package fox.hound.spring.controllers;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import fox.hound.spring.models.Cliente;
import fox.hound.spring.models.DiagnosticoVisita;
import fox.hound.spring.models.Empresa;
import fox.hound.spring.models.Garantia;
import fox.hound.spring.models.Inmueble;
import fox.hound.spring.models.Noticia;
import fox.hound.spring.models.OrdenServicio;
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
import fox.hound.spring.models.combo.Municipio;
import fox.hound.spring.models.combo.Parroquia;
import fox.hound.spring.models.combo.Presupuesto;
import fox.hound.spring.models.combo.Rol;
import fox.hound.spring.models.combo.Sector;
import fox.hound.spring.models.maestros.CalificarServicio;
import fox.hound.spring.models.maestros.Cargo;
import fox.hound.spring.models.maestros.Categoria;
import fox.hound.spring.models.maestros.CategoriaInmueble;
import fox.hound.spring.models.maestros.Condicion;
import fox.hound.spring.models.maestros.CondicionGarantia;
import fox.hound.spring.models.maestros.Estado;
import fox.hound.spring.models.maestros.Ocupacion;
import fox.hound.spring.models.maestros.Profesion;
import fox.hound.spring.models.maestros.TipoCaracteristica;
import fox.hound.spring.models.maestros.TipoCliente;
import fox.hound.spring.models.maestros.TipoDiagnosticoVisita;
import fox.hound.spring.models.maestros.TipoEventualidad;
import fox.hound.spring.models.maestros.TipoInmueble;
import fox.hound.spring.models.maestros.TipoOrdenServicio;
import fox.hound.spring.models.maestros.TipoPromocion;
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
import fox.hound.spring.models.puente.Promocion;
import fox.hound.spring.models.puente.PromocionServicio;
import fox.hound.spring.models.puente.Solicitud;
import fox.hound.spring.models.puente.TrabajadorVisita;
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
	private ParroquiaService parroquiaService;
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
	private InmuebleService inmuebleService;
	@Autowired
	private EmpresaService empresaService;
	@Autowired
	private UnidadMedidaService unidadMedidaService;
	@Autowired
	private TipoServicioService tipoServicioService;
	@Autowired
	private CondicionGarantiaService condicionGarantiaService;
	@Autowired
	private GarantiaService garantiaService;
	@Autowired
	private ServicioService servicioService;
	@Autowired
	private TipoPromocionService tipoPromocionService;
	@Autowired
	private DescuentoService descuentoService;
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
	@Autowired
	private DetalleOrdenServicioService detalleOrdenService;
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
	private PreferenciaClienteService preferenciaClienteService;
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
	private SolicitudServicioMotivoService solicitusServicioMotivoService;
	@Autowired
	private SolicitudServicioService solicitudServicioService;
	@Autowired
	private TareaService tareaService;
	@Autowired
	private TipoCaracteristicaInmuebleService tipoCaracteristicaInmuebleService;
	@Autowired
	private TipoCaracteristicaService tipoCaracteristicaService;
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
	private DetalleDiagnosticoVisitaService detalleDiagnosticoVisitaService;
	@Autowired
	private PresupuestoService presupuestoService;
	
	
	
	
	
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
		personaService.saveOrUpdate(cliente);
		
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
		personaService.saveOrUpdate(cliente2);
		
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
		servicio2.setTipoServicio(tipoServicio2);
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
		
		// Visita
		Visita visita = new Visita();
		visita.setFechaVisita(DateUtil.getCurrentDate());
		visita.setTurno(turno);
		visita.setTipoVisita(tipoVisita);
		visita.setSolicitud(solicitud);
		visita.setOrdenServicio(ordenServicio);
		visita.setEstatus(0);
		visita.setFecha_creacion( DateUtil.getCurrentDate() );
		visitaService.saveOrUpdate(visita);
		
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
		trabajador.setEmail("jose@gmail.com");
		trabajador.setPassword( "123" );
		trabajador.setRol(rol);
		trabajador.setEstatus(0);
		trabajador.setFecha_creacion( DateUtil.getCurrentDate() );
		personaService.saveOrUpdate(trabajador);
		
		Trabajador trabajador2 = new Trabajador();
		trabajador2.setNombre("Jose Miguel");
		trabajador2.setSexo('M');
		trabajador2.setTipoPersona(0);
		trabajador2.setCargo(cargo);
		trabajador2.setIdentificacion(21526571);
		trabajador2.setDireccion("Carrera 13b");
		trabajador2.setSector(sector);
		trabajador2.setTelefono("04120523025");
		trabajador2.setFecha_de_nacimiento( DateUtil.getCurrentDate() );
		trabajador2.setEmail("jose@gmail.com");
		trabajador2.setPassword( "123" );
		trabajador2.setRol(rol2);
		trabajador2.setEstatus(0);
		trabajador2.setFecha_creacion( DateUtil.getCurrentDate() );
		personaService.saveOrUpdate(trabajador2);
		
		Trabajador trabajador3 = new Trabajador();
		trabajador3.setNombre("Luis Medina");
		trabajador3.setSexo('M');
		trabajador3.setTipoPersona(0);
		trabajador3.setCargo(cargo);
		trabajador3.setIdentificacion(21526571);
		trabajador3.setDireccion("Carrera 13b");
		trabajador3.setSector(sector);
		trabajador3.setTelefono("04120523025");
		trabajador3.setFecha_de_nacimiento( DateUtil.getCurrentDate() );
		trabajador3.setEmail("jose@gmail.com");
		trabajador3.setPassword( "123" );
		trabajador3.setRol(rol2);
		trabajador3.setEstatus(0);
		trabajador3.setFecha_creacion( DateUtil.getCurrentDate() );
		personaService.saveOrUpdate(trabajador3);
		
		Trabajador trabajador4 = new Trabajador();
		trabajador4.setNombre("Pedro Medina");
		trabajador4.setSexo('M');
		trabajador4.setTipoPersona(0);
		trabajador4.setCargo(cargo);
		trabajador4.setIdentificacion(21526571);
		trabajador4.setDireccion("Carrera 13b");
		trabajador4.setSector(sector);
		trabajador4.setTelefono("04120523025");
		trabajador4.setFecha_de_nacimiento( DateUtil.getCurrentDate() );
		trabajador4.setEmail("jose@gmail.com");
		trabajador4.setPassword( "123" );
		trabajador4.setRol(rol3);
		trabajador4.setEstatus(0);
		trabajador4.setFecha_creacion( DateUtil.getCurrentDate() );
		personaService.saveOrUpdate(trabajador4);
		
		Trabajador trabajador5 = new Trabajador();
		trabajador5.setNombre("Alfredo Medina");
		trabajador5.setSexo('M');
		trabajador5.setTipoPersona(0);
		trabajador5.setCargo(cargo);
		trabajador5.setIdentificacion(21526571);
		trabajador5.setDireccion("Carrera 13b");
		trabajador5.setSector(sector);
		trabajador5.setTelefono("04120523025");
		trabajador5.setFecha_de_nacimiento( DateUtil.getCurrentDate() );
		trabajador5.setEmail("jose@gmail.com");
		trabajador5.setPassword( "123" );
		trabajador5.setRol(rol3);
		trabajador5.setEstatus(0);
		trabajador5.setFecha_creacion( DateUtil.getCurrentDate() );
		personaService.saveOrUpdate(trabajador5);
		
		// TrabajadorVisita
		TrabajadorVisita trabajadorVisita = new TrabajadorVisita();
		trabajadorVisita.setTrabajador(trabajador);
		trabajadorVisita.setVisita(visita);
		trabajadorVisita.setEstatus(0);
		trabajadorVisita.setFecha_creacion( DateUtil.getCurrentDate() );
		trabajadorVisitaService.saveOrUpdate(trabajadorVisita);
		
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
		asuntoComentario3.setDescripcion("Opiniòn");
		asuntoComentario3.setEstatus(0);
		asuntoComentario3.setFecha_creacion( DateUtil.getCurrentDate() );
		asuntoComentarioService.saveOrUpdate(asuntoComentario3);

/*
		 * ******************************************
		 *          Aqui trabaja Mahola             *
		 * ******************************************
		 */
		
		// BUZON DE SUGERENCIA 
		
		BuzonSugerencia buzonSugerencia = new BuzonSugerencia();
		buzonSugerencia.setAsuntoComentario(asuntoComentario);
		buzonSugerencia.setDescripcion("Descripcion del buzon");
		buzonSugerencia.setEstatus(0);
		buzonSugerencia.setFecha_creacion(DateUtil.getCurrentDate());
		buzonSugerencia.setFecha_modificacion(cal.getTime());
		buzonSugerencia.setPersona(cliente2);
		buzonSugerenciaService.saveOrUpdate(buzonSugerencia);		

		
		//ASUNTO  COMENTARIO
		AsuntoComentario asuntoComentario4 = new AsuntoComentario();
		asuntoComentario4.setDescripcion("descripcion del comentario externo");
		asuntoComentario4.setEstatus(0);
		asuntoComentario4.setFecha_creacion(DateUtil.getCurrentDate());
		asuntoComentario4.setFecha_modificacion(cal.getTime());
		asuntoComentarioService.saveOrUpdate(asuntoComentario4);

		// CALIFICAR SERVICIO
		
		CalificarServicio calificarServicio = new CalificarServicio();
		calificarServicio.setDescripcion("Excelente");
		calificarServicio.setEstatus(0);
		calificarServicio.setFecha_creacion(DateUtil.getCurrentDate());
		calificarServicio.setFecha_modificacion(cal.getTime());
		calificarServicio.setServicio(servicio2);
		calificarServicio.setValor(5);
		calificarServicioService.saveOrUpdate(calificarServicio);
		
		// Tipo Caracteristica
		TipoCaracteristica tipoCaracteristica = new TipoCaracteristica();
		tipoCaracteristica.setDescripcion("Piso");
		tipoCaracteristica.setEstatus(0);
		tipoCaracteristica.setFecha_creacion(DateUtil.getCurrentDate());
		tipoCaracteristica.setFecha_modificacion(cal.getTime());
		tipoCaracteristicaService.saveOrUpdate(tipoCaracteristica);
		
		// caracteristica
		
		Caracteristica caracteristica = new Caracteristica();
		caracteristica.setDescripcion("piso");
		caracteristica.setEstatus(0);
		caracteristica.setFecha_creacion(DateUtil.getCurrentDate());
		caracteristica.setFecha_modificacion(cal.getTime());
		caracteristica.setPadre_descripcion("padre descripcion");
		caracteristica.setTipoCaracteristica(tipoCaracteristica);
		caracteristicaService.saveOrUpdate(caracteristica);
		
		// Ubicacion
		Ubicacion ubicacion = new Ubicacion();
		ubicacion.setDescripcion("Oeste");
		ubicacion.setEstatus(0);
		ubicacion.setFecha_creacion(DateUtil.getCurrentDate());
		ubicacion.setFecha_modificacion(cal.getTime());
		ubicacionService.saveOrUpdate(ubicacion);
		
		//caracteristicaInmueble
		
//		CaracteristicaInmueble caracteristicaInmueble = new CaracteristicaInmueble();
//		caracteristicaInmueble.setCaracteristica(caracteristica);
//		caracteristicaInmueble.setEstatus(0);
//		caracteristicaInmueble.setFecha_creacion(DateUtil.getCurrentDate());
//		caracteristicaInmueble.setFecha_modificacion(cal.getTime());
//		caracteristicaInmueble.setInmueble(inmueble);
//		caracteristicaInmueble.setUbicacion(ubicacion);
//		caracteristicaInmuebleService.saveOrUpdate(caracteristicaInmueble);
		
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
//		comentarioExterno.setAsuntoComentario(asuntoComentario);
//		comentarioExterno.setCorreoEmisor("mhlyrda@gmail.com");
//		comentarioExterno.setDescripcion("descripcion del comentario");
//		comentarioExterno.setEstatus(0);
//		comentarioExterno.setFecha_creacion(DateUtil.getCurrentDate());
//		comentarioExterno.setFecha_modificacion(cal.getTime());
//		comentarioExternoService.saveOrUpdate(comentarioExterno);
//		
//		// condicion
//		Condicion condicion = new Condicion();
//		condicion.setDescripcion("Buena");
//		condicion.setEstatus(0);
//		condicion.setFecha_creacion(DateUtil.getCurrentDate());
//		condicion.setFecha_modificacion(cal.getTime());
//		condicionService.saveOrUpdate(condicion);
//		
//		// condicionInmueble
//		CondicionInmueble condicionInmueble = new CondicionInmueble();
//		condicionInmueble.setDescripcion("Buena");
//		condicionInmueble.setEstatus(0);
//		condicionInmueble.setFecha_creacion(DateUtil.getCurrentDate());
//		condicionInmueble.setFecha_modificacion(cal.getTime());
//		condicionInmueble.setCondicion(condicion);
//		condicionInmuebleService.saveOrUpdate(condicionInmueble);
//		
//		// tipoRespuesta
//		TipoRespuesta tipoRespuesta = new TipoRespuesta();
//		tipoRespuesta.setDescripcion("SI");
//		tipoRespuesta.setEstatus(0);
//		tipoRespuesta.setFecha_creacion(DateUtil.getCurrentDate());
//		tipoRespuesta.setFecha_modificacion(cal.getTime());
//		tipoRespuestaService.saveOrUpdate(tipoRespuesta);
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
//		
//		//tipoDiagnosticoVisita
//		TipoDiagnosticoVisita tipoDiagnosticoVisita = new TipoDiagnosticoVisita();
//		tipoDiagnosticoVisita.setDescripcion("Garantia");
//		tipoDiagnosticoVisita.setEstatus(0);
//		tipoDiagnosticoVisita.setFecha_creacion(DateUtil.getCurrentDate());
//		tipoDiagnosticoVisita.setFecha_modificacion(cal.getTime());
//		tipoDiagnosticoVisitaService.saveOrUpdate(tipoDiagnosticoVisita);
//		
//		// DiagnosticoVisita
//		DiagnosticoVisita diagnosticoVisitas = new DiagnosticoVisita();
//		diagnosticoVisitas.setDescripcion("Detalle diagnostico 1");
//		diagnosticoVisitas.setEstatus(0);
//		diagnosticoVisitas.setFecha_creacion(DateUtil.getCurrentDate());
//		diagnosticoVisitas.setFecha_modificacion(cal.getTime());
//		diagnosticoVisitas.setTipoDiagnosticoVisita(tipoDiagnosticoVisita);
//		diagnosticoVisitas.setPresupuesto(presupuesto);
//		diagnosticoVisitas.setOrdenServicio(ordenServicio);
//		diagnosticoVisitaService.saveOrUpdate(diagnosticoVisitas);
//		
//		presupuesto.setDiagnosticoVisita(diagnosticoVisitas);
//		presupuestoService.saveOrUpdate(presupuesto);
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
//		
	}

}

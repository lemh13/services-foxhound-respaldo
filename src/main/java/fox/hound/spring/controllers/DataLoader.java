package fox.hound.spring.controllers;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import fox.hound.spring.models.Cliente;
import fox.hound.spring.models.Empresa;
import fox.hound.spring.models.Garantia;
import fox.hound.spring.models.Inmueble;
import fox.hound.spring.models.Noticia;
import fox.hound.spring.models.OrdenServicio;
import fox.hound.spring.models.Servicio;
import fox.hound.spring.models.Trabajador;
import fox.hound.spring.models.Visita;
import fox.hound.spring.models.combo.Ciudad;
import fox.hound.spring.models.combo.Descuento;
import fox.hound.spring.models.combo.Eventualidad;
import fox.hound.spring.models.combo.Municipio;
import fox.hound.spring.models.combo.Parroquia;
import fox.hound.spring.models.combo.Rol;
import fox.hound.spring.models.combo.Sector;
import fox.hound.spring.models.maestros.Cargo;
import fox.hound.spring.models.maestros.Categoria;
import fox.hound.spring.models.maestros.CategoriaInmueble;
import fox.hound.spring.models.maestros.CondicionGarantia;
import fox.hound.spring.models.maestros.Estado;
import fox.hound.spring.models.maestros.Ocupacion;
import fox.hound.spring.models.maestros.Profesion;
import fox.hound.spring.models.maestros.TipoCliente;
import fox.hound.spring.models.maestros.TipoEventualidad;
import fox.hound.spring.models.maestros.TipoInmueble;
import fox.hound.spring.models.maestros.TipoOrdenServicio;
import fox.hound.spring.models.maestros.TipoPromocion;
import fox.hound.spring.models.maestros.TipoServicio;
import fox.hound.spring.models.maestros.TipoVisita;
import fox.hound.spring.models.maestros.Turno;
import fox.hound.spring.models.maestros.UnidadMedida;
import fox.hound.spring.models.maestros.UsoInmueble;
import fox.hound.spring.models.puente.Promocion;
import fox.hound.spring.models.puente.PromocionServicio;
import fox.hound.spring.models.puente.Solicitud;
import fox.hound.spring.models.puente.TrabajadorVisita;
import fox.hound.spring.services.CargoService;
import fox.hound.spring.services.CategoriaInmuebleService;
import fox.hound.spring.services.CategoriaService;
import fox.hound.spring.services.CiudadService;
import fox.hound.spring.services.CondicionGarantiaService;
import fox.hound.spring.services.DescuentoService;
import fox.hound.spring.services.EmpresaService;
import fox.hound.spring.services.EstadoService;
import fox.hound.spring.services.EventualidadService;
import fox.hound.spring.services.GarantiaService;
import fox.hound.spring.services.InmuebleService;
import fox.hound.spring.services.MunicipioService;
import fox.hound.spring.services.NoticiaService;
import fox.hound.spring.services.OcupacionService;
import fox.hound.spring.services.OrdenServicioService;
import fox.hound.spring.services.ParroquiaService;
import fox.hound.spring.services.PersonaService;
import fox.hound.spring.services.ProfesionService;
import fox.hound.spring.services.PromocionService;
import fox.hound.spring.services.PromocionServicioService;
import fox.hound.spring.services.RolService;
import fox.hound.spring.services.SectorService;
import fox.hound.spring.services.ServicioService;
import fox.hound.spring.services.SolicitudService;
import fox.hound.spring.services.TipoClienteService;
import fox.hound.spring.services.TipoEventualidadService;
import fox.hound.spring.services.TipoInmuebleService;
import fox.hound.spring.services.TipoOrdenServicioService;
import fox.hound.spring.services.TipoPromocionService;
import fox.hound.spring.services.TipoServicioService;
import fox.hound.spring.services.TipoVisitaService;
import fox.hound.spring.services.TrabajadorVisitaService;
import fox.hound.spring.services.TurnoService;
import fox.hound.spring.services.UnidadMedidaService;
import fox.hound.spring.services.UsoInmuebleService;
import fox.hound.spring.services.VisitaService;
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
		rol.setEstatus(0);
		rol.setFecha_creacion( DateUtil.getCurrentDate() );
		rolService.saveOrUpdate(rol);
		
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
		tipoServicio.setEstatus(0);
		tipoServicio.setFecha_creacion( DateUtil.getCurrentDate() );
		tipoServicioService.saveOrUpdate(tipoServicio);
		
		TipoServicio tipoServicio2 = new TipoServicio();
		tipoServicio2.setDescripcion("Pintura");
		tipoServicio2.setImagenServicio("imagen/p4.jpg");
		tipoServicio2.setEstatus(0);
		tipoServicio2.setFecha_creacion( DateUtil.getCurrentDate() );
		tipoServicioService.saveOrUpdate(tipoServicio2);
		
		TipoServicio tipoServicio3 = new TipoServicio();
		tipoServicio3.setDescripcion("Electricidad");
		tipoServicio3.setImagenServicio("imagen/p4.jpg");
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
		servicio.setCategoria(categoria2);
		servicio.setTitulo("Reparacion de Pisos");
		servicio.setImagenServicio("imagen/p4.jpg");
		servicio.setDescripcion("Reparalo ya.");
		servicio.setCosto(200.00);
		servicio.setEstatus(0);
		servicio.setFecha_creacion( DateUtil.getCurrentDate() );
		servicioService.saveOrUpdate(servicio);
		
		Servicio servicio2 = new Servicio();
		servicio2.setGarantia(garantia);
		servicio2.setTipoServicio(tipoServicio);
		servicio2.setUnidadMedida(unidadMedida);
		servicio2.setEmpresa(empresa);
		servicio2.setCategoria(categoria);
		servicio2.setTitulo("Mantenimiento de Platabanda");
		servicio2.setImagenServicio("imagen/p2.jpg");
		servicio2.setDescripcion("Reparalo ya: filtros, grietas, rayones y mucho mas..");
		servicio2.setCosto(200.00);
		servicio2.setEstatus(0);
		servicio2.setFecha_creacion( DateUtil.getCurrentDate() );
		servicioService.saveOrUpdate(servicio2);
		
		// TipoPromocion
		TipoPromocion tipoPromocion = new TipoPromocion();
		tipoPromocion.setDescripcion("Días de las Madres");
		tipoPromocion.setEstatus(0);
		tipoPromocion.setFecha_creacion( DateUtil.getCurrentDate() );
		tipoPromocionService.saveOrUpdate(tipoPromocion);
		
		// Descuento
		Descuento descuento = new Descuento();
		descuento.setDescripcion("Días de las Madres");
		descuento.setMonto(200);
		descuento.setPorcentaje(0.43);
		descuento.setEstatus(0);
		descuento.setFecha_creacion( DateUtil.getCurrentDate() );
		descuentoService.saveOrUpdate(descuento);
		
		// Promocion
		Promocion promocion = new Promocion();
		promocion.setTitulo("Promocíon del Pisos para el Dia de las Madres");
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
		
		// TrabajadorVisita
		TrabajadorVisita trabajadorVisita = new TrabajadorVisita();
		trabajadorVisita.setTrabajador(trabajador);
		trabajadorVisita.setVisita(visita);
		trabajadorVisita.setEstatus(0);
		trabajadorVisita.setFecha_creacion( DateUtil.getCurrentDate() );
		trabajadorVisitaService.saveOrUpdate(trabajadorVisita);
		
	}

}

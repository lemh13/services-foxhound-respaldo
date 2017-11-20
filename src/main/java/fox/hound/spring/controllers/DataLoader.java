package fox.hound.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import fox.hound.spring.models.Cliente;
import fox.hound.spring.models.Empresa;
import fox.hound.spring.models.Inmueble;
import fox.hound.spring.models.Servicio;
import fox.hound.spring.models.combo.Ciudad;
import fox.hound.spring.models.combo.Garantia;
import fox.hound.spring.models.combo.Municipio;
import fox.hound.spring.models.combo.Parroquia;
import fox.hound.spring.models.combo.Rol;
import fox.hound.spring.models.combo.Sector;
import fox.hound.spring.models.maestros.Cargo;
import fox.hound.spring.models.maestros.Categoria;
import fox.hound.spring.models.maestros.CategoriaInmueble;
import fox.hound.spring.models.maestros.CondicionGarantia;
import fox.hound.spring.models.maestros.Estado;
import fox.hound.spring.models.maestros.Profesion;
import fox.hound.spring.models.maestros.TipoCliente;
import fox.hound.spring.models.maestros.TipoInmueble;
import fox.hound.spring.models.maestros.TipoServicio;
import fox.hound.spring.models.maestros.UnidadMedida;
import fox.hound.spring.models.maestros.UsoInmueble;
import fox.hound.spring.services.CargoService;
import fox.hound.spring.services.CategoriaInmuebleService;
import fox.hound.spring.services.CategoriaService;
import fox.hound.spring.services.CiudadService;
import fox.hound.spring.services.CondicionGarantiaService;
import fox.hound.spring.services.EmpresaService;
import fox.hound.spring.services.EstadoService;
import fox.hound.spring.services.GarantiaService;
import fox.hound.spring.services.InmuebleService;
import fox.hound.spring.services.MunicipioService;
import fox.hound.spring.services.ParroquiaService;
import fox.hound.spring.services.PersonaService;
import fox.hound.spring.services.ProfesionService;
import fox.hound.spring.services.RolService;
import fox.hound.spring.services.SectorService;
import fox.hound.spring.services.TipoClienteService;
import fox.hound.spring.services.TipoInmuebleService;
import fox.hound.spring.services.TipoServicioService;
import fox.hound.spring.services.UnidadMedidaService;
import fox.hound.spring.services.UsoInmuebleService;
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
		tipoServicio.setDescripcion("Servicio Chulito");
		tipoServicio.setImagenServicio("imagen/p4.jpg");
		tipoServicio.setEstatus(0);
		tipoServicio.setFecha_creacion( DateUtil.getCurrentDate() );
		tipoServicioService.saveOrUpdate(tipoServicio);
		
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
		
		
		
		
	}

}

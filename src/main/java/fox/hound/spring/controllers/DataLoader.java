package fox.hound.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import fox.hound.spring.models.Cliente;
import fox.hound.spring.models.combo.Ciudad;
import fox.hound.spring.models.combo.Municipio;
import fox.hound.spring.models.combo.Parroquia;
import fox.hound.spring.models.combo.Rol;
import fox.hound.spring.models.combo.Sector;
import fox.hound.spring.models.maestros.Cargo;
import fox.hound.spring.models.maestros.Categoria;
import fox.hound.spring.models.maestros.Estado;
import fox.hound.spring.models.maestros.Profesion;
import fox.hound.spring.models.maestros.TipoCliente;
import fox.hound.spring.services.CargoService;
import fox.hound.spring.services.CategoriaService;
import fox.hound.spring.services.CiudadService;
import fox.hound.spring.services.EstadoService;
import fox.hound.spring.services.MunicipioService;
import fox.hound.spring.services.ParroquiaService;
import fox.hound.spring.services.PersonaService;
import fox.hound.spring.services.ProfesionService;
import fox.hound.spring.services.RolService;
import fox.hound.spring.services.SectorService;
import fox.hound.spring.services.TipoClienteService;
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
	private EncryptionUtil encript;
	
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
		cliente.setPassword(  encript.md5("123") );
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
		
		
		
		
		
		
		
	}

}

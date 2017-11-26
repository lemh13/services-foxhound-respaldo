package fox.hound.spring.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.BuzonSugerencia;
import fox.hound.spring.models.combo.Rol;
import fox.hound.spring.models.combo.Sector;
import fox.hound.spring.models.maestros.TipoPersona;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@CustomJsonRootName(plural = "usuario", singular = "usuario")
public class Persona extends Base {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private char sexo;
	@Column(nullable = false)
	private String direccion;
	@Column(nullable = false)
	private int identificacion;
	@Column(nullable = false)
	private Date fecha_de_nacimiento;
	@Column(nullable = false)
	private String telefono;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false, unique = true)
	private String email;
	private int tipoPersona;
	// aqui coloco la descripcion del tipoPersona
	@Transient
	private String descripcionTipoPersona;
	
	@ManyToOne
	@JoinColumn(name="sector_id")
	@JsonBackReference(value="personas-sector")
	private Sector sector;
	@Transient
	private Long sector_id;
	@Transient
	private String sector_descripcion;
	
	@ManyToOne
	@JoinColumn(name="rol_id")
	@JsonBackReference(value="persona-rol")
	private Rol rol;
	@Transient
	private Long rol_id;
	@Transient
	private String rol_descripcion;
	
	@OneToMany(mappedBy="persona")
	@JsonManagedReference(value="persona-buzonSugerencia")
	private List<BuzonSugerencia> buzonSugerencia;
	
	public Persona(Long id, String nombre, char sexo, String direccion, int identificacion,
			Date fecha_de_nacimiento, String telefono, int estatusId,
			String sectorId, String email, String password, String rolId, int tipoPersona) {
		super(estatusId);
		this.id = id;
		this.nombre = nombre;
		this.sexo = sexo;
		this.tipoPersona = tipoPersona;
		this.direccion = direccion;
		this.identificacion = identificacion;
		this.fecha_de_nacimiento = fecha_de_nacimiento;
		this.telefono = telefono;
		this.password = password;
		this.email = email;
		this.sector = new Sector(sectorId);
		this.rol = new Rol(rolId);
	}
	public Persona(String id) {
		this.id = Long.valueOf(id);
	}
	public Persona() {
		
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(int identificacion) {
		this.identificacion = identificacion;
	}
	public Date getFecha_de_nacimiento() {
		return fecha_de_nacimiento;
	}
	public void setFecha_de_nacimiento(Date fecha_de_nacimiento) {
		this.fecha_de_nacimiento = fecha_de_nacimiento;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Sector getSector() {
		return sector;
	}
	public void setSector(Sector sector) {
		this.sector = sector;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public List<BuzonSugerencia> getBuzonSugerencia() {
		return buzonSugerencia;
	}
	public void setBuzonSugerencia(List<BuzonSugerencia> buzonSugerencia) {
		this.buzonSugerencia = buzonSugerencia;
	}
	public int getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(int tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	public String getDescripcionTipoPersona() {
		TipoPersona tipoPersonas = new TipoPersona();
		return tipoPersonas.nombre(tipoPersona);
	}
	public void setDescripcionTipoPersona(String descripcionTipoPersona) {
		this.descripcionTipoPersona = descripcionTipoPersona;
	}
	public Long getSector_id() {
		return sector.getId();
	}
	public void setSector_id(Long sector_id) {
		this.sector_id = sector_id;
	}
	public String getSector_descripcion() {
		return sector.getDescripcion();
	}
	public void setSector_descripcion(String sector_descripcion) {
		this.sector_descripcion = sector_descripcion;
	}
	public Long getRol_id() {
		return rol.getId();
	}
	public void setRol_id(Long rol_id) {
		this.rol_id = rol_id;
	}
	public String getRol_descripcion() {
		return rol.getDescripcion();
	}
	public void setRol_descripcion(String rol_descripcion) {
		this.rol_descripcion = rol_descripcion;
	}
	
	
	
}

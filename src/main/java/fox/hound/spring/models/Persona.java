package fox.hound.spring.models;

import java.util.Date;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

import fox.hound.spring.models.combo.Sector;
import fox.hound.spring.models.maestros.TipoIdentificacion;
import fox.hound.spring.models.maestros.TipoPersona;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class Persona {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name="sector_id")
	@JsonBackReference
	private Sector sector;
	@ManyToOne
	@JoinColumn(name="tipoPersona_id")
	@JsonBackReference
	private TipoPersona tipoPersona;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private char sexo;
	@Column(nullable = false)
	private String direccion;
	@Column(nullable = false)
	private int identificacion;
	@ManyToOne
	@JoinColumn(name="tipoIdentificacion_id")
	@JsonBackReference
	private TipoIdentificacion tipoIdentificacion;
	@Column(nullable = false)
	private Date fecha_de_nacimiento;
	@Column(nullable = false)
	private String telefono;
	@ManyToOne
	@JoinColumn(name="estatusId", nullable = false)
	@JsonIdentityReference(alwaysAsId = true)
	private Estatus estatus;
	@Column(nullable = true)
	private Date fecha_creacion;
	@Column(nullable = true)
	private Date fecha_modificacion;
	
	public Persona(Long id, String nombre, char sexo, String direccion, int identificacion, Long estatus,
			Date fecha_de_nacimiento, String telefono, String estatusId, String tipoIdentificacionId,
			String tipoPersonaId, String sectorId) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.sexo = sexo;
		this.direccion = direccion;
		this.identificacion = identificacion;
		this.fecha_de_nacimiento = fecha_de_nacimiento;
		this.telefono = telefono;
		this.estatus = new Estatus(estatusId);
		this.tipoIdentificacion = new TipoIdentificacion(tipoIdentificacionId);
		this.tipoPersona = new TipoPersona(tipoPersonaId);
		this.sector = new Sector(sectorId);
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
	public TipoPersona getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(TipoPersona tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	public TipoIdentificacion getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	public Estatus getEstatus() {
		return estatus;
	}
	public void setEstatus(Estatus estatus) {
		this.estatus = estatus;
	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public Date getFecha_modificacion() {
		return fecha_modificacion;
	}
	public void setFecha_modificacion(Date fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}
	public Sector getSector() {
		return sector;
	}
	public void setSector(Sector sector) {
		this.sector = sector;
	}
	
}

package fox.hound.spring.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.Sector;
import fox.hound.spring.models.maestros.TipoInmueble;
import fox.hound.spring.models.maestros.UsoInmueble;
import fox.hound.spring.models.puente.CaracteristicaInmueble;

@Entity
@Table(name="inmueble")
//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "inmuebles", singular = "inmueble")
public class Inmueble {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name="tipoInmueble_id", nullable = false)
	@JsonBackReference
	private TipoInmueble tipoInmueble;
	//OneToMany
	//Solicitud
	
	@OneToMany(mappedBy="inmueble")
	@JsonManagedReference
	private List<CaracteristicaInmueble> caracteristicaInmueble;
	@ManyToOne
	@JoinColumn(name="usoInmueble_id", nullable = false)
	@JsonBackReference
	private UsoInmueble usoInmueble;
	@ManyToOne
	@JoinColumn(name="sector_id", nullable = false)
	@JsonBackReference
	private Sector sector;
	@ManyToOne
	@JoinColumn(name="clienteId", nullable = false)
	@JsonBackReference
	private Cliente cliente;
	@Column(nullable = false)
	private String direccion;
	@Column(nullable = false)
	private String descripcion;
	@ManyToOne
	@JoinColumn(name="estatusId", nullable = false)
	@JsonIdentityReference(alwaysAsId = true)
	private Estatus estatus;
	@Column(nullable = true)
	private Date fecha_creacion;
	@Column(nullable = true)
	private Date fecha_modificacion;
	
	public Inmueble(Long id, String direccion, String descripcion, String estatusId, 
			String clienteId, String tipoInmuebleId, String usoInmuebleId, String sectorId) {
		super();
		this.id = id;
		this.direccion = direccion;
		this.descripcion = descripcion;
		this.estatus = new Estatus(estatusId);
		this.cliente = new Cliente(clienteId);
		this.tipoInmueble = new TipoInmueble(tipoInmuebleId);
		this.usoInmueble = new UsoInmueble(usoInmuebleId);
		this.sector = new Sector(sectorId);
	}
	public Inmueble(String id) {
		this.id = Long.valueOf(id);
	}
	public Inmueble() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public TipoInmueble getTipoInmueble() {
		return tipoInmueble;
	}
	public void setTipoInmueble(TipoInmueble tipoInmueble) {
		this.tipoInmueble = tipoInmueble;
	}
	public UsoInmueble getUsoInmueble() {
		return usoInmueble;
	}
	public void setUsoInmueble(UsoInmueble usoInmueble) {
		this.usoInmueble = usoInmueble;
	}
	public Sector getSector() {
		return sector;
	}
	public void setSector(Sector sector) {
		this.sector = sector;
	}
	public List<CaracteristicaInmueble> getCaracteristicaInmueble() {
		return caracteristicaInmueble;
	}
	public void setCaracteristicaInmueble(List<CaracteristicaInmueble> caracteristicaInmueble) {
		this.caracteristicaInmueble = caracteristicaInmueble;
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
	
}

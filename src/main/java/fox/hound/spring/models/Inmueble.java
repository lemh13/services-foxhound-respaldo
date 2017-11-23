package fox.hound.spring.models;

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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.Sector;
import fox.hound.spring.models.maestros.TipoInmueble;
import fox.hound.spring.models.maestros.UsoInmueble;
import fox.hound.spring.models.puente.CaracteristicaInmueble;
import fox.hound.spring.models.puente.Solicitud;

@Entity
@Table(name="inmueble")
//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "inmuebles", singular = "inmueble")
public class Inmueble extends Base {

	@Column(nullable = false)
	private String direccion;
	@Column(nullable = false)
	private String descripcion;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="tipoInmueble_id", nullable = false)
	@JsonBackReference(value="inmueble-tipoInmueble")
	private TipoInmueble tipoInmueble;
	
	@OneToMany(mappedBy="inmueble")
	@JsonManagedReference(value="inmueble-solicitudes")
	private List<Solicitud> solicitudes;
	
	@OneToMany(mappedBy="inmueble")
	@JsonManagedReference(value="inmueble-caracteristicaInmueble")
	private List<CaracteristicaInmueble> caracteristicaInmueble;
	
	@ManyToOne
	@JoinColumn(name="usoInmueble_id", nullable = false)
	@JsonBackReference(value="inmueble-usoInmueble")
	private UsoInmueble usoInmueble;
	@Transient
	private Long usoInmueble_id;
	@Transient
	private String usoInmueble_descripcion;
	
	@ManyToOne
	@JoinColumn(name="sector_id", nullable = false)
	@JsonBackReference(value="inmueble-sector")
	private Sector sector;
	
	@ManyToOne
	@JoinColumn(name="clienteId", nullable = false)
	@JsonBackReference(value="cliente-inmueble")
	private Cliente cliente;
	
	public Inmueble(Long id, String direccion, String descripcion, int estatusId, 
			String clienteId, String tipoInmuebleId, String usoInmuebleId, String sectorId) {
		super(estatusId);
		this.id = id;
		this.direccion = direccion;
		this.descripcion = descripcion;
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
	public List<Solicitud> getSolicitudes() {
		return solicitudes;
	}
	public void setSolicitudes(List<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}
	public Long getUsoInmueble_id() {
		return usoInmueble.getId();
	}
	public void setUsoInmueble_id(Long usoInmueble_id) {
		this.usoInmueble_id = usoInmueble_id;
	}
	public String getUsoInmueble_descripcion() {
		return usoInmueble.getDescripcion();
	}
	public void setUsoInmueble_descripcion(String usoInmueble_descripcion) {
		this.usoInmueble_descripcion = usoInmueble_descripcion;
	}
	
	
}

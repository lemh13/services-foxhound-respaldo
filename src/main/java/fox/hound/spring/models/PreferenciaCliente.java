package fox.hound.spring.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.puente.TipoCaracteristicaInmueble;

//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "preferenciasCliente", singular = "preferenciaCliente")
@Entity
public class PreferenciaCliente {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name="clienteId", nullable = false)
	@JsonBackReference
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(name="tipoCaracteristicaInmuebleId", nullable = false)
	@JsonBackReference
	private TipoCaracteristicaInmueble tipoCaracteristicaInmueble;
	@ManyToOne
	@JoinColumn(name="estatusId", nullable = false)
	@JsonIdentityReference(alwaysAsId = true)
	private Estatus estatus;
	@Column(nullable = true)
	private Date fecha_creacion;
	@Column(nullable = true)
	private Date fecha_modificacion;
	
	public PreferenciaCliente(Long id, String estatusId, String clienteId, String tipoCaracteristicaInmuebleId) {
		super();
		this.id = id;
		this.estatus = new Estatus(estatusId);
		this.cliente = new Cliente(clienteId);
		this.tipoCaracteristicaInmueble = new TipoCaracteristicaInmueble(tipoCaracteristicaInmuebleId);
	}
	public PreferenciaCliente() {
		
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
	public TipoCaracteristicaInmueble getTipoCaracteristicaInmueble() {
		return tipoCaracteristicaInmueble;
	}
	public void setTipoCaracteristicaInmueble(TipoCaracteristicaInmueble tipoCaracteristicaInmueble) {
		this.tipoCaracteristicaInmueble = tipoCaracteristicaInmueble;
	}
	
}

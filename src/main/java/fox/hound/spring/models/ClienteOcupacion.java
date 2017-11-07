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

//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "clienteOcupacion", singular = "clienteOcupacion")
@Entity
public class ClienteOcupacion {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name="clienteId", nullable = false)
	@JsonBackReference
	private Cliente cliente;
	//ManyToOne
	//Ocupacion
	@ManyToOne
	@JoinColumn(name="estatusId", nullable = false)
	@JsonIdentityReference(alwaysAsId = true)
	private Estatus estatus;
	@Column(nullable = true)
	private Date fecha_creacion;
	@Column(nullable = true)
	private Date fecha_modificacion;
	
	public ClienteOcupacion(Long id, Cliente cliente, String estatusId) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.estatus = new Estatus(estatusId);
	}
	public ClienteOcupacion() {
		
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
	
	
}

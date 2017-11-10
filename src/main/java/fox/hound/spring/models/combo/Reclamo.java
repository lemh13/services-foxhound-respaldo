package fox.hound.spring.models.combo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Cliente;
import fox.hound.spring.models.Visita;
import fox.hound.spring.models.maestros.Maestro;
import fox.hound.spring.models.maestros.TipoReclamo;
import fox.hound.spring.models.puente.ReclamoOrdenEntrega;
import fox.hound.spring.models.puente.ReclamoSolicitudServicio;

@Entity
@Table(name="reclamo")
@CustomJsonRootName(plural = "reclamo", singular = "reclamo")
public class Reclamo extends Maestro {
	
	@ManyToOne
	@JoinColumn(name="tipoReclamo_id")
	@JsonBackReference(value="reclamo-tipoReclamo")
	private TipoReclamo tipoReclamo;
	
	@OneToMany(mappedBy="reclamo")
	@JsonManagedReference(value="reclamo-reclamoSolicitudServicios")
	private List<ReclamoSolicitudServicio> reclamoSolicitudServicios;
	
	@OneToMany(mappedBy="reclamo")
	@JsonManagedReference(value="reclamo-reclamoOrdenEntrega")
	private List<ReclamoOrdenEntrega> reclamoOrdenEntrega; 
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	@JsonBackReference(value="cliente-reclamos")
	private Cliente cliente;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "reclamo", cascade = CascadeType.ALL)
	private Visita visita;

	public Reclamo(Long id, String name, String estatusId, String tipoReclamo, String cliente) {
		super(id, name, estatusId);
		this.tipoReclamo = new TipoReclamo(tipoReclamo);
		this.cliente = new Cliente(cliente);
	}
	public Reclamo(String id) {
		super(id);
	}
	public Reclamo() {
		super();
	}
	public List<ReclamoOrdenEntrega> getReclamoOrdenEntrega() {
		return reclamoOrdenEntrega;
	}
	public void setReclamoOrdenEntrega(List<ReclamoOrdenEntrega> reclamoOrdenEntrega) {
		this.reclamoOrdenEntrega = reclamoOrdenEntrega;
	}
	public TipoReclamo getTipoReclamo() {
		return tipoReclamo;
	}
	public void setTipoReclamo(TipoReclamo tipoReclamo) {
		this.tipoReclamo = tipoReclamo;
	}
	public List<ReclamoSolicitudServicio> getReclamoSolicitudServicios() {
		return reclamoSolicitudServicios;
	}
	public void setReclamoSolicitudServicios(List<ReclamoSolicitudServicio> reclamoSolicitudServicios) {
		this.reclamoSolicitudServicios = reclamoSolicitudServicios;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Visita getVisita() {
		return visita;
	}
	public void setVisita(Visita visita) {
		this.visita = visita;
	}
	
	
}
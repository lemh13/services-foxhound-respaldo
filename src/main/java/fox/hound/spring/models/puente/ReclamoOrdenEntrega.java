package fox.hound.spring.models.puente;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.OrdenEntrega;
import fox.hound.spring.models.maestros.TipoReclamo;

@Entity
@Table(name="reclamo_orden_entrega")
@CustomJsonRootName(plural = "reclamoOrdenEntrega", singular = "reclamoOrdenEntrega")
public class ReclamoOrdenEntrega extends Puente {
	
	@ManyToOne
	@JoinColumn(name="tipoReclamo_id")
	@JsonBackReference(value="reclamo-reclamoOrdenEntrega")
	private TipoReclamo tipoReclamo;
	
	@ManyToOne
	@JoinColumn(name="motivoReclamo_id")
	@JsonBackReference(value="reclamoOrdenEntregas-motivoReclamos")
	private MotivoReclamo motivoReclamo;
	
	@ManyToOne
	@JoinColumn(name="ordenEntrega_id")
	@JsonBackReference(value="ordenEntrega-reclamoOrdenEntrega")
	private OrdenEntrega ordenEntrega;
	
	public ReclamoOrdenEntrega() {
		super();
	}
	public ReclamoOrdenEntrega(Long id, int estatusId,
			String tipoReclamo, String motivoReclamo, String ordenEntrega, String descripcion) {
		super(id, estatusId);
		this.tipoReclamo = new TipoReclamo(tipoReclamo);
		this.motivoReclamo = new MotivoReclamo(motivoReclamo);
		this.ordenEntrega = new OrdenEntrega(ordenEntrega);
	}
	public ReclamoOrdenEntrega(String id) {
		super(id);
	}
	
	public TipoReclamo getTipoReclamo() {
		return tipoReclamo;
	}
	public void setTipoReclamo(TipoReclamo tipoReclamo) {
		this.tipoReclamo = tipoReclamo;
	}
	public MotivoReclamo getMotivoReclamo() {
		return motivoReclamo;
	}
	public void setMotivoReclamo(MotivoReclamo motivoReclamo) {
		this.motivoReclamo = motivoReclamo;
	}
	public OrdenEntrega getOrdenEntrega() {
		return ordenEntrega;
	}
	public void setOrdenEntrega(OrdenEntrega ordenEntrega) {
		this.ordenEntrega = ordenEntrega;
	}
	
}

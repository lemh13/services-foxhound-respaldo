package fox.hound.spring.models.puente;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	private TipoReclamo tipoReclamo;;
	
	@ManyToOne
	@JoinColumn(name="motivoReclamo_id")
	@JsonBackReference(value="reclamoOrdenEntregas-motivoReclamos")
	private MotivoReclamo motivoReclamo;
	
	@OneToMany(mappedBy="reclamoOrdenEntrega")
	@JsonManagedReference(value="ordenEntrega-reclamoOrdenEntrega")
	private List<OrdenEntrega> ordenEntregas;
	
	public ReclamoOrdenEntrega() {
		super();
	}
	public ReclamoOrdenEntrega(Long id, int estatusId,
			String tipoReclamo, String motivoReclamo) {
		super(id, estatusId);
		this.tipoReclamo = new TipoReclamo(tipoReclamo);
		this.motivoReclamo = new MotivoReclamo(motivoReclamo);
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
	public List<OrdenEntrega> getOrdenEntregas() {
		return ordenEntregas;
	}
	public void setOrdenEntregas(List<OrdenEntrega> ordenEntregas) {
		this.ordenEntregas = ordenEntregas;
	}
	
}

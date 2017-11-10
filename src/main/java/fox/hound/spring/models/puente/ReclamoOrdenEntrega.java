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
import fox.hound.spring.models.combo.OrdenEntrega;
import fox.hound.spring.models.combo.Reclamo;

@Entity
@Table(name="reclamo_orden_entrega")
@CustomJsonRootName(plural = "reclamoOrdenEntrega", singular = "reclamoOrdenEntrega")
public class ReclamoOrdenEntrega extends Puente {
	
	@ManyToOne
	@JoinColumn(name="reclamo_id")
	@JsonBackReference(value="reclamo-reclamoOrdenEntrega")
	private Reclamo reclamo;
	
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
	public ReclamoOrdenEntrega(Long id, String estatusId,
			String reclamo, String motivoReclamo) {
		super(id, estatusId);
		this.reclamo = new Reclamo(reclamo);
		this.motivoReclamo = new MotivoReclamo(motivoReclamo);
	}
	public ReclamoOrdenEntrega(String id) {
		super(id);
	}
	public Reclamo getReclamo() {
		return reclamo;
	}
	public void setReclamo(Reclamo reclamo) {
		this.reclamo = reclamo;
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

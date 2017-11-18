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
import fox.hound.spring.models.combo.Motivo;

@Entity
@Table(name="motivo_reclamo")
@CustomJsonRootName(plural = "motivoReclamo", singular = "motivoReclamo")
public class MotivoReclamo extends Puente {

	@OneToMany(mappedBy="motivoReclamo")
	@JsonManagedReference(value="reclamoOrdenEntregas-motivoReclamos")
	private List<ReclamoOrdenEntrega> reclamoOrdenEntregas;
	
	@ManyToOne
	@JoinColumn(name="motivo_id")
	@JsonBackReference(value="motivo-motivoReclamos")
	private Motivo motivo;
	
	public MotivoReclamo() {
		super();
	}
	public MotivoReclamo(Long id, int estatusId, String motivo) {
		super(id, estatusId);
		this.motivo = new Motivo(motivo);
	}
	public MotivoReclamo(String id) {
		super(id);
	}
	public List<ReclamoOrdenEntrega> getReclamoOrdenEntregas() {
		return reclamoOrdenEntregas;
	}
	public void setReclamoOrdenEntregas(List<ReclamoOrdenEntrega> reclamoOrdenEntregas) {
		this.reclamoOrdenEntregas = reclamoOrdenEntregas;
	}
	public Motivo getMotivo() {
		return motivo;
	}
	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}
	
}

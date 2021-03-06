package fox.hound.spring.models.puente;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.Motivo;
import fox.hound.spring.models.maestros.Maestro;

@Entity
@Table(name="motivo_reclamo")
@CustomJsonRootName(plural = "motivoReclamo", singular = "motivoReclamo")
public class MotivoReclamo extends Maestro {

	@OneToMany(mappedBy="motivoReclamo")
	@JsonManagedReference(value="reclamoOrdenEntregas-motivoReclamos")
	private List<ReclamoOrdenEntrega> reclamoOrdenEntregas;
	
	@ManyToOne
	@JoinColumn(name="motivo_id")
	@JsonBackReference(value="motivo-motivoReclamos")
	private Motivo motivo;
	@Transient
	private Long padre_id;
	@Transient
	private String padre_descripcion;
	
	public MotivoReclamo() {
		super();
	}
	public MotivoReclamo(Long id, String descripcion, int estatusId, String motivo) {
		super(id, descripcion, estatusId);
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

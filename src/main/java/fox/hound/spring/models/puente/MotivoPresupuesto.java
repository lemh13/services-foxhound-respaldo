package fox.hound.spring.models.puente;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.Motivo;
import fox.hound.spring.models.combo.Presupuesto;

@Entity
@Table(name="motivo_presupuesto")
@CustomJsonRootName(plural = "motivoPresupuesto", singular = "motivoPresupuesto")
public class MotivoPresupuesto extends Puente {

	@ManyToOne
	@JoinColumn(name="motivo_id")
	@JsonBackReference(value="motivo-motivoPresupuestos")
	private Motivo motivo;
	
	@ManyToOne
	@JoinColumn(name="presupuesto_id")
	@JsonBackReference(value="presupuesto-motivoPresupuestos")
	private Presupuesto presupuesto;
	
	public MotivoPresupuesto() {
		super();
	}
	public MotivoPresupuesto(Long id, String estatusId, String motivo, String presupuesto) {
		super(id, estatusId);
		this.motivo = new Motivo(motivo);
		this.presupuesto = new Presupuesto(presupuesto);
	}
	public MotivoPresupuesto(String id) {
		super(id);
	}
	public Motivo getMotivo() {
		return motivo;
	}
	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}
	public Presupuesto getPresupuesto() {
		return presupuesto;
	}
	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}
	
}

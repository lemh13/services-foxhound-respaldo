package fox.hound.spring.models.puente;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.Motivo;

//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "motivoOrdenServicioEventualidad", singular = "motivoOrdenServicioEventualidad")
@Entity
@Table(name="motivo_orden_servicio_eventualidad")
public class MotivoOrdenServicioEventualidad extends Puente {

	@ManyToOne
	@JoinColumn(name="ordenServicioEventualidad_id")
	@JsonBackReference(value="ordenServicioEventualidad-motivoOrdenServicioEventualidads")
	private OrdenServicioEventualidad ordenServicioEventualidad;
	
	@ManyToOne
	@JoinColumn(name="motivo_id")
	@JsonBackReference(value="motivo-motivoOrdenServicioEventualidads")
	private Motivo motivo;
	
	public MotivoOrdenServicioEventualidad(Long id, int estatusId, 
			String ordenServicioEventualidad, String motivo) {
		super(id, estatusId);
		this.ordenServicioEventualidad = new OrdenServicioEventualidad(ordenServicioEventualidad);
		this.motivo = new Motivo(motivo);
	}
	public MotivoOrdenServicioEventualidad(String id) {
		super(id);
	}
	public MotivoOrdenServicioEventualidad() {
		
	}
	public OrdenServicioEventualidad getOrdenServicioEventualidad() {
		return ordenServicioEventualidad;
	}
	public void setOrdenServicioEventualidad(OrdenServicioEventualidad ordenServicioEventualidad) {
		this.ordenServicioEventualidad = ordenServicioEventualidad;
	}
	public Motivo getMotivo() {
		return motivo;
	}
	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}
	
	
}

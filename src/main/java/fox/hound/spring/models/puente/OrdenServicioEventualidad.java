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
import fox.hound.spring.models.OrdenServicio;
import fox.hound.spring.models.combo.Eventualidad;

@Entity
@Table(name="orden_servicio_eventualidad")
@CustomJsonRootName(plural = "ordenServicioEventualidad", singular = "ordenServicioEventualidad")
public class OrdenServicioEventualidad extends Puente {

	@ManyToOne
	@JoinColumn(name="eventualidad_id")
	@JsonBackReference(value="eventualidad-ordenServicioEventualidads")
	private Eventualidad eventualidad;
	
	@OneToMany(mappedBy="ordenServicioEventualidad")
	@JsonManagedReference(value="ordenServicioEventualidad-motivoOrdenServicioEventualidads")
	private List<MotivoOrdenServicioEventualidad> motivoOrdenServicioEventualidads;
	
	@ManyToOne
	@JoinColumn(name="ordenServicio_id")
	@JsonBackReference(value="ordenServicio-ordenServicioEventualidads")
	private OrdenServicio ordenServicio;
	
	public OrdenServicioEventualidad(Long id, int estatusId,
			String eventualidad, String ordenServicio) {
		super(id, estatusId);
		this.eventualidad = new Eventualidad(eventualidad);
		this.ordenServicio = new OrdenServicio(ordenServicio);
	}
	public OrdenServicioEventualidad(String id) {
		super(id);
	}
	public OrdenServicioEventualidad() {
		super();
	}
	public Eventualidad getEventualidad() {
		return eventualidad;
	}
	public void setEventualidad(Eventualidad eventualidad) {
		this.eventualidad = eventualidad;
	}
	public List<MotivoOrdenServicioEventualidad> getMotivoOrdenServicioEventualidads() {
		return motivoOrdenServicioEventualidads;
	}
	public void setMotivoOrdenServicioEventualidads(
			List<MotivoOrdenServicioEventualidad> motivoOrdenServicioEventualidads) {
		this.motivoOrdenServicioEventualidads = motivoOrdenServicioEventualidads;
	}
	public OrdenServicio getOrdenServicio() {
		return ordenServicio;
	}
	public void setOrdenServicio(OrdenServicio ordenServicio) {
		this.ordenServicio = ordenServicio;
	}
	
}

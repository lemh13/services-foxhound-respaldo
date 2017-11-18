package fox.hound.spring.models.puente;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.Eventualidad;

@Entity
@Table(name="solicitud_eventualidad")
@CustomJsonRootName(plural = "solicitudEventualidad", singular = "solicitudEventualidad")
public class SolicitudEventualidad extends Puente {

	@ManyToOne
	@JoinColumn(name="solicitud_id")
	@JsonBackReference(value="solicitudEventualidads-solicitudes")
	private Solicitud solicitud;
	
	@ManyToOne
	@JoinColumn(name="eventualidad_id")
	@JsonBackReference(value="eventualidad-solicitudEventualidads")
	private Eventualidad eventualidad;
	
	public SolicitudEventualidad() {
		super();
	}
	public SolicitudEventualidad(Long id, int estatusId, 
			String solicitud, String eventualidad) {
		super(id, estatusId);
		this.solicitud = new Solicitud(solicitud);
		this.eventualidad = new Eventualidad(eventualidad);
	}
	public SolicitudEventualidad(String id)  {
		super(id);
	}
	public Solicitud getSolicitud() {
		return solicitud;
	}
	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}
	public Eventualidad getEventualidad() {
		return eventualidad;
	}
	public void setEventualidad(Eventualidad eventualidad) {
		this.eventualidad = eventualidad;
	}
	
}

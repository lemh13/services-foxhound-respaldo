package fox.hound.spring.models.puente;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.Reclamo;

@Entity
@Table(name="reclamo_solicitud_servicio")
@CustomJsonRootName(plural = "reclamoSolicitudServicio", singular = "reclamoSolicitudServicio")
public class ReclamoSolicitudServicio extends Puente {

	@ManyToOne
	@JoinColumn(name="reclamo_id")
	@JsonBackReference(value="reclamo-reclamoSolicitudServicios")
	private Reclamo reclamo;

	@ManyToOne
	@JoinColumn(name="solicitudServicio_id")
	@JsonBackReference(value="solicitudServicio-reclamoSolicitudServicios")
	private SolicitudServicio solicitudServicio;
	
	public ReclamoSolicitudServicio(Long id, String estatusId, String reclamo, 
			String solicitudServicio) {
		super(id, estatusId);
		this.reclamo = new Reclamo(reclamo);
		this.solicitudServicio = new SolicitudServicio(solicitudServicio);
	}
	public ReclamoSolicitudServicio(String id) {
		super(id);
	}
	public ReclamoSolicitudServicio() {
		super();
	}
	public Reclamo getReclamo() {
		return reclamo;
	}
	public void setReclamo(Reclamo reclamo) {
		this.reclamo = reclamo;
	}
	public SolicitudServicio getSolicitudServicio() {
		return solicitudServicio;
	}
	public void setSolicitudServicio(SolicitudServicio solicitudServicio) {
		this.solicitudServicio = solicitudServicio;
	}
	
}

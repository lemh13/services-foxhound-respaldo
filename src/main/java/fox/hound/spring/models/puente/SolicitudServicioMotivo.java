package fox.hound.spring.models.puente;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.Motivo;

@Entity
@Table(name="solicitud_servicio_motivo")
@CustomJsonRootName(plural = "solicitudServicioMotivo", singular = "solicitudServicioMotivo")
public class SolicitudServicioMotivo extends Puente {
	
	@ManyToOne
	@JoinColumn(name="solicitudServicio_id")
	@JsonBackReference(value="solicitudServicioMotivos-solicitudServicios")
	private SolicitudServicio solicitudServicio;
	
	@ManyToOne
	@JoinColumn(name="motivo_id")
	@JsonBackReference(value="motivo-solicitudServicioMotivos")
	private Motivo motivo;
	
	public SolicitudServicioMotivo(Long id, int estatusId, 
			String solicitudServicioId, String motivoId) {
		super(id, estatusId);
		this.solicitudServicio = new SolicitudServicio(solicitudServicioId);
		this.motivo = new Motivo(motivoId);
	}
	public SolicitudServicioMotivo(String id) {
		super(id);
	}
	public SolicitudServicioMotivo() {
		super();
	}
	public SolicitudServicio getSolicitudServicio() {
		return solicitudServicio;
	}
	public void setSolicitudServicio(SolicitudServicio solicitudServicio) {
		this.solicitudServicio = solicitudServicio;
	}
	public Motivo getMotivo() {
		return motivo;
	}
	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}
	
}

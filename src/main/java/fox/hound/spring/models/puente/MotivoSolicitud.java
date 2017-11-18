package fox.hound.spring.models.puente;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.Motivo;

@Entity
@Table(name="motivo_solicitud")
@CustomJsonRootName(plural = "motivoSolicitud", singular = "motivoSolicitud")
public class MotivoSolicitud extends Puente {

	@ManyToOne
	@JoinColumn(name="solicitud_id")
	@JsonBackReference(value="solicitud-motivoSolicitud")
	private Solicitud solicitud;
	
	@ManyToOne
	@JoinColumn(name="motivo_id")
	@JsonBackReference(value="motivo-motivoSolicitud")
	private Motivo motivo;
	
	public MotivoSolicitud() {
		super();
	}
	public MotivoSolicitud(Long id, int estatusId, String motivo, String solicitud) {
		super(id, estatusId);
		this.motivo = new Motivo(motivo);
		this.solicitud = new Solicitud(solicitud);
	}
	public MotivoSolicitud(String id) {
		super(id);
	}
	public Motivo getMotivo() {
		return motivo;
	}
	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}
	public Solicitud getSolicitud() {
		return solicitud;
	}
	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}
	
	
}

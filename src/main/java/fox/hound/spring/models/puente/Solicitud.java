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
import fox.hound.spring.models.Inmueble;
import fox.hound.spring.models.Visita;
import fox.hound.spring.models.combo.Motivo;

@Entity
@Table(name="solicitud")
@CustomJsonRootName(plural = "solicitud", singular = "solicitud")
public class Solicitud extends Puente {

	@OneToMany(mappedBy="solicitud")
	@JsonManagedReference(value="solicitudServicios-solicitudes")
	private List<SolicitudServicio> solicitudServicios;
	
	@ManyToOne
	@JoinColumn(name="inmueble_id")
	@JsonBackReference(value="inmueble-solicitudes")
	private Inmueble inmueble;
	
	@ManyToOne
	@JoinColumn(name="motivo_id")
	@JsonBackReference(value="motivo-solicitudes")
	private Motivo motivo;
	
	@OneToMany(mappedBy="solicitud")
	@JsonManagedReference(value="visita-solicitud")
	private List<Visita> visitas;
	
	@OneToMany(mappedBy="solicitud")
	@JsonManagedReference(value="solicitudEventualidads-solicitudes")
	private List<SolicitudEventualidad> solicitudEventualidads;
	
	public Solicitud() {
		super();
	}
	public Solicitud(Long id, String estatusId, String motivoId, String inmuebleId) {
		super(id, estatusId);
		this.motivo = new Motivo(motivoId);
		this.inmueble = new Inmueble(inmuebleId);
	}
	public Solicitud(String id)  {
		super(id);
	}
	public List<SolicitudServicio> getSolicitudServicios() {
		return solicitudServicios;
	}
	public void setSolicitudServicios(List<SolicitudServicio> solicitudServicios) {
		this.solicitudServicios = solicitudServicios;
	}
	public Inmueble getInmueble() {
		return inmueble;
	}
	public void setInmueble(Inmueble inmueble) {
		this.inmueble = inmueble;
	}
	public Motivo getMotivo() {
		return motivo;
	}
	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}
	public List<Visita> getVisitas() {
		return visitas;
	}
	public void setVisitas(List<Visita> visitas) {
		this.visitas = visitas;
	}
	public List<SolicitudEventualidad> getSolicitudEventualidads() {
		return solicitudEventualidads;
	}
	public void setSolicitudEventualidads(List<SolicitudEventualidad> solicitudEventualidads) {
		this.solicitudEventualidads = solicitudEventualidads;
	}
	
	
}

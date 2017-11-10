package fox.hound.spring.models.combo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.maestros.Maestro;
import fox.hound.spring.models.maestros.TipoMotivo;
import fox.hound.spring.models.puente.MotivoOrdenServicioEventualidad;
import fox.hound.spring.models.puente.MotivoPresupuesto;
import fox.hound.spring.models.puente.MotivoReclamo;
import fox.hound.spring.models.puente.Solicitud;
import fox.hound.spring.models.puente.SolicitudServicioMotivo;

@Entity
@Table(name="motivo")
@CustomJsonRootName(plural = "motivo", singular = "motivo")
public class Motivo extends Maestro {
	
	@ManyToOne
	@JoinColumn(name="tipoMotivo_id")
	@JsonBackReference(value="motivo-tipoMotivo")
	private TipoMotivo tipoMotivo;
	
	@OneToMany(mappedBy="motivo")
	@JsonManagedReference(value="motivo-solicitudServicioMotivos")
	private List<SolicitudServicioMotivo> solicitudServicioMotivos;
	
	@OneToMany(mappedBy="motivo")
	@JsonManagedReference(value="motivo-solicitudes")
	private List<Solicitud> solicitudes;
	
	@OneToMany(mappedBy="motivo")
	@JsonManagedReference(value="motivo-motivoReclamos")
	private List<MotivoReclamo> motivoReclamos;
	
	@OneToMany(mappedBy="motivo")
	@JsonManagedReference(value="motivo-motivoOrdenServicioEventualidads")
	private List<MotivoOrdenServicioEventualidad> motivoOrdenServicioEventualidads;
	
	@OneToMany(mappedBy="motivo")
	@JsonManagedReference(value="motivo-motivoPresupuestos")
	private List<MotivoPresupuesto> motivoPresupuestos;
	
	public Motivo(Long id, String name, String estatusId, String tipoMotivoId) {
		super(id, name, estatusId);
		this.tipoMotivo = new TipoMotivo(tipoMotivoId);
	}
	public Motivo(String id) {
		super(id);
	}
	public Motivo() {
		super();
	}
	public TipoMotivo getTipoMotivo() {
		return tipoMotivo;
	}
	public void setTipoMotivo(TipoMotivo tipoMotivo) {
		this.tipoMotivo = tipoMotivo;
	}
	public List<SolicitudServicioMotivo> getSolicitudServicioMotivos() {
		return solicitudServicioMotivos;
	}
	public void setSolicitudServicioMotivos(List<SolicitudServicioMotivo> solicitudServicioMotivos) {
		this.solicitudServicioMotivos = solicitudServicioMotivos;
	}
	public List<Solicitud> getSolicitudes() {
		return solicitudes;
	}
	public void setSolicitudes(List<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}
	public List<MotivoReclamo> getMotivoReclamos() {
		return motivoReclamos;
	}
	public void setMotivoReclamos(List<MotivoReclamo> motivoReclamos) {
		this.motivoReclamos = motivoReclamos;
	}
	public List<MotivoOrdenServicioEventualidad> getMotivoOrdenServicioEventualidads() {
		return motivoOrdenServicioEventualidads;
	}
	public void setMotivoOrdenServicioEventualidads(
			List<MotivoOrdenServicioEventualidad> motivoOrdenServicioEventualidads) {
		this.motivoOrdenServicioEventualidads = motivoOrdenServicioEventualidads;
	}
	public List<MotivoPresupuesto> getMotivoPresupuestos() {
		return motivoPresupuestos;
	}
	public void setMotivoPresupuestos(List<MotivoPresupuesto> motivoPresupuestos) {
		this.motivoPresupuestos = motivoPresupuestos;
	}
	
	
}
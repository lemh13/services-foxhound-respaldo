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
import fox.hound.spring.models.DetallePresupuesto;
import fox.hound.spring.models.Servicio;

@Entity
@Table(name="solicitud_servicio")
@CustomJsonRootName(plural = "solicitudServicio", singular = "solicitudServicio")
public class SolicitudServicio extends Puente {

	@ManyToOne
	@JoinColumn(name="servicio_id", nullable = false)
	@JsonBackReference(value="servicio-solicitudServicios")
	private Servicio servicio;

	@OneToMany(mappedBy="solicitudServicio")
	@JsonManagedReference(value="solicitudServicioMotivos-solicitudServicios")
	private List<SolicitudServicioMotivo> solicitudServicioMotivos;
	
	@ManyToOne
	@JoinColumn(name="solicitud_id", nullable = false)
	@JsonBackReference(value="solicitudServicios-solicitudes")
	private Solicitud solicitud;
	
	@OneToMany(mappedBy="solicitudServicio")
	@JsonManagedReference(value="detallePresupuesto-solicitudServicio")
	private List<DetallePresupuesto> detallePresupuestos;
	
	@OneToMany(mappedBy="solicitudServicio")
	@JsonManagedReference(value="solicitudServicio-detalleOrdenServicios")
	private List<DetalleOrdenServicio> detalleOrdenServicios;
	
	public SolicitudServicio() {
		super();
	}
	public SolicitudServicio(Long id, int estatusId, String servicioId, String solicitudId) {
		super(id, estatusId);
		this.solicitud = new Solicitud(solicitudId);
		this.servicio = new Servicio(servicioId);
	}
	public SolicitudServicio(String id) {
		super(id);
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	public SolicitudServicioMotivo getSolicitudServicioMotivos() {
		return solicitudServicioMotivos.get(0);
	}
	public void setSolicitudServicioMotivos(List<SolicitudServicioMotivo> solicitudServicioMotivos) {
		this.solicitudServicioMotivos = solicitudServicioMotivos;
	}
	public Solicitud getSolicitud() {
		return solicitud;
	}
	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}
	public List<DetallePresupuesto> getDetallePresupuestos() {
		return detallePresupuestos;
	}
	public void setDetallePresupuestos(List<DetallePresupuesto> detallePresupuestos) {
		this.detallePresupuestos = detallePresupuestos;
	}
	public List<DetalleOrdenServicio> getDetalleOrdenServicios() {
		return detalleOrdenServicios;
	}
	public void setDetalleOrdenServicios(List<DetalleOrdenServicio> detalleOrdenServicios) {
		this.detalleOrdenServicios = detalleOrdenServicios;
	}
	
}

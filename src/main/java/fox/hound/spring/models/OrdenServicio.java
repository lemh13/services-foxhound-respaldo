package fox.hound.spring.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.DiagnosticoVisita;
import fox.hound.spring.models.combo.Respuesta;
import fox.hound.spring.models.maestros.EstadoServicio;
import fox.hound.spring.models.puente.DetalleOrdenServicio;
import fox.hound.spring.models.puente.OrdenServicioEventualidad;

@Entity
@Table(name="orden_servicio")
//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "ordenServicio", singular = "ordenServicio")
public class OrdenServicio extends Base {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Date fechaInicio;
	@Column(nullable = false)
	private Date fechaCulminacion;
	
	@OneToMany(mappedBy="ordenServicio")
	@JsonManagedReference(value="ordenServicio-detalleOrdenServicios")
	private List<DetalleOrdenServicio> detalleOrdenServicios;
	
	@ManyToOne
	@JoinColumn(name="estadoServicio_id")
	@JsonBackReference(value="ordenServicio-estadoServicio")
	private EstadoServicio estadoServicio;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "ordenServicio", cascade = CascadeType.ALL)
	private Respuesta respuesta;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "ordenServicio", cascade = CascadeType.ALL)
	private DiagnosticoVisita diagnosticoVisita;
	
	@OneToMany(mappedBy="ordenServicio")
	@JsonManagedReference(value="ordenServicio-ordenServicioEventualidads")
	private List<OrdenServicioEventualidad> ordenServicioEventualidads;
	
	public OrdenServicio(Long id, Date fechaInicio, Date fechaCulminacion, String estatus, String estadoServicio) {
		super(estatus);
		this.id = id;
		this.fechaInicio = fechaInicio;
		this.fechaCulminacion = fechaCulminacion;
		this.estadoServicio = new EstadoServicio(estadoServicio);
	}
	public OrdenServicio(String id) {
		this.id = Long.valueOf(id);
	}
	public OrdenServicio() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaCulminacion() {
		return fechaCulminacion;
	}
	public void setFechaCulminacion(Date fechaCulminacion) {
		this.fechaCulminacion = fechaCulminacion;
	}
	public List<DetalleOrdenServicio> getDetalleOrdenServicios() {
		return detalleOrdenServicios;
	}
	public void setDetalleOrdenServicios(List<DetalleOrdenServicio> detalleOrdenServicios) {
		this.detalleOrdenServicios = detalleOrdenServicios;
	}
	public EstadoServicio getEstadoServicio() {
		return estadoServicio;
	}
	public void setEstadoServicio(EstadoServicio estadoServicio) {
		this.estadoServicio = estadoServicio;
	}
	public Respuesta getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}
	public DiagnosticoVisita getDiagnosticoVisita() {
		return diagnosticoVisita;
	}
	public void setDiagnosticoVisita(DiagnosticoVisita diagnosticoVisita) {
		this.diagnosticoVisita = diagnosticoVisita;
	}
	public List<OrdenServicioEventualidad> getOrdenServicioEventualidads() {
		return ordenServicioEventualidads;
	}
	public void setOrdenServicioEventualidads(List<OrdenServicioEventualidad> ordenServicioEventualidads) {
		this.ordenServicioEventualidads = ordenServicioEventualidads;
	}
	
}

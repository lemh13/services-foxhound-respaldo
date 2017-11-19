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
import fox.hound.spring.models.maestros.TipoOrdenServicio;
import fox.hound.spring.models.puente.DetalleOrdenServicio;
import fox.hound.spring.models.puente.OrdenServicioEventualidad;
import fox.hound.spring.models.puente.ValoracionOrdenServicio;

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
	
	@OneToMany(mappedBy="ordenServicio")
	@JsonManagedReference(value="ordenServicio-visitas")
	private List<Visita> visitas;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "ordenServicio", cascade = CascadeType.ALL)
	private Respuesta respuesta;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "ordenServicio", cascade = CascadeType.ALL)
	private DiagnosticoVisita diagnosticoVisita;
	
	@OneToMany(mappedBy="ordenServicio")
	@JsonManagedReference(value="ordenServicio-ordenServicioEventualidads")
	private List<OrdenServicioEventualidad> ordenServicioEventualidads;
	
	@ManyToOne
	@JoinColumn(name="tipoOrdenServicio_id")
	@JsonBackReference(value="ordenServicios-tipoOrdenServicio")
	private TipoOrdenServicio tipoOrdenServicio;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "ordenServicio", cascade = CascadeType.ALL)
	private ValoracionOrdenServicio valoracionOrdenServicio;
	
	public OrdenServicio(Long id, Date fechaInicio, Date fechaCulminacion, int estatus,
			String tipoOrdenServicio) {
		super(estatus);
		this.id = id;
		this.fechaInicio = fechaInicio;
		this.fechaCulminacion = fechaCulminacion;
		this.tipoOrdenServicio = new TipoOrdenServicio(tipoOrdenServicio);
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
	public List<Visita> getVisitas() {
		return visitas;
	}
	public void setVisitas(List<Visita> visitas) {
		this.visitas = visitas;
	}
	public TipoOrdenServicio getTipoOrdenServicio() {
		return tipoOrdenServicio;
	}
	public void setTipoOrdenServicio(TipoOrdenServicio tipoOrdenServicio) {
		this.tipoOrdenServicio = tipoOrdenServicio;
	}
	public ValoracionOrdenServicio getValoracionOrdenServicio() {
		return valoracionOrdenServicio;
	}
	public void setValoracionOrdenServicio(ValoracionOrdenServicio valoracionOrdenServicio) {
		this.valoracionOrdenServicio = valoracionOrdenServicio;
	}
	
	
}

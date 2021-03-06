package fox.hound.spring.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.ServicioTarea;
import fox.hound.spring.models.combo.Zona;
import fox.hound.spring.models.maestros.CalificarServicio;
import fox.hound.spring.models.maestros.Categoria;
import fox.hound.spring.models.maestros.TipoServicio;
import fox.hound.spring.models.maestros.UnidadMedida;
import fox.hound.spring.models.puente.DetalleServicioInmueble;
import fox.hound.spring.models.puente.PromocionServicio;
import fox.hound.spring.models.puente.SolicitudServicio;
import fox.hound.spring.models.puente.TipoCaracteristicaServicio;
import fox.hound.spring.models.puente.TipoInmuebleServicio;

@Entity
@Table(name="servicio")
@CustomJsonRootName(plural = "servicio", singular = "servicio")
public class Servicio extends Base {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
    private String titulo;
	@Column(nullable = false)
    private String descripcion;
	@Column(nullable = false)
    private String imagenServicio;
	@Column(nullable = false)
    private double costo;
	
	@OneToMany(mappedBy="servicio")
	@JsonManagedReference(value="servicio-servicioTareas")
	private List<ServicioTarea> servicioTareas;
	
	@OneToMany(mappedBy="servicio")
	@JsonManagedReference(value="servicio-tipoInmuebleServicio")
	private List<TipoInmuebleServicio> tipoInmuebleServicio;
	
	@ManyToOne
	@JoinColumn(name="tipoServicio_id")
	@JsonBackReference(value="servicio-tipoServicio")
	private TipoServicio tipoServicio;
	@Transient
	private Long tipoServicio_id;
	@Transient
	private String tipoServicio_descripcion;

	@OneToMany(mappedBy="servicio")
	@JsonManagedReference(value="servicio-promocionServicio")
	private List<PromocionServicio> promocionServicios;
	
	@OneToMany(mappedBy="servicio")
	@JsonManagedReference(value="servicio-calificarServicio")
	private List<CalificarServicio> calificarServicio;
	
	@OneToMany(mappedBy="servicio")
	@JsonManagedReference(value="detalleServicioInmueble-servicio")
	private List<DetalleServicioInmueble> detalleServicioInmuebles;
	
	@OneToMany(mappedBy="servicio")
	@JsonManagedReference(value="servicio-zonas")
	private List<Zona> zonas;
	
	@ManyToOne
	@JoinColumn(name="unidadMedida_id")
	@JsonBackReference(value="servicio-unidadMedida")
	private UnidadMedida unidadMedida;
	
	@OneToMany(mappedBy="servicio")
	@JsonManagedReference(value="servicio-solicitudServicios")
	private List<SolicitudServicio> solicitudServicios;
	
	@OneToMany(mappedBy="servicio")
	@JsonManagedReference(value="servicio-tipoCaracteristicaServicios")
	private List<TipoCaracteristicaServicio> tipoCaracteristicaServicios;
	
	@ManyToOne
	@JoinColumn(name="garantia_id")
	@JsonBackReference(value="garantia-servicios")
	private Garantia garantia;
	
	@ManyToOne
	@JoinColumn(name="empresa_id")
	@JsonBackReference(value="empresa-servicios")
	private Empresa empresa;
	
	public Servicio(Long id, String titulo, String descripcion, String imagenServicio, double costo, int estatusId,
			String tipoServicioId, String categoriaId, String unidadMedidaId, String empresaId, String garantia) {
		super(estatusId);
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.imagenServicio = imagenServicio;
		this.costo = costo;
		this.tipoServicio = new TipoServicio(tipoServicioId);
		this.unidadMedida = new UnidadMedida(unidadMedidaId);
		this.empresa = new Empresa(empresaId);
		this.garantia = new Garantia(garantia);
	}
	public Servicio(String id) {
		this.id = Long.valueOf(id);
	}
	public Servicio() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getImagenServicio() {
		return imagenServicio;
	}
	public void setImagenServicio(String imagenServicio) {
		this.imagenServicio = imagenServicio;
	}
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}	
	public List<ServicioTarea> getServicioTareas() {
		return servicioTareas;
	}
	public void setServicioTareas(List<ServicioTarea> servicioTareas) {
		this.servicioTareas = servicioTareas;
	}
	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public UnidadMedida getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	
	public List<PromocionServicio> getPromocionServicios() {
		return promocionServicios;
	}
	public void setPromocionServicios(List<PromocionServicio> promocionServicios) {
		this.promocionServicios = promocionServicios;
	}
	public List<CalificarServicio> getCalificarServicio() {
		return calificarServicio;
	}
	public void setCalificarServicio(List<CalificarServicio> calificarServicio) {
		this.calificarServicio = calificarServicio;
	}
	public List<Zona> getZonas() {
		return zonas;
	}
	public void setZonas(List<Zona> zonas) {
		this.zonas = zonas;
	}
	public List<SolicitudServicio> getSolicitudServicios() {
		return solicitudServicios;
	}
	public void setSolicitudServicios(List<SolicitudServicio> solicitudServicios) {
		this.solicitudServicios = solicitudServicios;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public List<TipoCaracteristicaServicio> getTipoCaracteristicaServicios() {
		return tipoCaracteristicaServicios;
	}
	public void setTipoCaracteristicaServicios(List<TipoCaracteristicaServicio> tipoCaracteristicaServicios) {
		this.tipoCaracteristicaServicios = tipoCaracteristicaServicios;
	}
	public Garantia getGarantia() {
		return garantia;
	}
	public void setGarantia(Garantia garantia) {
		this.garantia = garantia;
	}
	public List<TipoInmuebleServicio> getTipoInmuebleServicio() {
		return tipoInmuebleServicio;
	}
	public void setTipoInmuebleServicio(List<TipoInmuebleServicio> tipoInmuebleServicio) {
		this.tipoInmuebleServicio = tipoInmuebleServicio;
	}
	public List<DetalleServicioInmueble> getDetalleServicioInmuebles() {
		return detalleServicioInmuebles;
	}
	public void setDetalleServicioInmuebles(List<DetalleServicioInmueble> detalleServicioInmuebles) {
		this.detalleServicioInmuebles = detalleServicioInmuebles;
	}
	public Long getTipoServicio_id() {
		return tipoServicio.getId();
	}
	public void setTipoServicio_id(Long tipoServicioId) {
		this.tipoServicio_id = tipoServicioId;
	}
	public String getTipoServicio_descripcion() {
		return tipoServicio.getDescripcion();
	}
	public void setTipoServicio_descripcion(String tipoServicioDescripcion) {
		this.tipoServicio_descripcion = tipoServicioDescripcion;
	}
	
	
	
}

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.ServicioHerramienta;
import fox.hound.spring.models.combo.ServicioTarea;
import fox.hound.spring.models.combo.Zona;
import fox.hound.spring.models.maestros.CalificarServicio;
import fox.hound.spring.models.maestros.Categoria;
import fox.hound.spring.models.maestros.TipoServicio;
import fox.hound.spring.models.maestros.UnidadMedida;
import fox.hound.spring.models.puente.Promocion;
import fox.hound.spring.models.puente.ServicioGarantia;
import fox.hound.spring.models.puente.ServicioRecurso;
import fox.hound.spring.models.puente.SolicitudServicio;
import fox.hound.spring.models.puente.TipoCaracteristicaInmueble;
import fox.hound.spring.models.puente.TipoCaracteristicaServicio;

@Entity
@Table(name="servicio")
@CustomJsonRootName(plural = "servicios", singular = "servicio")
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
	@JsonManagedReference(value="servicio-servicioGarantias")
	private List<ServicioGarantia> servicioGarantias;
	
	@OneToMany(mappedBy="servicio")
	@JsonManagedReference(value="servicio-servicioRecursos")
	private List<ServicioRecurso> servicioRecursos;
	
	@OneToMany(mappedBy="servicio")
	@JsonManagedReference(value="servicio-servicioTareas")
	private List<ServicioTarea> servicioTareas;
	
	@OneToMany(mappedBy="servicio")
	@JsonManagedReference(value="servicio-servicioHerramientas")
	private List<ServicioHerramienta> servicioHerramientas;
	
	@ManyToOne
	@JoinColumn(name="tipoServicio_id")
	@JsonBackReference(value="servicio-tipoServicio")
	private TipoServicio tipoServicio;
	
	@ManyToOne
	@JoinColumn(name="categoria_id")
	@JsonBackReference(value="servicio-categoria")
	private Categoria categoria;
	
	@OneToMany(mappedBy="servicio")
	@JsonManagedReference(value="servicio-promociones")
	private List<Promocion> promociones;
	
	@OneToMany(mappedBy="servicio")
	@JsonManagedReference(value="servicio-calificarServicio")
	private List<CalificarServicio> calificarServicio;
	
	@OneToMany(mappedBy="servicio")
	@JsonManagedReference(value="servicio-tipoCaracteristicaInmueble")
	private List<TipoCaracteristicaInmueble> tipoCaracteristicaInmueble;
	
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
	@JoinColumn(name="empresa_id")
	@JsonBackReference(value="empresa-servicios")
	private Empresa empresa;
	
	public Servicio(Long id, String titulo, String descripcion, String imagenServicio, double costo, String estatusId,
			String tipoServicioId, String categoriaId, String unidadMedidaId, String empresaId) {
		super(estatusId);
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.imagenServicio = imagenServicio;
		this.costo = costo;
		this.tipoServicio = new TipoServicio(tipoServicioId);
		this.categoria = new Categoria(categoriaId);
		this.unidadMedida = new UnidadMedida(unidadMedidaId);
		this.empresa = new Empresa(empresaId);
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
	public List<ServicioGarantia> getServicioGarantias() {
		return servicioGarantias;
	}
	public void setServicioGarantias(List<ServicioGarantia> servicioGarantias) {
		this.servicioGarantias = servicioGarantias;
	}
	public List<ServicioRecurso> getServicioRecursos() {
		return servicioRecursos;
	}
	public void setServicioRecursos(List<ServicioRecurso> servicioRecursos) {
		this.servicioRecursos = servicioRecursos;
	}
	public List<ServicioTarea> getServicioTareas() {
		return servicioTareas;
	}
	public void setServicioTareas(List<ServicioTarea> servicioTareas) {
		this.servicioTareas = servicioTareas;
	}
	public List<ServicioHerramienta> getServicioHerramientas() {
		return servicioHerramientas;
	}
	public void setServicioHerramientas(List<ServicioHerramienta> servicioHerramientas) {
		this.servicioHerramientas = servicioHerramientas;
	}
	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public UnidadMedida getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public List<Promocion> getPromociones() {
		return promociones;
	}
	public void setPromociones(List<Promocion> promociones) {
		this.promociones = promociones;
	}
	public List<CalificarServicio> getCalificarServicio() {
		return calificarServicio;
	}
	public void setCalificarServicio(List<CalificarServicio> calificarServicio) {
		this.calificarServicio = calificarServicio;
	}
	public List<TipoCaracteristicaInmueble> getTipoCaracteristicaInmueble() {
		return tipoCaracteristicaInmueble;
	}
	public void setTipoCaracteristicaInmueble(List<TipoCaracteristicaInmueble> tipoCaracteristicaInmueble) {
		this.tipoCaracteristicaInmueble = tipoCaracteristicaInmueble;
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
	
}
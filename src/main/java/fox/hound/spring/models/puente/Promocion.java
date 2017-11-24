package fox.hound.spring.models.puente;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.Descuento;
import fox.hound.spring.models.maestros.TipoPromocion;

@Entity
@Table(name="promocion")
//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "promocion", singular = "promocion")
public class Promocion extends Puente {

	@Column(nullable = false)
	private String titulo;
	@Column(nullable = false)
	private String descripcion;
	@Column(nullable = false)
	private Date fecha_inicio;
	@Column(nullable = false)
	private Date fecha_caducidad;
	@Column(nullable = false)
    private String imagenServicio;
	@Transient
	private Long dias_caducidad; 
	
	@ManyToOne
	@JoinColumn(name="tipoPromocionId", nullable = false)
	@JsonBackReference(value="tipoPromocion-promociones")
	private TipoPromocion tipoPromocion;
	@Transient
	private Long tipoPromocion_id;
	@Transient
	private String tipoPromocion_descripcion;
	
	@ManyToOne
	@JoinColumn(name="descuentoId", nullable = false)
	@JsonBackReference(value="descuento-promociones")
	private Descuento descuento;
	@Transient
	private Long descuento_id;
	@Transient
	private double descuento_procentaje;
	
	@OneToMany(mappedBy="servicio")
	@JsonManagedReference(value="promocionServicio-promociones")
	private List<PromocionServicio> promocionServicios;
	
	public Promocion(Long id, int estatusId, Date fecha_caducidad, String tipoPromocionId, String descuentoId,
			String titulo, String descripcion, Date fecha_inicio, String imagenServicio) {
		super(id, estatusId);
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.imagenServicio = imagenServicio;
		this.tipoPromocion = new TipoPromocion(tipoPromocionId);
		this.descuento = new Descuento(descuentoId);
		this.fecha_inicio = fecha_inicio;
		this.fecha_caducidad = fecha_caducidad;
	}
	public Promocion(String id) {
		super(id);
	}
	public Promocion() {
		
	}
	public TipoPromocion getTipoPromocion() {
		return tipoPromocion;
	}
	public void setTipoPromocion(TipoPromocion tipoPromocion) {
		this.tipoPromocion = tipoPromocion;
	}
	public Descuento getDescuento() {
		return descuento;
	}
	public void setDescuento(Descuento descuento) {
		this.descuento = descuento;
	}
	
	public List<PromocionServicio> getPromocionServicios() {
		return promocionServicios;
	}
	public void setPromocionServicios(List<PromocionServicio> promocionServicios) {
		this.promocionServicios = promocionServicios;
	}
	public Date getFecha_caducidad() {
		return fecha_caducidad;
	}
	public void setFecha_caducidad(Date fecha_caducidad) {
		this.fecha_caducidad = fecha_caducidad;
	}
	public Long getTipoPromocion_id() {
		return tipoPromocion.getId();
	}
	public void setTipoPromocion_id(Long tipoPromocion_id) {
		this.tipoPromocion_id = tipoPromocion_id;
	}
	public Long getDescuento_id() {
		return descuento.getId();
	}
	public void setDescuento_id(Long descuento_id) {
		this.descuento_id = descuento_id;
	}
	public String getTipoPromocion_descripcion() {
		return tipoPromocion.getDescripcion();
	}
	public void setTipoPromocion_descripcion(String tipoPromocion_descripcion) {
		this.tipoPromocion_descripcion = tipoPromocion_descripcion;
	}
	public double getDescuento_procentaje() {
		return descuento.getPorcentaje();
	}
	public void setDescuento_procentaje(double descuento_procentaje) {
		this.descuento_procentaje = descuento_procentaje;
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
	public Date getFecha_inicio() {
		return fecha_inicio;
	}
	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public String getImagenServicio() {
		return imagenServicio;
	}
	public void setImagenServicio(String imagenServicio) {
		this.imagenServicio = imagenServicio;
	}
	public Long getDias_caducidad() {
		long diff = Math.abs(getFecha_creacion().getTime() - getFecha_caducidad().getTime());
		long diffDays = diff / (24 * 60 * 60 * 1000);
		return diffDays;
	}
	public void setDias_caducidad(Long dias_caducidad) {
		this.dias_caducidad = dias_caducidad;
	}
	
	
}

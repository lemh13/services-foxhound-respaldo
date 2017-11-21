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
	private Date fecha_caducidad;
	@Column(nullable = false)
	private boolean prioridad;
	
	@ManyToOne
	@JoinColumn(name="tipoPromocionId", nullable = false)
	@JsonBackReference(value="tipoPromocion-promociones")
	private TipoPromocion tipoPromocion;
	@Transient
	private Long tipoPromocion_id;
	
	@ManyToOne
	@JoinColumn(name="descuentoId", nullable = false)
	@JsonBackReference(value="descuento-promociones")
	private Descuento descuento;
	@Transient
	private Long descuento_id;
	
	@OneToMany(mappedBy="servicio")
	@JsonManagedReference(value="promocionServicio-promociones")
	private List<PromocionServicio> promocionServicios;
	
	public Promocion(Long id, int estatusId, Date fecha_caducidad, String tipoPromocionId, String descuentoId,
			boolean prioridad) {
		super(id, estatusId);
		this.prioridad = prioridad;
		this.tipoPromocion = new TipoPromocion(tipoPromocionId);
		this.descuento = new Descuento(descuentoId);
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
	public boolean isPrioridad() {
		return prioridad;
	}
	public void setPrioridad(boolean prioridad) {
		this.prioridad = prioridad;
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
	
	
}

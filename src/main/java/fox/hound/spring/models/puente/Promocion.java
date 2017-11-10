package fox.hound.spring.models.puente;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Servicio;
import fox.hound.spring.models.combo.Descuento;
import fox.hound.spring.models.maestros.TipoPromocion;

@Entity
@Table(name="promocion")
//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "promocion", singular = "promocion")
public class Promocion extends Puente {

	@Column(nullable = false)
	private Date fecha_caducidad;
	
	@ManyToOne
	@JoinColumn(name="tipoPromocionId", nullable = false)
	@JsonBackReference(value="tipoPromocion-promociones")
	private TipoPromocion tipoPromocion;
	
	@ManyToOne
	@JoinColumn(name="descuentoId", nullable = false)
	@JsonBackReference(value="descuento-promociones")
	private Descuento descuento;
	
	@ManyToOne
	@JoinColumn(name="servicio_id", nullable = false)
	@JsonBackReference(value="servicio-promociones")
	private Servicio servicio;
	
	public Promocion(Long id, String estatusId, Date fecha_caducidad, String tipoPromocionId, String descuentoId,
			String servicioId) {
		super(id, estatusId);
		this.tipoPromocion = new TipoPromocion(tipoPromocionId);
		this.descuento = new Descuento(descuentoId);
		this.servicio = new Servicio(servicioId);
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
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	public Date getFecha_caducidad() {
		return fecha_caducidad;
	}
	public void setFecha_caducidad(Date fecha_caducidad) {
		this.fecha_caducidad = fecha_caducidad;
	}
	
	
}

package fox.hound.spring.models.puente;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Servicio;

@Entity
@Table(name="promocion_servicio")
//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "promocionServicio", singular = "promocionServicio")
public class PromocionServicio extends Puente {
	
	@ManyToOne
	@JoinColumn(name="promocion_id", nullable = false)
	@JsonBackReference(value="promocionServicio-promociones")
	private Promocion promocion;
	
	@ManyToOne
	@JoinColumn(name="servicio_id", nullable = false)
	@JsonBackReference(value="servicio-promocionServicio")
	private Servicio servicio;
	
	public PromocionServicio(Long id, int estatusId, String servicioId,
			String promocion) {
		super(id, estatusId);
		this.promocion = new Promocion(promocion);
		this.servicio = new Servicio(servicioId);
	}
	public PromocionServicio(String id) {
		super(id);
	}
	public PromocionServicio() {
		
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	public Promocion getPromocion() {
		return promocion;
	}
	public void setPromocion(Promocion promocion) {
		this.promocion = promocion;
	}
	
	
}

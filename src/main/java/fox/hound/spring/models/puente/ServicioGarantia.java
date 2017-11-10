package fox.hound.spring.models.puente;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Servicio;
import fox.hound.spring.models.combo.Garantia;

@Entity
@Table(name="servicio_garantia")
@CustomJsonRootName(plural = "servicioGarantia", singular = "servicioGarantia")
public class ServicioGarantia extends Puente {

	@ManyToOne
	@JoinColumn(name="garantia_id")
	@JsonBackReference(value="garantia-servicioGarantias")
	private Garantia garantia;
	
	@ManyToOne
	@JoinColumn(name="servicio_id")
	@JsonBackReference(value="servicio-servicioGarantias")
	private Servicio servicio;
	
	public ServicioGarantia(Long id, String estatusId,
			String garantiaId, String servicioId) {
		super(id, estatusId);
		this.garantia = new Garantia(garantiaId);
		this.servicio = new Servicio(servicioId);
	}
	public ServicioGarantia(String id) {
		super(id);
	}
	public ServicioGarantia() {
		super();
	}
	public Garantia getGarantia() {
		return garantia;
	}
	public void setGarantia(Garantia garantia) {
		this.garantia = garantia;
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
	
}

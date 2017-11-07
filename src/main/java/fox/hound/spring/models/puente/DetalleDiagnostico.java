package fox.hound.spring.models.puente;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.CondicionInmueble;

@Entity
@Table(name="detalle_diagnostico")
@CustomJsonRootName(plural = "detalleDiagnostico", singular = "detalleDiagnostico")
public class DetalleDiagnostico extends Puente {

	@ManyToOne
	@JoinColumn(name="condicionInmueble_id")
	@JsonBackReference
	private CondicionInmueble condicionInmueble;
	@ManyToOne
	@JoinColumn(name="caracteristicaInmueble_id")
	@JsonBackReference
	private CaracteristicaInmueble caracteristicaInmueble;
	
	//ManyToOne
	//DiagnosticoVisita
	
	public DetalleDiagnostico() {
		super();
	}
	public DetalleDiagnostico(Long id, String descripcion, String estatusId, String condicionInmuebleId,
			String caracteristicaInmuebleId) {
		super(id, descripcion, estatusId);
		this.condicionInmueble = new CondicionInmueble(condicionInmuebleId);
		this.caracteristicaInmueble = new CaracteristicaInmueble(caracteristicaInmuebleId);
	}
	public DetalleDiagnostico(String id) {
		super(id);
	}
	public CondicionInmueble getCondicionInmueble() {
		return condicionInmueble;
	}
	public void setCondicionInmueble(CondicionInmueble condicionInmueble) {
		this.condicionInmueble = condicionInmueble;
	}
	public CaracteristicaInmueble getCaracteristicaInmueble() {
		return caracteristicaInmueble;
	}
	public void setCaracteristicaInmueble(CaracteristicaInmueble caracteristicaInmueble) {
		this.caracteristicaInmueble = caracteristicaInmueble;
	}

	
}

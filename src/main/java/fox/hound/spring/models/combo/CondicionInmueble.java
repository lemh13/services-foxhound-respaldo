package fox.hound.spring.models.combo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.maestros.Condicion;
import fox.hound.spring.models.maestros.Maestro;
import fox.hound.spring.models.puente.DetalleDiagnostico;

@Entity
@Table(name="condicion_inmueble")
@CustomJsonRootName(plural = "condicionInmueble", singular = "condicionInmueble")
public class CondicionInmueble extends Maestro {
	
	@ManyToOne
	@JoinColumn(name="condicion_id")
	@JsonBackReference(value="condicionInmueble-condicion")
	private Condicion condicion;
	
	@OneToMany(mappedBy="condicionInmueble")
	@JsonManagedReference(value="condicionInmueble-detalleDiagnosticos")
	private List<DetalleDiagnostico> detalleDiagnosticos;

	public CondicionInmueble(Long id, String name, String estatusId, String condicionId) {
		super(id, name, estatusId);
		this.condicion = new Condicion(condicionId);
	}
	public CondicionInmueble(String id) {
		super(id);
	}
	public CondicionInmueble() {
		super();
	}
	public Condicion getCondicion() {
		return condicion;
	}
	public void setCondicion(Condicion condicion) {
		this.condicion = condicion;
	}
	public List<DetalleDiagnostico> getDetalleDiagnosticos() {
		return detalleDiagnosticos;
	}
	public void setDetalleDiagnosticos(List<DetalleDiagnostico> detalleDiagnosticos) {
		this.detalleDiagnosticos = detalleDiagnosticos;
	}
	
	
}
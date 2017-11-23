package fox.hound.spring.models.combo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.maestros.Condicion;
import fox.hound.spring.models.maestros.Maestro;
import fox.hound.spring.models.puente.CondicionDiagnostico;

@Entity
@Table(name="condicion_inmueble")
@CustomJsonRootName(plural = "condicionInmueble", singular = "condicionInmueble")
public class CondicionInmueble extends Maestro {
	
	@ManyToOne
	@JoinColumn(name="condicion_id")
	@JsonBackReference(value="condicionInmueble-condicion")
	private Condicion condicion;
	@Transient
	private Long padre_id;
	@Transient
	private String padre_descripcion;
	
	@OneToMany(mappedBy="condicionInmueble")
	@JsonManagedReference(value="condicionInmueble-detalleDiagnosticos")
	private List<CondicionDiagnostico> detalleDiagnosticos;

	public CondicionInmueble(Long id, String name, int estatusId, String condicionId) {
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
	public List<CondicionDiagnostico> getDetalleDiagnosticos() {
		return detalleDiagnosticos;
	}
	public void setDetalleDiagnosticos(List<CondicionDiagnostico> detalleDiagnosticos) {
		this.detalleDiagnosticos = detalleDiagnosticos;
	}
	public Long getPadre_id() {
		return condicion.getId();
	}
	public void setPadre_id(Long padre_id) {
		this.padre_id = padre_id;
	}
	public String getPadre_descripcion() {
		return condicion.getDescripcion();
	}
	public void setPadre_descripcion(String padre_descripcion) {
		this.padre_descripcion = padre_descripcion;
	}
	
	
}
package fox.hound.spring.models.puente;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.DetallePresupuesto;

@Entity
@Table(name="detalle_diagnostico_visita")
//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "detalleDiagnosticoVisita", singular = "detalleDiagnosticoVisita")
public class DetalleDiagnosticoVisita extends Puente {

	@Column(nullable = false)
	private double area;

	@ManyToOne
	@JoinColumn(name="condicionDiagnostico_id", nullable = false)
	@JsonBackReference(value="detalleDiagnostico-detalleDiagnosticoVisita")
	private CondicionDiagnostico condicionDiagnostico;
	
	@OneToMany(mappedBy="detalleDiagnosticoVisita")
	@JsonManagedReference(value="detallePresupuestos-detalleDiagnosticoVisita")
	private List<DetallePresupuesto> detallePresupuestos;
	
	public DetalleDiagnosticoVisita(Long id, int estatusId, double area, String condicionDiagnostico) {
		super(id, estatusId);
		this.condicionDiagnostico = new CondicionDiagnostico(condicionDiagnostico);
		this.area = area;
	}
	public DetalleDiagnosticoVisita(String id) {
		super(id);
	}
	public DetalleDiagnosticoVisita() {
		
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public CondicionDiagnostico getCondicionDiagnostico() {
		return condicionDiagnostico;
	}
	public void setCondicionDiagnostico(CondicionDiagnostico condicionDiagnostico) {
		this.condicionDiagnostico = condicionDiagnostico;
	}
	public List<DetallePresupuesto> getDetallePresupuestos() {
		return detallePresupuestos;
	}
	public void setDetallePresupuestos(List<DetallePresupuesto> detallePresupuestos) {
		this.detallePresupuestos = detallePresupuestos;
	}
	
	
}

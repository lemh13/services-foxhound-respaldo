package fox.hound.spring.models.puente;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.DetallePresupuesto;
import fox.hound.spring.models.combo.CondicionInmueble;
import fox.hound.spring.models.combo.DiagnosticoVisita;

@Entity
@Table(name="detalle_diagnostico")
@CustomJsonRootName(plural = "detalleDiagnostico", singular = "detalleDiagnostico")
public class DetalleDiagnostico extends Puente {

	@ManyToOne
	@JoinColumn(name="condicionInmueble_id")
	@JsonBackReference(value="condicionInmueble-detalleDiagnosticos")
	private CondicionInmueble condicionInmueble;
	
	@ManyToOne
	@JoinColumn(name="caracteristicaInmueble_id")
	@JsonBackReference(value="caracteristicaInmueble-detalleDiagnostico")
	private CaracteristicaInmueble caracteristicaInmueble;
	
	@ManyToOne
	@JoinColumn(name="diagnosticoVisita_id")
	@JsonBackReference(value="diagnosticoVisita-detalleDiagnosticos")
	private DiagnosticoVisita diagnosticoVisita;
	
	@OneToMany(mappedBy="detalleDiagnostico")
	@JsonManagedReference(value="detallePresupuesto-detalleDiagnostico")
	private List<DetallePresupuesto> detallePresupuestos;
	
	public DetalleDiagnostico() {
		super();
	}
	public DetalleDiagnostico(Long id, String estatusId, String condicionInmuebleId,
			String caracteristicaInmuebleId, String diagnosticoVisitaId) {
		super(id, estatusId);
		this.condicionInmueble = new CondicionInmueble(condicionInmuebleId);
		this.caracteristicaInmueble = new CaracteristicaInmueble(caracteristicaInmuebleId);
		this.diagnosticoVisita = new DiagnosticoVisita(diagnosticoVisitaId);
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
	public DiagnosticoVisita getDiagnosticoVisita() {
		return diagnosticoVisita;
	}
	public void setDiagnosticoVisita(DiagnosticoVisita diagnosticoVisita) {
		this.diagnosticoVisita = diagnosticoVisita;
	}
	public List<DetallePresupuesto> getDetallePresupuestos() {
		return detallePresupuestos;
	}
	public void setDetallePresupuestos(List<DetallePresupuesto> detallePresupuestos) {
		this.detallePresupuestos = detallePresupuestos;
	}

	
}

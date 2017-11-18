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
import fox.hound.spring.models.combo.CondicionInmueble;
import fox.hound.spring.models.combo.DiagnosticoVisita;

@Entity
@Table(name="condicion_diagnostico")
@CustomJsonRootName(plural = "condicionDiagnostico", singular = "condicionDiagnostico")
public class CondicionDiagnostico extends Puente {

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
	
	@OneToMany(mappedBy="condicionDiagnostico")
	@JsonManagedReference(value="detalleDiagnostico-detalleDiagnosticoVisita")
	private List<DetalleDiagnosticoVisita> detalleDiagnosticoVisitas;
	
	public CondicionDiagnostico() {
		super();
	}
	public CondicionDiagnostico(Long id, int estatusId, String condicionInmuebleId,
			String caracteristicaInmuebleId, String diagnosticoVisitaId) {
		super(id, estatusId);
		this.condicionInmueble = new CondicionInmueble(condicionInmuebleId);
		this.caracteristicaInmueble = new CaracteristicaInmueble(caracteristicaInmuebleId);
		this.diagnosticoVisita = new DiagnosticoVisita(diagnosticoVisitaId);
	}
	public CondicionDiagnostico(String id) {
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
	public List<DetalleDiagnosticoVisita> getDetalleDiagnosticoVisitas() {
		return detalleDiagnosticoVisitas;
	}
	public void setDetalleDiagnosticoVisitas(List<DetalleDiagnosticoVisita> detalleDiagnosticoVisitas) {
		this.detalleDiagnosticoVisitas = detalleDiagnosticoVisitas;
	}
	
	
}

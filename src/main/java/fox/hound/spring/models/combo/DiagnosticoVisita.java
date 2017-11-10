package fox.hound.spring.models.combo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.OrdenServicio;
import fox.hound.spring.models.Visita;
import fox.hound.spring.models.maestros.Maestro;
import fox.hound.spring.models.maestros.TipoDiagnosticoVisita;
import fox.hound.spring.models.puente.DetalleDiagnostico;

@Entity
@Table(name="diagnostico_visita")
@CustomJsonRootName(plural = "diagnosticoVisita", singular = "diagnosticoVisita")
public class DiagnosticoVisita extends Maestro {
	
	@OneToMany(mappedBy="diagnosticoVisita")
	@JsonManagedReference(value="diagnosticoVisita-detalleDiagnosticos")
	private List<DetalleDiagnostico> detalleDiagnosticos;
	
	@ManyToOne
	@JoinColumn(name="tipoDiagnosticoVisita_id")
	@JsonBackReference(value="diagnosticoVisita-tipoDiagnosticoVisita")
	private TipoDiagnosticoVisita tipoDiagnosticoVisita;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "diagnosticoVisita", cascade = CascadeType.ALL)
	private Visita visita;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "diagnosticoVisita", cascade = CascadeType.ALL)
	private Respuesta respuesta;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Presupuesto presupuesto;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private OrdenServicio ordenServicio;
	
	public DiagnosticoVisita(Long id, String name, String estatusId, String tipoDiagnosticoVisitaId) {
		super(id, name, estatusId);
		this.tipoDiagnosticoVisita = new TipoDiagnosticoVisita(tipoDiagnosticoVisitaId);
	}
	public DiagnosticoVisita(String id) {
		super(id);
	}
	public DiagnosticoVisita() {
		super();
	}
	public List<DetalleDiagnostico> getDetalleDiagnosticos() {
		return detalleDiagnosticos;
	}
	public void setDetalleDiagnosticos(List<DetalleDiagnostico> detalleDiagnosticos) {
		this.detalleDiagnosticos = detalleDiagnosticos;
	}
	public TipoDiagnosticoVisita getTipoDiagnosticoVisita() {
		return tipoDiagnosticoVisita;
	}
	public void setTipoDiagnosticoVisita(TipoDiagnosticoVisita tipoDiagnosticoVisita) {
		this.tipoDiagnosticoVisita = tipoDiagnosticoVisita;
	}
	public Visita getVisita() {
		return visita;
	}
	public void setVisita(Visita visita) {
		this.visita = visita;
	}
	public Respuesta getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}
	public Presupuesto getPresupuesto() {
		return presupuesto;
	}
	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}
	public OrdenServicio getOrdenServicio() {
		return ordenServicio;
	}
	public void setOrdenServicio(OrdenServicio ordenServicio) {
		this.ordenServicio = ordenServicio;
	}
	
	
}
package fox.hound.spring.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.Presupuesto;
import fox.hound.spring.models.maestros.Maestro;
import fox.hound.spring.models.maestros.TipoRespuesta;
import fox.hound.spring.models.puente.ValoracionOrdenServicio;

@Entity
@Table(name="respuesta")
@CustomJsonRootName(plural = "respuesta", singular = "respuesta")
public class Respuesta extends Maestro {
	
	@ManyToOne
	@JoinColumn(name="tipoRecurso_id")
	@JsonBackReference(value="respuesta-tipoRespuesta")
	private TipoRespuesta tipoRespuesta;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Presupuesto presupuesto;
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private ValoracionOrdenServicio valoracionOrdenServicio;
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private DiagnosticoVisita diagnosticoVisita;
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private OrdenServicio ordenServicio;

	public Respuesta(Long id, String name, int estatusId, String parroquiaId,
			String tipoRespuestaId) {
		super(id, name, estatusId);
		this.tipoRespuesta = new TipoRespuesta(tipoRespuestaId);
	}
	public Respuesta(String id) {
		super(id);
	}
	public Respuesta() {
		super();
	}
	
	public Presupuesto getPresupuesto() {
		return presupuesto;
	}
	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}
	public ValoracionOrdenServicio getValoracionOrdenServicio() {
		return valoracionOrdenServicio;
	}
	public void setValoracionOrdenServicio(ValoracionOrdenServicio valoracionOrdenServicio) {
		this.valoracionOrdenServicio = valoracionOrdenServicio;
	}
	public TipoRespuesta getTipoRespuesta() {
		return tipoRespuesta;
	}
	public void setTipoRespuesta(TipoRespuesta tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
	}
	public DiagnosticoVisita getDiagnosticoVisita() {
		return diagnosticoVisita;
	}
	public void setDiagnosticoVisita(DiagnosticoVisita diagnosticoVisita) {
		this.diagnosticoVisita = diagnosticoVisita;
	}
	public OrdenServicio getOrdenServicio() {
		return ordenServicio;
	}
	public void setOrdenServicio(OrdenServicio ordenServicio) {
		this.ordenServicio = ordenServicio;
	}
	
	
}

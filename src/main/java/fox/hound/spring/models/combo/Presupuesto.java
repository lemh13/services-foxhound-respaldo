package fox.hound.spring.models.combo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.DetallePresupuesto;
import fox.hound.spring.models.maestros.Maestro;
import fox.hound.spring.models.puente.MotivoPresupuesto;

@Entity
@Table(name="presupuesto")
@CustomJsonRootName(plural = "presupuesto", singular = "presupuesto")
public class Presupuesto extends Maestro {
	
	@Column(nullable = false)
    private double montoTotal;
    
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "presupuesto", cascade = CascadeType.ALL)
	private Respuesta respuesta;
	
	@OneToMany(mappedBy="presupuesto")
	@JsonManagedReference(value="detallePresupuesto-presupuesto")
	private List<DetallePresupuesto> detallePresupuestos;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "presupuesto", cascade = CascadeType.ALL)
	private DiagnosticoVisita diagnosticoVisita;
	
	@OneToMany(mappedBy="presupuesto")
	@JsonManagedReference(value="presupuesto-motivoPresupuestos")
	private List<MotivoPresupuesto> motivoPresupuestos;

	public Presupuesto(Long id, String name, int estatusId) {
		super(id, name, estatusId);
	}
	public Presupuesto(String id) {
		super(id);
	}
	public Presupuesto() {
		super();
	}
	public double getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}
	public Respuesta getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}
	public List<DetallePresupuesto> getDetallePresupuestos() {
		return detallePresupuestos;
	}
	public void setDetallePresupuestos(List<DetallePresupuesto> detallePresupuestos) {
		this.detallePresupuestos = detallePresupuestos;
	}
	public DiagnosticoVisita getDiagnosticoVisita() {
		return diagnosticoVisita;
	}
	public void setDiagnosticoVisita(DiagnosticoVisita diagnosticoVisita) {
		this.diagnosticoVisita = diagnosticoVisita;
	}
	public List<MotivoPresupuesto> getMotivoPresupuestos() {
		return motivoPresupuestos;
	}
	public void setMotivoPresupuestos(List<MotivoPresupuesto> motivoPresupuestos) {
		this.motivoPresupuestos = motivoPresupuestos;
	}
	
}
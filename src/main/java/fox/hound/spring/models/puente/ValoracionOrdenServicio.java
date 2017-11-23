package fox.hound.spring.models.puente;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.DiagnosticoVisita;
import fox.hound.spring.models.OrdenEntrega;
import fox.hound.spring.models.OrdenServicio;

@Entity
@Table(name="valoracion_orden_servicio")
@CustomJsonRootName(plural = "valoracionOrdenServicio", singular = "valoracionOrdenServicio")
public class ValoracionOrdenServicio extends Puente {

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private OrdenEntrega ordenEntrega;
	
	@ManyToOne
	@JoinColumn(name="diagnosticoVisita_id")
	@JsonBackReference(value="valoracionOrdenServicio-diagnosticoVisita")
	private DiagnosticoVisita diagnosticoVisita;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private OrdenServicio ordenServicio;
	
	public ValoracionOrdenServicio() {
		super();
	}
	public ValoracionOrdenServicio(Long id, int estatusId, String ordenEntregaId, String ordenServicioId,
			String diagnosticoVisita) {
		super(id, estatusId);
		this.diagnosticoVisita = new DiagnosticoVisita(diagnosticoVisita);
		this.ordenEntrega = new OrdenEntrega(ordenEntregaId);
		this.ordenServicio = new OrdenServicio(ordenServicioId);
	}
	public ValoracionOrdenServicio(String id) {
		super(id);
	}
	
	public OrdenServicio getOrdenServicio() {
		return ordenServicio;
	}
	public void setOrdenServicio(OrdenServicio ordenServicio) {
		this.ordenServicio = ordenServicio;
	}
	
	public OrdenEntrega getOrdenEntrega() {
		return ordenEntrega;
	}
	public void setOrdenEntrega(OrdenEntrega ordenEntrega) {
		this.ordenEntrega = ordenEntrega;
	}
	public DiagnosticoVisita getDiagnosticoVisita() {
		return diagnosticoVisita;
	}
	public void setDiagnosticoVisita(DiagnosticoVisita diagnosticoVisita) {
		this.diagnosticoVisita = diagnosticoVisita;
	}


}

package fox.hound.spring.models.puente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Servicio;
import fox.hound.spring.models.combo.Recurso;

@Entity
@Table(name="servicio_recurso")
@CustomJsonRootName(plural = "servicioRecurso", singular = "servicioRecurso")
public class ServicioRecurso extends Puente {

	@Column(nullable = false)
    private double cantidad;
	
	@ManyToOne
	@JoinColumn(name="recurso_id")
	@JsonBackReference(value="recurso-servicioRecursos")
	private Recurso recurso;
	
	@ManyToOne
	@JoinColumn(name="servicio_id")
	@JsonBackReference(value="servicio-servicioRecursos")
	private Servicio servicio;
	
	public ServicioRecurso(Long id, String estatusId, String recursoId, String servicioId) {
		super(id, estatusId);
		this.recurso = new Recurso(recursoId);
		this.servicio = new Servicio(servicioId);
	}
	public ServicioRecurso(String id) {
		super(id);
	}
	public ServicioRecurso() {
		super();
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public Recurso getRecurso() {
		return recurso;
	}
	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
	
}

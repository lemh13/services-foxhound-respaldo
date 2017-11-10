package fox.hound.spring.models.combo;


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
import fox.hound.spring.models.maestros.Maestro;
import fox.hound.spring.models.maestros.TipoRecurso;
import fox.hound.spring.models.puente.ServicioRecurso;

@Entity
@Table(name="recurso")
@CustomJsonRootName(plural = "recurso", singular = "recurso")
public class Recurso extends Maestro {
	
	@Column(nullable = false)
    private double costo;
	
	@ManyToOne
	@JoinColumn(name="tipoRecurso_id")
	@JsonBackReference(value="recurso-tipoRecurso")
	private TipoRecurso tipoRecurso;
	
	@OneToMany(mappedBy="recurso")
	@JsonManagedReference(value="recurso-servicioRecursos")
	private List<ServicioRecurso> servicioRecursos;
	
	public Recurso(Long id, String name, String estatusId, String parroquiaId, double costo,
			String tipoRecursoId) {
		super(id, name, estatusId);
		this.costo = costo;
		this.tipoRecurso = new TipoRecurso(tipoRecursoId);
	}
	public Recurso(String id) {
		super(id);
	}
	public Recurso() {
		super();
	}
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	public TipoRecurso getTipoRecurso() {
		return tipoRecurso;
	}
	public void setTipoRecurso(TipoRecurso tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}
	public List<ServicioRecurso> getServicioRecursos() {
		return servicioRecursos;
	}
	public void setServicioRecursos(List<ServicioRecurso> servicioRecursos) {
		this.servicioRecursos = servicioRecursos;
	}
	
	
}
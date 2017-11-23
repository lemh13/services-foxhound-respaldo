package fox.hound.spring.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.maestros.CondicionGarantia;
import fox.hound.spring.models.maestros.Maestro;

@Entity
@Table(name="garantia")
@CustomJsonRootName(plural = "garantia", singular = "garantia")
public class Garantia extends Maestro {

	@Column(nullable = false)
    private String titulo;
	@Column(nullable = false)
    private int valido;
	
	@OneToMany(mappedBy="garantia")
	@JsonManagedReference(value="garantia-condicionGarantias")
	private List<CondicionGarantia> condicionGarantias;
	
	@OneToMany(mappedBy="garantia")
	@JsonManagedReference(value="garantia-servicios")
	private List<Servicio> servicio;
	
	public Garantia() {
		super();
	}
	public Garantia(Long id, String descripcion, int estatusId, String titulo, int valido) {
		super(id, descripcion, estatusId);
		this.titulo = titulo;
		this.valido = valido;
	}
	public Garantia(String id) {
		super(id);
	}
	public List<CondicionGarantia> getCondicionGarantias() {
		return condicionGarantias;
	}
	public void setCondicionGarantias(List<CondicionGarantia> condicionGarantias) {
		this.condicionGarantias = condicionGarantias;
	}
	public List<Servicio> getServicio() {
		return servicio;
	}
	public void setServicio(List<Servicio> servicio) {
		this.servicio = servicio;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getValido() {
		return valido;
	}
	public void setValido(int valido) {
		this.valido = valido;
	}
	
	
	
}

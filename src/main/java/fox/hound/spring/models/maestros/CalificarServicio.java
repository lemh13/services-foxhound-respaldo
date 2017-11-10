package fox.hound.spring.models.maestros;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Servicio;

@Entity
@Table(name="calificar_servicio")
@CustomJsonRootName(plural = "calificarServicio", singular = "calificarServicio")
public class CalificarServicio extends Maestro {

	@Column(nullable = false)
    private int valor;
	
	@ManyToOne
	@JoinColumn(name="servicio_id")
	@JsonBackReference(value="servicio-calificarServicio")
	private Servicio servicio;
	
	public CalificarServicio(Long id, String name, String estatusId, int valor, String servicioId) {
		super(id, name, estatusId);
		this.valor = valor;
		this.servicio = new Servicio(servicioId);
	}
	public CalificarServicio(String id) {
		super(id);
	}
	public CalificarServicio() {
		super();
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
	
}

package fox.hound.spring.models.combo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Servicio;
import fox.hound.spring.models.maestros.Herramienta;
import fox.hound.spring.models.maestros.Maestro;
import fox.hound.spring.models.puente.DetalleOrdenServicio;

@Entity
@Table(name="servicio_herramienta")
@CustomJsonRootName(plural = "servicioHerramienta", singular = "servicioHerramienta")
public class ServicioHerramienta extends Maestro {
	
	@ManyToOne
	@JoinColumn(name="herramienta_id")
	@JsonBackReference(value="servicioHerramienta-herramienta")
	private Herramienta herramienta;
	
	@ManyToOne
	@JoinColumn(name="servicio_id")
	@JsonBackReference(value="servicio-servicioHerramientas")
	private Servicio servicio;
	
	@OneToMany(mappedBy="servicioHerramienta")
	@JsonManagedReference(value="servicioHerramienta-detalleOrdenServicios")
	private List<DetalleOrdenServicio> detalleOrdenServicios;

	public ServicioHerramienta(Long id, String name, String estatusId, String estadoId, String herramientaId, String servicioId) {
		super(id, name, estatusId);
		this.herramienta = new Herramienta(herramientaId);
		this.servicio = new Servicio(servicioId);
	}
	public ServicioHerramienta(String id) {
		super(id);
	}
	public ServicioHerramienta() {
		super();
	}
	public Herramienta getHerramienta() {
		return herramienta;
	}
	public void setHerramienta(Herramienta herramienta) {
		this.herramienta = herramienta;
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
}
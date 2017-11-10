package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.ServicioHerramienta;

@Entity
@Table(name="herramienta")
@CustomJsonRootName(plural = "herramienta", singular = "herramienta")
public class Herramienta extends Maestro {

	@OneToMany(mappedBy="herramienta")
	@JsonManagedReference(value="servicioHerramienta-herramienta")
	private List<ServicioHerramienta> servicioHerramientas;
	
	public Herramienta(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	public Herramienta(String id) {
		super(id);
	}
	public Herramienta() {
		super();
	}
	public List<ServicioHerramienta> getServicioHerramientas() {
		return servicioHerramientas;
	}
	public void setServicioHerramientas(List<ServicioHerramienta> servicioHerramientas) {
		this.servicioHerramientas = servicioHerramientas;
	}
	
}

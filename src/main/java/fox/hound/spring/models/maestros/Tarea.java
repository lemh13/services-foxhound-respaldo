package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.ServicioTarea;

@Entity
@Table(name="tarea")
@CustomJsonRootName(plural = "tarea", singular = "tarea")
public class Tarea extends Maestro {
	
	@OneToMany(mappedBy="tarea")
	@JsonManagedReference(value="servicioTarea-tarea")
	private List<ServicioTarea> servicioTareas;

	public Tarea(Long id, String name, int estatusId) {
		super(id, name, estatusId);
	}
	public Tarea(String id) {
		super(id);
	}
	public Tarea() {
		super();
	}
	public List<ServicioTarea> getServicioTareas() {
		return servicioTareas;
	}
	public void setServicioTareas(List<ServicioTarea> servicioTareas) {
		this.servicioTareas = servicioTareas;
	}
	
	
}

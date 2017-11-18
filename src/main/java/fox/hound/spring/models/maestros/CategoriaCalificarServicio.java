package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="categoria_calificar_servicio")
@CustomJsonRootName(plural = "categoriaCalificarServicio", singular = "categoriaCalificarServicio")
public class CategoriaCalificarServicio extends Maestro {

	@OneToMany(mappedBy="categoriaCalificarServicio")
	@JsonManagedReference(value="categoriaCalificarServicio-calificarServicios")
	private List<CalificarServicio> calificarServicios;
	
	public CategoriaCalificarServicio(Long id, String name, int estatusId) {
		super(id, name, estatusId);
	}
	public CategoriaCalificarServicio(String id) {
		super(id);
	}
	public CategoriaCalificarServicio() {
		super();
	}
	public List<CalificarServicio> getCalificarServicios() {
		return calificarServicios;
	}
	public void setCalificarServicios(List<CalificarServicio> calificarServicios) {
		this.calificarServicios = calificarServicios;
	}
	
	
}

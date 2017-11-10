package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Servicio;

@Entity
@Table(name="categoria")
@CustomJsonRootName(plural = "categoria", singular = "categoria")
public class Categoria extends Maestro {

	@OneToMany(mappedBy="categoria")
	@JsonManagedReference(value="servicio-categoria")
	private List<Servicio> servicios;
	
	public Categoria(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	public Categoria(String id) {
		super(id);
	}
	public Categoria() {
		super();
	}
	public List<Servicio> getServicios() {
		return servicios;
	}
	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}
	
}

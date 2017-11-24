package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.maestros.TipoServicio;

@Entity
@Table(name="categoria")
@CustomJsonRootName(plural = "categoria", singular = "categoria")
public class Categoria extends Maestro {

	@OneToMany(mappedBy="categoria")
	@JsonManagedReference(value="tiposervicio-categoria")
	private List<TipoServicio> tiposervicios;
	
	public Categoria(Long id, String name, int estatusId) {
		super(id, name, estatusId);
	}
	public Categoria(String id) {
		super(id);
	}
	public Categoria() {
		super();
	}
	public List<TipoServicio> getTipoServicios() {
		return tiposervicios;
	}
	public void setTipoServicios(List<TipoServicio> tiposervicios) {
		this.tiposervicios = tiposervicios;
	}
	
}

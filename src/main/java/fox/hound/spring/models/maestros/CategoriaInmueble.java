package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="categoria_inmueble")
@CustomJsonRootName(plural = "categoriaInmueble", singular = "categoriaInmueble")
public class CategoriaInmueble extends Maestro {

	@OneToMany(mappedBy="categoriaInmueble")
	@JsonManagedReference
	private List<TipoInmueble> tipoInmueble;
	
	public CategoriaInmueble(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	public CategoriaInmueble(String id) {
		super(id);
	}
	public CategoriaInmueble() {
		super();
	}

	public List<TipoInmueble> getTipoInmueble() {
		return tipoInmueble;
	}
	public void setTipoInmueble(List<TipoInmueble> tipoInmueble) {
		this.tipoInmueble = tipoInmueble;
	}

}

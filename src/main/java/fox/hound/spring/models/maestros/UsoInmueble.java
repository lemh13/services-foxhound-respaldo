package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Inmueble;
import fox.hound.spring.models.puente.TipoInmuebleServicio;

@Entity
@Table(name="uso_inmueble")
@CustomJsonRootName(plural = "usoInmueble", singular = "usoInmueble")
public class UsoInmueble extends Maestro {

	@OneToMany(mappedBy="usoInmueble")
	@JsonManagedReference(value="inmueble-usoInmueble")
	private List<Inmueble> inmueble;
	
	@OneToMany(mappedBy="usoInmueble")
	@JsonManagedReference(value="tipoInmuebleServicio-usoInmueble")
	private List<TipoInmuebleServicio> tipoInmuebleServicios;
	
	public UsoInmueble(Long id, String name, int estatusId) {
		super(id, name, estatusId);
	}
	public UsoInmueble(String id) {
		super(id);
	}
	public UsoInmueble() {
		super();
	}
	public List<Inmueble> getInmueble() {
		return inmueble;
	}
	public void setInmueble(List<Inmueble> inmueble) {
		this.inmueble = inmueble;
	}
	public List<TipoInmuebleServicio> getTipoInmuebleServicios() {
		return tipoInmuebleServicios;
	}
	public void setTipoInmuebleServicios(List<TipoInmuebleServicio> tipoInmuebleServicios) {
		this.tipoInmuebleServicios = tipoInmuebleServicios;
	}
	
	
}

package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.puente.CaracteristicaInmueble;

@Entity
@Table(name="ubicacion")
@CustomJsonRootName(plural = "ubicacion", singular = "ubicacion")
public class Ubicacion extends Maestro {
	
	@OneToMany(mappedBy="ubicacion")
	@JsonManagedReference(value="caracteristicaInmueble-ubicacion")
	private List<CaracteristicaInmueble> caracteristicaInmuebles;

	public Ubicacion(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	public Ubicacion(String id) {
		super(id);
	}
	public Ubicacion() {
		super();
	}
	public List<CaracteristicaInmueble> getCaracteristicaInmuebles() {
		return caracteristicaInmuebles;
	}
	public void setCaracteristicaInmuebles(List<CaracteristicaInmueble> caracteristicaInmuebles) {
		this.caracteristicaInmuebles = caracteristicaInmuebles;
	}
	
}

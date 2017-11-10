package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.Eventualidad;

@Entity
@Table(name="tipo_eventualidad")
@CustomJsonRootName(plural = "tipoEventualidad", singular = "tipoEventualidad")
public class TipoEventualidad extends Maestro {

	@OneToMany(mappedBy="tipoEventualidad")
	@JsonManagedReference(value="eventualidad-tipoEventualidad")
	private List<Eventualidad> eventualidades;
	
	public TipoEventualidad(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	public TipoEventualidad(String id) {
		super(id);
	}
	public TipoEventualidad() {
		
	}
	public List<Eventualidad> getEventualidades() {
		return eventualidades;
	}
	public void setEventualidades(List<Eventualidad> eventualidades) {
		this.eventualidades = eventualidades;
	}

}

package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Persona;

@Entity
@Table(name="tipo_identificacion")
@CustomJsonRootName(plural = "tipoIdentificacion", singular = "tipoIdentificacion")
public class TipoIdentificacion extends Maestro {

	@OneToMany(mappedBy="tipoIdentificacion")
	@JsonManagedReference
	private List<Persona> personas;
	
	public TipoIdentificacion(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	public TipoIdentificacion(String id) {
		super(id);
	}
	public TipoIdentificacion() {
		super();
	}
	public List<Persona> getPersonas() {
		return personas;
	}
	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

}

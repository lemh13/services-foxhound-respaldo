package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Persona;

@Entity
@Table(name="tipo_persona")
@CustomJsonRootName(plural = "tipoPersona", singular = "tipoPersona")
public class TipoPersona extends Maestro {
	
	@OneToMany(mappedBy="tipoPersona")
	@JsonManagedReference(value="personas-tipoPersona")
	private List<Persona> personas;

	public TipoPersona(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	public TipoPersona(String id) {
		super(id);
	}
	public TipoPersona() {
		
	}
	public List<Persona> getPersonas() {
		return personas;
	}
	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

}

package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.puente.ClienteProfesion;

@Entity
@Table(name="profesion")
@CustomJsonRootName(plural = "profesion", singular = "profesion")
public class Profesion extends Maestro {

	@OneToMany(mappedBy="profesion")
	@JsonManagedReference(value="profesion-clienteProfesion")
	private List<ClienteProfesion> clienteProfesions;
	
	public Profesion(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	public Profesion(String id) {
		super(id);
	}
	public Profesion() {
		super();
	}
	public List<ClienteProfesion> getClienteProfesions() {
		return clienteProfesions;
	}
	public void setClienteProfesions(List<ClienteProfesion> clienteProfesions) {
		this.clienteProfesions = clienteProfesions;
	}
	
	
}

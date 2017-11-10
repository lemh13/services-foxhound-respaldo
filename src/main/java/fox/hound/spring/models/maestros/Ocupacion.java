package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.puente.ClienteOcupacion;

@Entity
@Table(name="ocupacion")
@CustomJsonRootName(plural = "ocupacion", singular = "ocupacion")
public class Ocupacion extends Maestro {

	@OneToMany(mappedBy="cliente")
	@JsonManagedReference(value="ocupacion-clienteOcupacion")
	private List<ClienteOcupacion> clienteOcupacions;
	
	public Ocupacion(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	public Ocupacion(String id) {
		super(id);
	}
	public Ocupacion() {
		super();
	}
	public List<ClienteOcupacion> getClienteOcupacions() {
		return clienteOcupacions;
	}
	public void setClienteOcupacions(List<ClienteOcupacion> clienteOcupacions) {
		this.clienteOcupacions = clienteOcupacions;
	}
	
}

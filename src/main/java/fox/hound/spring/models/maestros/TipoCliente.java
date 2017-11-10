package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Cliente;

@Entity
@Table(name="tipo_cliente")
@CustomJsonRootName(plural = "tipoCliente", singular = "tipoCliente")
public class TipoCliente extends Maestro {

	@OneToMany(mappedBy="tipoCliente")
	@JsonManagedReference(value="cliente-tipoCliente")
	private List<Cliente> clientes;
	
	public TipoCliente(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	public TipoCliente(String id) {
		super(id);
	}
	public TipoCliente() {
		
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
}

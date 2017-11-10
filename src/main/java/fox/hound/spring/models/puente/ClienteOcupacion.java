package fox.hound.spring.models.puente;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Cliente;
import fox.hound.spring.models.maestros.Ocupacion;

@Entity
@Table(name="cliente_ocupacion")
//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "clienteOcupacion", singular = "clienteOcupacion")
public class ClienteOcupacion extends Puente {

	@ManyToOne
	@JoinColumn(name="clienteId", nullable = false)
	@JsonBackReference(value="cliente-clienteOcupacion")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="ocupacionId", nullable = false)
	@JsonBackReference(value="ocupacion-clienteOcupacion")
	private Ocupacion ocupacion;
	
	public ClienteOcupacion(Long id, String estatusId, String clienteId, String ocupacionId) {
		super(id, estatusId);
		this.cliente = new Cliente(clienteId);
		this.ocupacion = new Ocupacion(ocupacionId);
	}
	public ClienteOcupacion(String id) {
		super(id);
	}
	public ClienteOcupacion() {
		
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Ocupacion getOcupacion() {
		return ocupacion;
	}
	public void setOcupacion(Ocupacion ocupacion) {
		this.ocupacion = ocupacion;
	}
	
}

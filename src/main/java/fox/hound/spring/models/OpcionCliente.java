package fox.hound.spring.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.maestros.Opcion;
import fox.hound.spring.models.puente.Puente;

//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "opcionCliente", singular = "opcionCliente")
@Entity
@Table(name="opcion_cliente")
public class OpcionCliente extends Puente {

	@ManyToOne
	@JoinColumn(name="clienteId", nullable = false)
	@JsonBackReference(value="cliente-opcionCliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="opcion_id")
	@JsonBackReference(value="opcionCliente-opcion")
	private Opcion opcion;
	
	public OpcionCliente(Long id, int estatusId, String clienteId, String opcionId) {
		super(id, estatusId);
		this.cliente = new Cliente(clienteId);
		this.opcion = new Opcion(opcionId);
	}
	public OpcionCliente() {
		
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Opcion getOpcion() {
		return opcion;
	}
	public void setOpcion(Opcion opcion) {
		this.opcion = opcion;
	}
	
	
}

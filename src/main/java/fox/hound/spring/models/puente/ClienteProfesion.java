package fox.hound.spring.models.puente;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Cliente;
import fox.hound.spring.models.maestros.Profesion;

//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "clienteProfesion", singular = "clienteProfesion")
@Entity
@Table(name="cliente_profesion")
public class ClienteProfesion extends Puente {

	@ManyToOne
	@JoinColumn(name="clienteId", nullable = false)
	@JsonBackReference(value="cliente-clienteProfesion")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="profesion_id")
	@JsonBackReference(value="profesion-clienteProfesion")
	private Profesion profesion;
	
	@Transient
	private String profesionStr;
	
	public ClienteProfesion(Long id, int estatusId, String clienteId, String profesionId) {
		super(id, estatusId);
		this.cliente = new Cliente(clienteId);
		this.profesion = new Profesion(profesionId);
	}
	public ClienteProfesion(String id) {
		super(id);
	}
	public ClienteProfesion() {
		
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Profesion getProfesion() {
		return profesion;
	}
	public void setProfesion(Profesion profesion) {
		this.profesion = profesion;
	}
	public String getProfesionStr() {
		return profesion.getDescripcion();
	}
	public void setProfesionStr(String profesionStr) {
		this.profesionStr = profesionStr;
	}
	
}

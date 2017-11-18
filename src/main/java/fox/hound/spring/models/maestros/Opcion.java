package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.OpcionCliente;
import fox.hound.spring.models.combo.OpcionPregunta;

@Entity
@Table(name="opcion")
@CustomJsonRootName(plural = "opcion", singular = "opcion")
public class Opcion extends Maestro {

	@OneToMany(mappedBy="opcion")
	@JsonManagedReference(value="opcionPregunta-opcion")
	private List<OpcionPregunta> opcionPreguntas;
	
	@OneToMany(mappedBy="opcion")
	@JsonManagedReference(value="opcionCliente-opcion")
	private List<OpcionCliente> opcionClientes;
	
	public Opcion(Long id, String name, int estatusId) {
		super(id, name, estatusId);
	}
	public Opcion(String id) {
		super(id);
	}
	public Opcion() {
		super();
	}
	public List<OpcionPregunta> getOpcionPreguntas() {
		return opcionPreguntas;
	}
	public void setOpcionPreguntas(List<OpcionPregunta> opcionPreguntas) {
		this.opcionPreguntas = opcionPreguntas;
	}
	public List<OpcionCliente> getOpcionClientes() {
		return opcionClientes;
	}
	public void setOpcionClientes(List<OpcionCliente> opcionClientes) {
		this.opcionClientes = opcionClientes;
	}
	
	
}

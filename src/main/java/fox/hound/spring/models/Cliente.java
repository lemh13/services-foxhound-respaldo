package fox.hound.spring.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.maestros.TipoCliente;

@Entity
@Table(name="cliente")
//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "clientes", singular = "cliente")
public class Cliente extends Persona {

	@ManyToOne
	@JoinColumn(name="tipoCliente_id")
	@JsonBackReference
	private TipoCliente tipoCliente;
	@OneToMany(mappedBy="cliente")
	@JsonManagedReference
	private List<Inmueble> inmuebles;
	@OneToMany(mappedBy="cliente")
	@JsonManagedReference
	private List<OpcionCliente> opcionCliente;
	@OneToMany(mappedBy="cliente")
	@JsonManagedReference
	private List<ClienteProfesion> clienteProfesion;
	@OneToMany(mappedBy="cliente")
	@JsonManagedReference
	private List<ClienteOcupacion> clienteOcupacion;
	@OneToMany(mappedBy="cliente")
	@JsonManagedReference
	private List<PreferenciaCliente> preferenciaCliente;
	
	public Cliente(Long id, String nombre, char sexo, String direccion, int identificacion, Long estatus,
			Date fecha_de_nacimiento, String telefono, String estatusId, String tipoIdentificacionId,
			String tipoPersonaId, String sectorId, String tipoClienteId) {
		super(id, nombre, sexo, direccion, identificacion, estatus, fecha_de_nacimiento, telefono, estatusId,
				tipoIdentificacionId, tipoPersonaId, sectorId);
		this.tipoCliente = new TipoCliente(tipoClienteId);
	}
	public Cliente(String id) {
		super(id);
	}
	public Cliente() {
		super();
	}
	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	public List<Inmueble> getInmuebles() {
		return inmuebles;
	}
	public void setInmuebles(List<Inmueble> inmuebles) {
		this.inmuebles = inmuebles;
	}
	public List<OpcionCliente> getOpcionCliente() {
		return opcionCliente;
	}
	public void setOpcionCliente(List<OpcionCliente> opcionCliente) {
		this.opcionCliente = opcionCliente;
	}
	public List<ClienteProfesion> getClienteProfesion() {
		return clienteProfesion;
	}
	public void setClienteProfesion(List<ClienteProfesion> clienteProfesion) {
		this.clienteProfesion = clienteProfesion;
	}
	public List<ClienteOcupacion> getClienteOcupacion() {
		return clienteOcupacion;
	}
	public void setClienteOcupacion(List<ClienteOcupacion> clienteOcupacion) {
		this.clienteOcupacion = clienteOcupacion;
	}
	public List<PreferenciaCliente> getPreferenciaCliente() {
		return preferenciaCliente;
	}
	public void setPreferenciaCliente(List<PreferenciaCliente> preferenciaCliente) {
		this.preferenciaCliente = preferenciaCliente;
	}
	
	
}

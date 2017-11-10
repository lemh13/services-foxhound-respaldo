package fox.hound.spring.models.puente;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Cliente;

@Entity
@Table(name="caracteristica_inmueble")
@CustomJsonRootName(plural = "caracteristicaInmueble", singular = "caracteristicaInmueble")
public class PreferenciaCliente extends Puente {
	
	@ManyToOne
	@JoinColumn(name="tipoCaracteristicaInmuebleId", nullable = false)
	@JsonBackReference(value="tipoCaracteristicaInmueble-preferenciaCliente")
	private TipoCaracteristicaInmueble tipoCaracteristicaInmueble;
	
	@ManyToOne
	@JoinColumn(name="clienteId", nullable = false)
	@JsonBackReference(value="cliente-preferenciaCliente")
	private Cliente cliente;
	
	public PreferenciaCliente() {
		super();
	}
	public PreferenciaCliente(Long id, String estatusId, String tipoCaracteristicaInmuebleId,
			String clienteId) {
		super(id, estatusId);
		this.tipoCaracteristicaInmueble = new TipoCaracteristicaInmueble(tipoCaracteristicaInmuebleId);
		this.cliente = new Cliente(clienteId);
	}
	public PreferenciaCliente(String id) {
		super(id);
	}
	public TipoCaracteristicaInmueble getTipoCaracteristicaInmueble() {
		return tipoCaracteristicaInmueble;
	}
	public void setTipoCaracteristicaInmueble(TipoCaracteristicaInmueble tipoCaracteristicaInmueble) {
		this.tipoCaracteristicaInmueble = tipoCaracteristicaInmueble;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}

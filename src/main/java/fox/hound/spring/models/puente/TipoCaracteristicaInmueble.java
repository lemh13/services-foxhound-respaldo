package fox.hound.spring.models.puente;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.maestros.TipoCaracteristica;
import fox.hound.spring.models.maestros.TipoInmueble;

@Entity
@Table(name="tipo_caracteristica_inmueble")
@CustomJsonRootName(plural = "tipoCaracteristicaInmueble", singular = "tipoCaracteristicaInmueble")
public class TipoCaracteristicaInmueble extends Puente {

	@ManyToOne
	@JoinColumn(name="tipoCaracteristica_id", nullable = false)
	@JsonBackReference(value="tipoCaracteristica-tipoCaracteristicaInmueble")
	private TipoCaracteristica tipoCaracteristica;
	
	@ManyToOne
	@JoinColumn(name="tipoInmuebleId", nullable = false)
	@JsonBackReference(value="tipoInmueble-tipoCaracteristicaInmueble")
	private TipoInmueble tipoInmueble;
	
	@OneToMany(mappedBy="tipoCaracteristicaInmueble")
	@JsonManagedReference(value="detalleServicioInmueble-tipoCaracteristicaInmueble")
	private List<DetalleServicioInmueble> detalleServicioInmuebles;
	
//	@OneToMany(mappedBy="tipoCaracteristicaInmueble")
//	@JsonManagedReference(value="tipoCaracteristicaInmueble-preferenciaCliente")
//	private List<PreferenciaCliente> preferenciaClientes;
	
	public TipoCaracteristicaInmueble() {
		super();
	}
	public TipoCaracteristicaInmueble(Long id, int estatusId, String tipoCaracteristicaId,
			String tipoInmuebleId) {
		super(id, estatusId);
		this.tipoCaracteristica = new TipoCaracteristica(tipoCaracteristicaId);
		this.tipoInmueble = new TipoInmueble(tipoInmuebleId);
	}
	public TipoCaracteristicaInmueble(String id) {
		super(id);
	}
	public TipoCaracteristica getTipoCaracteristica() {
		return tipoCaracteristica;
	}
	public void setTipoCaracteristica(TipoCaracteristica tipoCaracteristica) {
		this.tipoCaracteristica = tipoCaracteristica;
	}
	public TipoInmueble getTipoInmueble() {
		return tipoInmueble;
	}
	public void setTipoInmueble(TipoInmueble tipoInmueble) {
		this.tipoInmueble = tipoInmueble;
//	}
//	public List<PreferenciaCliente> getPreferenciaClientes() {
//		return preferenciaClientes;
//	}
//	public void setPreferenciaClientes(List<PreferenciaCliente> preferenciaClientes) {
//		this.preferenciaClientes = preferenciaClientes;
	}
	public List<DetalleServicioInmueble> getDetalleServicioInmuebles() {
		return detalleServicioInmuebles;
	}
	public void setDetalleServicioInmuebles(List<DetalleServicioInmueble> detalleServicioInmuebles) {
		this.detalleServicioInmuebles = detalleServicioInmuebles;
	}

	
}

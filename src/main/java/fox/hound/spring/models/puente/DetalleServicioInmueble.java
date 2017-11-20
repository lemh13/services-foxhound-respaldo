package fox.hound.spring.models.puente;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Servicio;

@Entity
@Table(name="detalle_servicio_inmueble")
@CustomJsonRootName(plural = "detalleServicioInmueble", singular = "detalleServicioInmueble")
public class DetalleServicioInmueble extends Puente {
	
	@ManyToOne
	@JoinColumn(name="tipoCaracteristicaInmueble_id")
	@JsonBackReference(value="detalleServicioInmueble-tipoCaracteristicaInmueble")
	private TipoCaracteristicaInmueble tipoCaracteristicaInmueble;
	
	@ManyToOne
	@JoinColumn(name="servicio_id")
	@JsonBackReference(value="detalleServicioInmueble-servicio")
	private Servicio servicio;
	
	public DetalleServicioInmueble() {
		super();
	}
	public DetalleServicioInmueble(Long id, int estatusId,
			String servicio, String tipoCaracteristicaInmueble) {
		super(id, estatusId);
		this.tipoCaracteristicaInmueble = new TipoCaracteristicaInmueble(tipoCaracteristicaInmueble);
		this.servicio = new Servicio(servicio);
	}
	public DetalleServicioInmueble(String id) {
		super(id);
	}
	public TipoCaracteristicaInmueble getTipoCaracteristicaInmueble() {
		return tipoCaracteristicaInmueble;
	}
	public void setTipoCaracteristicaInmueble(TipoCaracteristicaInmueble tipoCaracteristicaInmueble) {
		this.tipoCaracteristicaInmueble = tipoCaracteristicaInmueble;
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
	
}

package fox.hound.spring.models.puente;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Servicio;
import fox.hound.spring.models.maestros.TipoCaracteristica;

@Entity
@Table(name="tipo_caracteristica_servicio")
@CustomJsonRootName(plural = "tipoCaracteristicaServicio", singular = "tipoCaracteristicaServicio")
public class TipoCaracteristicaServicio extends Puente {

	@ManyToOne
	@JoinColumn(name="tipoCaracteristica_id", nullable = false)
	@JsonBackReference(value="tipoCaracteristica-tipoCaracteristicaServicios")
	private TipoCaracteristica tipoCaracteristica;
	
	@ManyToOne
	@JoinColumn(name="servicio_id", nullable = false)
	@JsonBackReference(value="servicio-tipoCaracteristicaServicios")
	private Servicio servicio;
	
	public TipoCaracteristicaServicio() {
		super();
	}
	public TipoCaracteristicaServicio(Long id, String estatusId, String tipoCaracteristicaId
			, String servicioId) {
		super(id, estatusId);
		this.tipoCaracteristica = new TipoCaracteristica(tipoCaracteristicaId);
		this.servicio = new Servicio(servicioId);
	}
	public TipoCaracteristicaServicio(String id) {
		super(id);
	}
	public TipoCaracteristica getTipoCaracteristica() {
		return tipoCaracteristica;
	}
	public void setTipoCaracteristica(TipoCaracteristica tipoCaracteristica) {
		this.tipoCaracteristica = tipoCaracteristica;
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
}

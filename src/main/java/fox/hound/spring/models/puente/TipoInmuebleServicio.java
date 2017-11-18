package fox.hound.spring.models.puente;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Servicio;
import fox.hound.spring.models.maestros.UsoInmueble;

@Entity
@Table(name="tipo_inmueble_servicio")
//http://www.baeldung.com/jackson-annotations
@CustomJsonRootName(plural = "tipoInmuebleServicio", singular = "tipoInmuebleServicio")
public class TipoInmuebleServicio extends Puente {
	
	@ManyToOne
	@JoinColumn(name="usoInmueble_id", nullable = false)
	@JsonBackReference(value="tipoInmuebleServicio-usoInmueble")
	private UsoInmueble usoInmueble;
	
	@ManyToOne
	@JoinColumn(name="servicio_id", nullable = false)
	@JsonBackReference(value="servicio-tipoInmuebleServicio")
	private Servicio servicio;
	
	public TipoInmuebleServicio(Long id, int estatusId, String servicioId,
			String usoInmueble) {
		super(id, estatusId);
		this.usoInmueble = new UsoInmueble(usoInmueble);
		this.servicio = new Servicio(servicioId);
	}
	public TipoInmuebleServicio(String id) {
		super(id);
	}
	public TipoInmuebleServicio() {
		
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	public UsoInmueble getUsoInmueble() {
		return usoInmueble;
	}
	public void setUsoInmueble(UsoInmueble usoInmueble) {
		this.usoInmueble = usoInmueble;
	}
	
	
}

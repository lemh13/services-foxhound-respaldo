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
import fox.hound.spring.models.Inmueble;
import fox.hound.spring.models.combo.Caracteristica;
import fox.hound.spring.models.maestros.Ubicacion;

@Entity
@Table(name="caracteristica_inmueble")
@CustomJsonRootName(plural = "caracteristicaInmueble", singular = "caracteristicaInmueble")
public class CaracteristicaInmueble extends Puente {
	
	@ManyToOne
	@JoinColumn(name="inmueble_id")
	@JsonBackReference(value="inmueble-caracteristicaInmueble")
	private Inmueble inmueble;
	
	@ManyToOne
	@JoinColumn(name="ubicacion_id")
	@JsonBackReference(value="caracteristicaInmueble-ubicacion")
	private Ubicacion ubicacion;
	
	@ManyToOne
	@JoinColumn(name="caracteristica_id")
	@JsonBackReference(value="caracteristica-caracteristicaInmuebles")
	private Caracteristica caracteristica;
	
	@OneToMany(mappedBy="caracteristicaInmueble")
	@JsonManagedReference(value="caracteristicaInmueble-detalleDiagnostico")
	private List<DetalleDiagnostico> detalleDiagnostico;
	
	public CaracteristicaInmueble() {
		super();
	}
	public CaracteristicaInmueble(Long id, String estatusId, String inmuebleId, String ubicacionId,
			String caracteristicaId) {
		super(id, estatusId);
		this.inmueble = new Inmueble(inmuebleId);
		this.ubicacion = new Ubicacion(ubicacionId);
		this.caracteristica = new Caracteristica(caracteristicaId);
	}
	public CaracteristicaInmueble(String id) {
		super(id);
	}
	public Inmueble getInmueble() {
		return inmueble;
	}
	public void setInmueble(Inmueble inmueble) {
		this.inmueble = inmueble;
	}
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Caracteristica getCaracteristica() {
		return caracteristica;
	}
	public void setCaracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}
	public List<DetalleDiagnostico> getDetalleDiagnostico() {
		return detalleDiagnostico;
	}
	public void setDetalleDiagnostico(List<DetalleDiagnostico> detalleDiagnostico) {
		this.detalleDiagnostico = detalleDiagnostico;
	}
	
}

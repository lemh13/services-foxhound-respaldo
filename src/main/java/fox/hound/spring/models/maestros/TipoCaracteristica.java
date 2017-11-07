package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.Caracteristica;
import fox.hound.spring.models.puente.TipoCaracteristicaInmueble;

@Entity
@Table(name="tipo_caracteristica")
@CustomJsonRootName(plural = "tipoCaracteristica", singular = "tipoCaracteristica")
public class TipoCaracteristica extends Maestro {
	
	@OneToMany(mappedBy="tipoCaracteristica")
	@JsonManagedReference
	private List<Caracteristica> caracteristicas;
	@OneToMany(mappedBy="tipoCaracteristica")
	@JsonManagedReference
	private List<TipoCaracteristicaInmueble> tipoCaracteristicaInmueble;
	
	public TipoCaracteristica(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	public TipoCaracteristica(String id) {
		super(id);
	}
	public TipoCaracteristica() {
		
	}
	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(List<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	public List<TipoCaracteristicaInmueble> getTipoCaracteristicaInmueble() {
		return tipoCaracteristicaInmueble;
	}
	public void setTipoCaracteristicaInmueble(List<TipoCaracteristicaInmueble> tipoCaracteristicaInmueble) {
		this.tipoCaracteristicaInmueble = tipoCaracteristicaInmueble;
	}

}
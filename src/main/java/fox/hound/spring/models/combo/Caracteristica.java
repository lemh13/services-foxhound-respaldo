package fox.hound.spring.models.combo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.maestros.Maestro;
import fox.hound.spring.models.maestros.TipoCaracteristica;
import fox.hound.spring.models.puente.CaracteristicaInmueble;

@Entity
@Table(name="caracteristica")
@CustomJsonRootName(plural = "caracteristica", singular = "caracteristica")
public class Caracteristica extends Maestro {
	
	@ManyToOne
	@JoinColumn(name="tipoCaracteristica_id")
	@JsonBackReference(value="caracteristica-tipoCaracteristica")
	private TipoCaracteristica tipoCaracteristica;
	
	@OneToMany(mappedBy="caracteristica")
	@JsonManagedReference(value="caracteristica-caracteristicaInmuebles")
	private List<CaracteristicaInmueble> caracteristicaInmuebles;
	
	public Caracteristica(Long id, String name, int estatusId, String tipoCaracteristicasId) {
		super(id, name, estatusId);
		this.tipoCaracteristica = new TipoCaracteristica(tipoCaracteristicasId);
	}
	public Caracteristica(String id) {
		super(id);
	}
	public Caracteristica() {
		super();
	}
	public TipoCaracteristica getTipoCaracteristica() {
		return tipoCaracteristica;
	}
	public void setTipoCaracteristica(TipoCaracteristica tipoCaracteristicas) {
		this.tipoCaracteristica = tipoCaracteristicas;
	}
	public List<CaracteristicaInmueble> getCaracteristicaInmuebles() {
		return caracteristicaInmuebles;
	}
	public void setCaracteristicaInmuebles(List<CaracteristicaInmueble> caracteristicaInmuebles) {
		this.caracteristicaInmuebles = caracteristicaInmuebles;
	}
	
}
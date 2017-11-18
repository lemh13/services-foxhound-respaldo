package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.Caracteristica;
import fox.hound.spring.models.puente.TipoCaracteristicaInmueble;
import fox.hound.spring.models.puente.TipoCaracteristicaServicio;

@Entity
@Table(name="tipo_caracteristica")
@CustomJsonRootName(plural = "tipoCaracteristica", singular = "tipoCaracteristica")
public class TipoCaracteristica extends Maestro {
	
	@OneToMany(mappedBy="tipoCaracteristica")
	@JsonManagedReference(value="caracteristica-tipoCaracteristica")
	private List<Caracteristica> caracteristicas;
	
	@OneToMany(mappedBy="tipoCaracteristica")
	@JsonManagedReference(value="tipoCaracteristica-tipoCaracteristicaInmueble")
	private List<TipoCaracteristicaInmueble> tipoCaracteristicaInmueble;
	
	@OneToMany(mappedBy="tipoCaracteristica")
	@JsonManagedReference(value="tipoCaracteristica-tipoCaracteristicaServicios")
	private List<TipoCaracteristicaServicio> tipoCaracteristicaServicios;
	
	public TipoCaracteristica(Long id, String name, int estatusId) {
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
	public List<TipoCaracteristicaServicio> getTipoCaracteristicaServicios() {
		return tipoCaracteristicaServicios;
	}
	public void setTipoCaracteristicaServicios(List<TipoCaracteristicaServicio> tipoCaracteristicaServicios) {
		this.tipoCaracteristicaServicios = tipoCaracteristicaServicios;
	}

}

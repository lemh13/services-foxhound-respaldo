package fox.hound.spring.models.maestros;

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
import fox.hound.spring.models.puente.TipoCaracteristicaInmueble;

@Entity
@Table(name="tipo_inmueble")
@CustomJsonRootName(plural = "tipoInmueble", singular = "tipoInmueble")
public class TipoInmueble extends Maestro {
	
	@ManyToOne
	@JoinColumn(name="categoriaInmueble_id")
	@JsonBackReference
	private CategoriaInmueble categoriaInmueble;

	@OneToMany(mappedBy="tipoInmueble")
	@JsonManagedReference
	private List<Inmueble> inmuebles;
	
	@OneToMany(mappedBy="tipoInmueble")
	@JsonManagedReference
	private List<TipoCaracteristicaInmueble> tipoCaracteristicaInmueble;
	
	public TipoInmueble(Long id, String name, String estatusId, String categoriaInmuebleId) {
		super(id, name, estatusId);
		this.categoriaInmueble = new CategoriaInmueble(categoriaInmuebleId);
	}
	public TipoInmueble(String id) {
		super(id);
	}
	public TipoInmueble() {
		
	}
	public CategoriaInmueble getCategoriaInmueble() {
		return categoriaInmueble;
	}
	public void setCategoriaInmueble(CategoriaInmueble categoriaInmueble) {
		this.categoriaInmueble = categoriaInmueble;
	}
	public List<Inmueble> getInmuebles() {
		return inmuebles;
	}
	public void setInmuebles(List<Inmueble> inmuebles) {
		this.inmuebles = inmuebles;
	}
	public List<TipoCaracteristicaInmueble> getTipoCaracteristicaInmueble() {
		return tipoCaracteristicaInmueble;
	}
	public void setTipoCaracteristicaInmueble(List<TipoCaracteristicaInmueble> tipoCaracteristicaInmueble) {
		this.tipoCaracteristicaInmueble = tipoCaracteristicaInmueble;
	}

}

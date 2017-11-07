package fox.hound.spring.models.combo;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.maestros.Maestro;
import fox.hound.spring.models.maestros.TipoCaracteristica;

@Entity
@Table(name="caracteristica")
@CustomJsonRootName(plural = "caracteristica", singular = "caracteristica")
public class Caracteristica extends Maestro {
	
	@ManyToOne
	@JoinColumn(name="tipoCaracteristica_id")
	@JsonBackReference
	private TipoCaracteristica tipoCaracteristica;
	
	//OneToMany
	//CaracteristicaInmueble
	
	public Caracteristica(Long id, String name, String estatusId, String tipoCaracteristicasId) {
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
	
}
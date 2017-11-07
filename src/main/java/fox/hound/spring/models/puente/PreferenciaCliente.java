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
import fox.hound.spring.models.maestros.Ubicacion;

@Entity
@Table(name="caracteristica_inmueble")
@CustomJsonRootName(plural = "caracteristicaInmueble", singular = "caracteristicaInmueble")
public class PreferenciaCliente extends Puente {
	
	@ManyToOne
	@JoinColumn(name="tipoCaracteristicaInmuebleId", nullable = false)
	@JsonBackReference
	private TipoCaracteristicaInmueble tipoCaracteristicaInmueble;
	//manyToOne
	//Cliente
	
	
	public PreferenciaCliente() {
		super();
	}
	public PreferenciaCliente(Long id, String descripcion, String estatusId, String tipoCaracteristicaInmuebleId) {
		super(id, descripcion, estatusId);
		this.tipoCaracteristicaInmueble = new TipoCaracteristicaInmueble(tipoCaracteristicaInmuebleId);
		
	}
	public PreferenciaCliente(String id) {
		super(id);
	}
	
	
}

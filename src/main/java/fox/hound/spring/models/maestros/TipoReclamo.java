package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.puente.ReclamoOrdenEntrega;

@Entity
@Table(name="tipo_reclamo")
@CustomJsonRootName(plural = "tipoReclamo", singular = "tipoReclamo")
public class TipoReclamo extends Maestro {

	@OneToMany(mappedBy="tipoReclamo")
	@JsonManagedReference(value="reclamo-reclamoOrdenEntrega")
	private List<ReclamoOrdenEntrega> reclamoOrdenEntregas;
	
	public TipoReclamo(Long id, String name, int estatusId) {
		super(id, name, estatusId);
	}
	public TipoReclamo(String id) {
		super(id);
	}
	public TipoReclamo() {
		
	}
	public List<ReclamoOrdenEntrega> getReclamoOrdenEntregas() {
		return reclamoOrdenEntregas;
	}
	public void setReclamoOrdenEntregas(List<ReclamoOrdenEntrega> reclamoOrdenEntregas) {
		this.reclamoOrdenEntregas = reclamoOrdenEntregas;
	}
	
}

package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.Reclamo;

@Entity
@Table(name="tipo_reclamo")
@CustomJsonRootName(plural = "tipoReclamo", singular = "tipoReclamo")
public class TipoReclamo extends Maestro {

	@OneToMany(mappedBy="tipoReclamo")
	@JsonManagedReference(value="reclamo-tipoReclamo")
	private List<Reclamo> reclamos;
	
	public TipoReclamo(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	public TipoReclamo(String id) {
		super(id);
	}
	public TipoReclamo() {
		
	}
	public List<Reclamo> getReclamos() {
		return reclamos;
	}
	public void setReclamos(List<Reclamo> reclamos) {
		this.reclamos = reclamos;
	}

}

package fox.hound.spring.models.maestros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.Visita;

@Entity
@Table(name="tipo_visita")
@CustomJsonRootName(plural = "tipoVisita", singular = "tipoVisita")
public class TipoVisita extends Maestro {

	@OneToMany(mappedBy="tipoVisita")
	@JsonManagedReference(value="visita-tipoVisita")
	private List<Visita> visitas;
	
	public TipoVisita(Long id, String name, int estatusId) {
		super(id, name, estatusId);
	}
	public TipoVisita(String id) {
		super(id);
	}
	public TipoVisita() {
		
	}
	public List<Visita> getVisitas() {
		return visitas;
	}
	public void setVisitas(List<Visita> visitas) {
		this.visitas = visitas;
	}

}

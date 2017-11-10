package fox.hound.spring.models.maestros;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fox.hound.spring.beans.CustomJsonRootName;
import fox.hound.spring.models.combo.Recurso;

@Entity
@Table(name="tipo_recurso")
@CustomJsonRootName(plural = "tipoRecurso", singular = "tipoRecurso")
public class TipoRecurso extends Maestro {

	@OneToMany(mappedBy="tipoRecurso")
	@JsonManagedReference(value="recurso-tipoRecurso")
	private List<Recurso> recurso;
	
	public TipoRecurso(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	public TipoRecurso(String id) {
		super(id);
	}
	public TipoRecurso() {
		
	}
	public List<Recurso> getRecurso() {
		return recurso;
	}
	public void setRecurso(List<Recurso> recurso) {
		this.recurso = recurso;
	}

}

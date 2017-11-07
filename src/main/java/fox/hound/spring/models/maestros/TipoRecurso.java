package fox.hound.spring.models.maestros;

import javax.persistence.Entity;
import javax.persistence.Table;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="tipo_recurso")
@CustomJsonRootName(plural = "tipoRecurso", singular = "tipoRecurso")
public class TipoRecurso extends Maestro {

	public TipoRecurso(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	
	public TipoRecurso() {
		
	}

}

package fox.hound.spring.models.maestros;

import javax.persistence.Entity;
import javax.persistence.Table;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="tipo_reclamo")
@CustomJsonRootName(plural = "tipoReclamo", singular = "tipoReclamo")
public class TipoReclamo extends Maestro {

	public TipoReclamo(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	
	public TipoReclamo() {
		
	}

}

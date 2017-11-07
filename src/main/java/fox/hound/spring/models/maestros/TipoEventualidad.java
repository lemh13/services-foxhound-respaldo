package fox.hound.spring.models.maestros;

import javax.persistence.Entity;
import javax.persistence.Table;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="tipo_eventualidad")
@CustomJsonRootName(plural = "tipoEventualidad", singular = "tipoEventualidad")
public class TipoEventualidad extends Maestro {

	public TipoEventualidad(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	
	public TipoEventualidad() {
		
	}

}

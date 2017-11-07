package fox.hound.spring.models.maestros;

import javax.persistence.Entity;
import javax.persistence.Table;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="tipo_visita")
@CustomJsonRootName(plural = "tipoVisita", singular = "tipoVisita")
public class TipoVisita extends Maestro {

	public TipoVisita(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	
	public TipoVisita() {
		
	}

}

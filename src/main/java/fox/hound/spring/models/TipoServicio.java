package fox.hound.spring.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="tipo_servicio")
@CustomJsonRootName(plural = "tipoServicio", singular = "tipoServicio")
public class TipoServicio extends Maestro {

	public TipoServicio(Long id, String name) {
		super(id, name);
	}
	
	public TipoServicio() {
		
	}

}

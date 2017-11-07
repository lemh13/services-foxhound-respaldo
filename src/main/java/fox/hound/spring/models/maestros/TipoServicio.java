package fox.hound.spring.models.maestros;

import javax.persistence.Entity;
import javax.persistence.Table;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="tipo_servicio")
@CustomJsonRootName(plural = "tipoServicio", singular = "tipoServicio")
public class TipoServicio extends Maestro {

	public TipoServicio(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	
	public TipoServicio() {
		
	}

}

package fox.hound.spring.models.maestros;

import javax.persistence.Entity;
import javax.persistence.Table;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="tipo_motivo")
@CustomJsonRootName(plural = "tipoMotivo", singular = "tipoMotivo")
public class TipoMotivo extends Maestro {

	public TipoMotivo(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	
	public TipoMotivo() {
		
	}

}

package fox.hound.spring.models.maestros;

import javax.persistence.Entity;
import javax.persistence.Table;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="tipo_garantia")
@CustomJsonRootName(plural = "tipoGarantia", singular = "tipoGarantia")
public class TipoGarantia extends Maestro {

	public TipoGarantia(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	
	public TipoGarantia() {
		
	}

}

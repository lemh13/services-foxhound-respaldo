package fox.hound.spring.models.maestros;

import javax.persistence.Entity;
import javax.persistence.Table;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="unidad_medida")
@CustomJsonRootName(plural = "unidadMedida", singular = "unidadMedida")
public class UnidadMedida extends Maestro {

	public UnidadMedida(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	
	public UnidadMedida() {
		super();
	}
	
}

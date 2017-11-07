package fox.hound.spring.models.maestros;

import javax.persistence.Entity;
import javax.persistence.Table;

import fox.hound.spring.beans.CustomJsonRootName;

@Entity
@Table(name="ocupacion")
@CustomJsonRootName(plural = "ocupacion", singular = "ocupacion")
public class Ocupacion extends Maestro {

	public Ocupacion(Long id, String name, String estatusId) {
		super(id, name, estatusId);
	}
	
	public Ocupacion() {
		super();
	}
	
}
